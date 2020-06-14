package com.xml.service.impl;

import com.xml.dto.*;
import com.xml.enummeration.RentRequestStatus;
import com.xml.feignClients.CodebookFeignClient;
import com.xml.feignClients.RentRequestFeignClient;
import com.xml.feignClients.UserFeignClient;
import com.xml.mapper.CommentDtoMapper;
import com.xml.model.Advertisement;
import com.xml.model.Car;
import com.xml.repository.AdvertisementRepository;
import com.xml.service.AdvertisementService;
import com.xml.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Autowired
    private CodebookFeignClient codebookFeignClient;

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private CommentDtoMapper commentDtoMapper;

    @Autowired
    private CarService carService;

    @Autowired
    private RentRequestFeignClient rentRequestFeignClient;

    @Override
    public List<AdvertisementDto> getAll(String token) {
        List<AdvertisementDto> advertisementDtos = new ArrayList<>();

        List<Advertisement> allAdvertisements = this.advertisementRepository.findAll();

        return getAdvertisementDtos(token, advertisementDtos, allAdvertisements, "");
    }

    @Override
    public List<String> getAdvertisementPhotos(Long id) throws IOException {
        Path resourceDirectory = Paths.get("advertisement-service", "src", "main", "resources");
        String path = resourceDirectory.toFile().getAbsolutePath() + "\\images\\advertisement\\" + id + "\\";
        Set<String> allFiles = Stream.of(new File(path).listFiles())
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toSet());
        System.out.println(allFiles);
        List<String> allEncodedImages = new ArrayList<>();
        String encodeImage = null;
        for (String file : allFiles) {
            File image = new File(path + file);
            encodeImage = Base64.getEncoder().withoutPadding().encodeToString(Files.readAllBytes(image.toPath()));
            allEncodedImages.add(encodeImage);
        }
        return allEncodedImages;
    }

    @Override
    public AdvertisementDto getOne(Long id, String token) {
        List<AdvertisementDto> advertisementDtos = new ArrayList<>();
        List<Advertisement> advertisements = new ArrayList<>();
        advertisements.add(this.advertisementRepository.getOne(id));
        advertisementDtos = this.getAdvertisementDtos(token, advertisementDtos, advertisements, "");
        return advertisementDtos.get(0);
    }

    @Override
    public List<AdvertisementDto> getUserAdvertisements(Long userId, String token) {
        List<AdvertisementDto> advertisementDtos = new ArrayList<>();

        List<Advertisement> allAdvertisements = this.advertisementRepository.getAllByAdvertiserId(userId);

        return getAdvertisementDtos(token, advertisementDtos, allAdvertisements, "");
    }

    @Override
    public Long saveAdvertisement(CreateAdvertisementDto createAdvertisementDto, String token) throws ParseException {
        Car newCar = new Car();
        newCar.setCarBrandId(createAdvertisementDto.getCarBrand().getId());
        newCar.setCarModelId(createAdvertisementDto.getCarModel().getId());
        newCar.setCarClassId(createAdvertisementDto.getCarClass().getId());
        newCar.setFuelTypeId(createAdvertisementDto.getFuelType().getId());
        newCar.setTransmissionTypeId(createAdvertisementDto.getTransmissionType().getId());
        newCar.setMileage(createAdvertisementDto.getMileage());
        newCar.setCollisionDamageWaiverExists(createAdvertisementDto.isHasACDW());
        newCar.setChildSeats(createAdvertisementDto.getChildSeats());
        newCar.setAllowedDistance(createAdvertisementDto.getAllowedDistance());
        this.carService.save(newCar);

        Advertisement advertisement = new Advertisement();
        advertisement.setCar(newCar);
        advertisement.setAdvertiserId(createAdvertisementDto.getAdvertiserId());
        createAdvertisementDto.setAvailableFrom(createAdvertisementDto.getAvailableFrom().
                plus(1, ChronoUnit.DAYS));
        createAdvertisementDto.setAvailableTo(createAdvertisementDto.getAvailableTo().
                plus(1, ChronoUnit.DAYS));
        advertisement.setAvailableFrom(createAdvertisementDto.getAvailableFrom());
        advertisement.setAvailableTo(createAdvertisementDto.getAvailableTo());
        advertisement.setPricelistId(createAdvertisementDto.getPricelist().getId());
        advertisement.setDiscount(createAdvertisementDto.convertToHashMap(createAdvertisementDto.getDiscount()));
        this.advertisementRepository.save(advertisement);
        this.advertisementRepository.flush();

        if (createAdvertisementDto.getUserRole().equals("ROLE_CUSTOMER")) {
            this.userFeignClient.updateTimesPosted(createAdvertisementDto.getAdvertiserId(), token);
        }

        return advertisement.getId();
    }

    @Override
    public void uploadPhotos(MultipartFile[] files, Long id) throws IOException {
        Path resourceDirectory = Paths.get("advertisement-service", "src", "main", "resources");
        String path = resourceDirectory.toFile().getAbsolutePath() + File.separator + "images" + File.separator + "advertisement" + File.separator + id + File.separator;
        if (!new File(path).exists()) {
            new File(path).mkdir();
        }
        for (int i = 0; i < files.length; i++) {
            String imgName = files[i].getOriginalFilename();
            String filePath = path + imgName;
            File dest = new File(filePath);
            files[i].transferTo(dest);
        }
    }

    @Override
    public List<AdvertisementDto> basicSearch(String dateFrom, String dateTo, String place, String token) {
        dateFrom = dateFrom.replace('T', ' ');
        dateTo = dateTo.replace('T', ' ');

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateFromTime = LocalDateTime.parse(dateFrom, formatter);
        LocalDateTime dateFromTo = LocalDateTime.parse(dateTo, formatter);

        List<AdvertisementDto> advertisementDtos = new ArrayList<>();

        List<Advertisement> allAdvertisements = this.advertisementRepository.basicSearch(dateFromTime, dateFromTo);
        List<Advertisement> validAdvertisements = new ArrayList<>();

        List<RentRequestDto> rentRequestDtos = this.rentRequestFeignClient.getPaidRentRequests(token);
        List<RentRequestDto> invalidRentRequestDtos = new ArrayList<>();

        for (RentRequestDto rentRequestDto : rentRequestDtos) {
            if (rentRequestDto.getReservedTo().isBefore(dateFromTime) || rentRequestDto.getReservedFrom().isAfter(dateFromTo)) {
                System.out.println("U redu je");
            } else {
                invalidRentRequestDtos.add(rentRequestDto);
            }
        }

        boolean flag = false;
        for (Advertisement a : allAdvertisements) {
            for (RentRequestDto rentRequestDto : invalidRentRequestDtos) {
                for (AdvertisementDto adto : rentRequestDto.getAdvertisementsForRent()) {
                    if (a.getId().equals(adto.getId())) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    break;
                }
            }
            if (!flag) {
                validAdvertisements.add(a);
            }
            flag = false;
        }


        return getAdvertisementDtos(token, advertisementDtos, validAdvertisements, place);
    }

    @Override
    public List<AdvertisementDto> basicSearchForMyAdvertisements(String dateFrom, String dateTo, Long id, String token) {
        dateFrom = dateFrom.replace('T', ' ');
        dateTo = dateTo.replace('T', ' ');

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateFromTime = LocalDateTime.parse(dateFrom, formatter);
        LocalDateTime dateToTime = LocalDateTime.parse(dateTo, formatter);

        List<AdvertisementDto> advertisementDtos = new ArrayList<>();
        List<Advertisement> advertisementList = this.advertisementRepository.basicSearchForMyAdvertisements(dateFromTime, dateToTime, id);
        List<Advertisement> validAdvertisements = new ArrayList<>();

        List<RentRequestDto> rentRequestDtos = this.rentRequestFeignClient.getPaidRentRequests(token);
        List<RentRequestDto> invalidRentRequestDtos = new ArrayList<>();

        for (RentRequestDto rentRequestDto : rentRequestDtos) {
            if (rentRequestDto.getReservedTo().isBefore(dateFromTime) || rentRequestDto.getReservedFrom().isAfter(dateToTime)) {
                System.out.println("U redu je");
            } else {
                invalidRentRequestDtos.add(rentRequestDto);
            }
        }

        boolean flag = false;
        for (Advertisement a : advertisementList) {
            for (RentRequestDto rentRequestDto : invalidRentRequestDtos) {
                for (AdvertisementDto adto : rentRequestDto.getAdvertisementsForRent()) {
                    if (a.getId().equals(adto.getId())) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    break;
                }
            }
            if (!flag) {
                validAdvertisements.add(a);
            }
            flag = false;
        }

        return getAdvertisementDtos(token, advertisementDtos, validAdvertisements, "");
    }


    private List<AdvertisementDto> getAdvertisementDtos(String token, List<AdvertisementDto> advertisementDtos, List<Advertisement> allAdvertisements, String place) {
        for (Advertisement advertisement : allAdvertisements) {
            UserDto advertiserDto = this.userFeignClient.getUserById(advertisement.getAdvertiserId(), token);

            if (!place.equals("")) {
                if (!advertiserDto.getCity().toLowerCase().contains(place.toLowerCase())) {
                    continue;
                }
            }

            CodebookInfoDto codebookInfoDto = this.codebookFeignClient.getMoreInfo(advertisement.getCar().getCarBrandId(),
                    advertisement.getCar().getCarModelId(), advertisement.getCar().getCarClassId(), advertisement.getCar().getFuelTypeId(),
                    advertisement.getCar().getTransmissionTypeId(), advertisement.getPricelistId());


            AdvertisementDto advertisementDto = new AdvertisementDto();
            advertisementDto.setId(advertisement.getId());
            advertisementDto.setAvailableFrom(advertisement.getAvailableFrom());
            advertisementDto.setAvailableTo(advertisement.getAvailableTo());
            advertisementDto.setPricelist(codebookInfoDto.getPricelistDto());

            CarDto carDto = new CarDto();
            carDto.setId(advertisement.getCar().getId());
            carDto.setAllowedDistance(advertisement.getCar().getAllowedDistance());
            carDto.setAverageRating(advertisement.getCar().getAverageRating());
            carDto.setCarBrand(codebookInfoDto.getCarBrandDto());
            carDto.setCarClass(codebookInfoDto.getCarClassDto());
            carDto.setCarModel(codebookInfoDto.getCarModelDto());
            carDto.setChildSeats(advertisement.getCar().getChildSeats());
            carDto.setCollisionDamageWaiverExists(advertisement.getCar().isCollisionDamageWaiverExists());
            carDto.setFuelType(codebookInfoDto.getFuelTypeDto());
            carDto.setHasAndroid(advertisement.getCar().isHasAndroid());
            carDto.setMileage(advertisement.getCar().getMileage());
            carDto.setTimesRated(advertisement.getCar().getTimesRated());
            carDto.setTransmissionType(codebookInfoDto.getTransmissionTypeDto());

            advertisementDto.setCar(carDto);
            advertisementDto.setComments(advertisement.getComments().stream().map(commentDtoMapper::toDto).collect(Collectors.toSet()));
            advertisementDto.setAdvertiser(advertiserDto);

            advertisementDtos.add(advertisementDto);
        }

        return advertisementDtos;
    }


}
