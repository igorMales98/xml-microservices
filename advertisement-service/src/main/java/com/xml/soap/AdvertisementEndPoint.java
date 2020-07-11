package com.xml.soap;

import com.xml.dto.AdvertisementDto;
import com.xml.dto.CodebookInfoDto;
import com.xml.dto.CreateAdvertisementDto;
import com.xml.feignClients.CodebookFeignClient;
import com.xml.model.Car;
import com.xml.repository.AdvertisementRepository;
import com.xml.repository.CarRepository;
import com.xml.service.AdvertisementService;

import com.xml.service.impl.AdvertisementServiceImpl;
import com.xml.soap.code.*;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.io.*;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Endpoint
public class AdvertisementEndPoint {
    private static final String NAMESPACE_URI = "http://localhost:8085/advertisement-service-schema";

    @Autowired
    private AdvertisementService advertisementService;

    @Autowired
    private AdvertisementServiceImpl advertisementServiceImpl;

    @Autowired
    private CodebookFeignClient codebookFeignClient;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private AdvertisementRepository advertisementRepository;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "advertisementRequest")
    @ResponsePayload
    public AdvertisementResponse createAdvertisement(@RequestPayload AdvertisementRequest request) throws ParseException, IOException {
        System.out.println("SOAP - Create Advertisement");
        AdvertisementResponse response = new AdvertisementResponse();
        CreateAdvertisementDto advertisementDto = new CreateAdvertisementDto();

        advertisementDto.setAvailableFrom(LocalDateTime.parse(request.getAvailableFrom()));
        advertisementDto.setAvailableTo(LocalDateTime.parse(request.getAvailableTo()));
        advertisementDto.setAdvertiserId(request.getAdvertiserId());
        advertisementDto.setAllowedDistance(request.getCar().getAllowedDistance());

        CodebookInfoDto codebookInfo = this.codebookFeignClient.getMoreInfo(request.getCar().getCarBrandId(),
                request.getCar().getCarModelId(), request.getCar().getCarClassId(), request.getCar().getFuelTypeId(),
                request.getCar().getTransmissionTypeId(), request.getPricelistId());

        advertisementDto.setCarBrand(codebookInfo.getCarBrandDto());
        advertisementDto.setCarModel(codebookInfo.getCarModelDto());
        advertisementDto.setCarClass(codebookInfo.getCarClassDto());
        advertisementDto.setFuelType(codebookInfo.getFuelTypeDto());
        advertisementDto.setTransmissionType(codebookInfo.getTransmissionTypeDto());
        advertisementDto.setPricelist(codebookInfo.getPricelistDto());
        advertisementDto.setHasACDW(request.getCar().isCollisionDamageWaiverExists());
        advertisementDto.setMileage(request.getCar().getMileage());
        advertisementDto.setHasAndroid(request.getCar().isHasAndroid());
        advertisementDto.setDiscount(request.getDiscount());
        advertisementDto.setUserRole("");

        // Fale Android i ChildSeats i slike
        long advertisementId = advertisementService.saveAdvertisement(advertisementDto, "");
        Car car = this.carRepository.findTopByOrderByIdDesc();
        response.setAdvertisementId(advertisementId);
        response.setCarId(car.getId());

        Path resourceDirectory = Paths.get("advertisement-service", "src", "main", "resources");
        String path = resourceDirectory.toFile().getAbsolutePath() + File.separator + "images" + File.separator + "advertisement" +
                File.separator + advertisementId + File.separator;
        if (!new File(path).exists()) {
            new File(path).mkdir();
        }

        String pathToAudi = resourceDirectory.toFile().getAbsolutePath() + File.separator + "images" + File.separator + "advertisement" +
                File.separator + "audi";

        File source = new File(pathToAudi);
        File dest = new File(path);
        FileUtils.copyDirectory(source, dest);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAdvertisementsRequest")
    @ResponsePayload
    public GetAdvertisementsResponse getAdvertisements(@RequestPayload GetAdvertisementsRequest request) throws ParseException, IOException {
        System.out.println("SOAP - Get Advertisements");

        String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJic2VwIiwic3ViIjoibWFyaWNtIiwiYXVkIjoid2ViIiwiaWF0IjoxNTk0NDk5OTQzLCJleHAiOjE1OTQ1ODYzNDMsIlJPTEVfVVNFUiI6eyJpZCI6MiwibmFtZSI6IlJPTEVfQUdFTlQiLCJwZXJtaXNzaW9ucyI6W3siaWQiOjQ1LCJuYW1lIjoiUkVBRF9SRU5UX1JFUVVFU1RTIiwiYXV0aG9yaXR5IjoiUkVBRF9SRU5UX1JFUVVFU1RTIn0seyJpZCI6NCwibmFtZSI6IlJFQURfVVNFUiIsImF1dGhvcml0eSI6IlJFQURfVVNFUiJ9LHsiaWQiOjE5LCJuYW1lIjoiUkVBRF9BUFBST1ZFRF9DT01NRU5UUyIsImF1dGhvcml0eSI6IlJFQURfQVBQUk9WRURfQ09NTUVOVFMifSx7ImlkIjoyOCwibmFtZSI6IlJFQURfQ0FSX0NMQVNTRVMiLCJhdXRob3JpdHkiOiJSRUFEX0NBUl9DTEFTU0VTIn0seyJpZCI6NDAsIm5hbWUiOiJSRUFEX1BSSUNFTElTVFMiLCJhdXRob3JpdHkiOiJSRUFEX1BSSUNFTElTVFMifSx7ImlkIjoyMSwibmFtZSI6IkNSRUFURV9DT01NRU5UIiwiYXV0aG9yaXR5IjoiQ1JFQVRFX0NPTU1FTlQifSx7ImlkIjozMSwibmFtZSI6IlJFQURfQ0FSX01PREVMUyIsImF1dGhvcml0eSI6IlJFQURfQ0FSX01PREVMUyJ9LHsiaWQiOjQyLCJuYW1lIjoiUkVBRF9NRVNTQUdFUyIsImF1dGhvcml0eSI6IlJFQURfTUVTU0FHRVMifSx7ImlkIjo0NiwibmFtZSI6IkVESVRfUkVOVF9SRVFVRVNUUyIsImF1dGhvcml0eSI6IkVESVRfUkVOVF9SRVFVRVNUUyJ9LHsiaWQiOjQxLCJuYW1lIjoiUEVPUExFX0ZPUl9DSEFUIiwiYXV0aG9yaXR5IjoiUEVPUExFX0ZPUl9DSEFUIn0seyJpZCI6NDMsIm5hbWUiOiJDUkVBVEVfTUVTU0FHRSIsImF1dGhvcml0eSI6IkNSRUFURV9NRVNTQUdFIn0seyJpZCI6MTQsIm5hbWUiOiJVUExPQURfUEhPVE9TIiwiYXV0aG9yaXR5IjoiVVBMT0FEX1BIT1RPUyJ9LHsiaWQiOjEzLCJuYW1lIjoiQ1JFQVRFX0FEVkVSVElTRU1FTlRTIiwiYXV0aG9yaXR5IjoiQ1JFQVRFX0FEVkVSVElTRU1FTlRTIn0seyJpZCI6MTYsIm5hbWUiOiJCQVNJQ19TRUFSQ0hfVVNFUiIsImF1dGhvcml0eSI6IkJBU0lDX1NFQVJDSF9VU0VSIn0seyJpZCI6MzcsIm5hbWUiOiJSRUFEX1RSQU5TTUlTU0lPTl9UWVBFUyIsImF1dGhvcml0eSI6IlJFQURfVFJBTlNNSVNTSU9OX1RZUEVTIn0seyJpZCI6MTIsIm5hbWUiOiJSRUFEX1VTRVJfQURWRVJUSVNFTUVOVFMiLCJhdXRob3JpdHkiOiJSRUFEX1VTRVJfQURWRVJUSVNFTUVOVFMifSx7ImlkIjoxMSwibmFtZSI6IlJFQURfQURWRVJUSVNFTUVOVFMiLCJhdXRob3JpdHkiOiJSRUFEX0FEVkVSVElTRU1FTlRTIn0seyJpZCI6MTUsIm5hbWUiOiJCQVNJQ19TRUFSQ0giLCJhdXRob3JpdHkiOiJCQVNJQ19TRUFSQ0gifSx7ImlkIjoyNSwibmFtZSI6IlJFQURfQ0FSX0JSQU5EUyIsImF1dGhvcml0eSI6IlJFQURfQ0FSX0JSQU5EUyJ9LHsiaWQiOjUsIm5hbWUiOiJXSE9fQU1fSSIsImF1dGhvcml0eSI6IldIT19BTV9JIn0seyJpZCI6NywibmFtZSI6IkNSRUFURV9QSFlTSUNBTF9VU0VSIiwiYXV0aG9yaXR5IjoiQ1JFQVRFX1BIWVNJQ0FMX1VTRVIifSx7ImlkIjoyMCwibmFtZSI6IlJFUExZX0NPTU1FTlQiLCJhdXRob3JpdHkiOiJSRVBMWV9DT01NRU5UIn0seyJpZCI6NDQsIm5hbWUiOiJDUkVBVEVfUkVOVF9SRVFVRVNUUyIsImF1dGhvcml0eSI6IkNSRUFURV9SRU5UX1JFUVVFU1RTIn0seyJpZCI6MzQsIm5hbWUiOiJSRUFEX0ZVRUxfVFlQRVMiLCJhdXRob3JpdHkiOiJSRUFEX0ZVRUxfVFlQRVMifV19fQ.uvAOe8C6wPKrPMznzUcGlZ4Vny3T1Xu4JdkQyFejqYlttHkBX418s5g3cjvWDpWaq6IKwMecGIKGAcumr-Tyxg";
        GetAdvertisementsResponse getAdvertisementsResponse = new GetAdvertisementsResponse();
        List<AdvertisementDto> advertisementDtos = new ArrayList<>();
        try {
            advertisementDtos = this.advertisementService.getAgentAdvertisementsDtos(2L, token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (AdvertisementDto advertisementDto : advertisementDtos) {
            Advertisement advertisement = new Advertisement();
            advertisement.setId(advertisementDto.getId());
            advertisement.setAdvertiserId(advertisementDto.getAdvertiser().getId());
            advertisement.setAvailableFrom(advertisementDto.getAvailableFrom().toString());
            advertisement.setAvailableTo(advertisementDto.getAvailableTo().toString());
            advertisement.setPricelistId(advertisementDto.getPricelist().getId());

            com.xml.soap.code.Car car = new com.xml.soap.code.Car();
            car.setCarBrandId(advertisementDto.getCar().getCarBrand().getId());
            car.setCarModelId(advertisementDto.getCar().getCarModel().getId());
            car.setCarClassId(advertisementDto.getCar().getCarClass().getId());
            car.setTransmissionTypeId(advertisementDto.getCar().getTransmissionType().getId());
            car.setFuelTypeId(advertisementDto.getCar().getFuelType().getId());
            car.setCollisionDamageWaiverExists(advertisementDto.getCar().isCollisionDamageWaiverExists());
            car.setHasAndroid(advertisementDto.getCar().isHasAndroid());
            car.setMileage(advertisementDto.getCar().getMileage());
            car.setAllowedDistance(advertisementDto.getCar().getAllowedDistance());
            car.setAverageRating(advertisementDto.getCar().getAverageRating());
            car.setTimesRated(advertisementDto.getCar().getTimesRated());

            advertisement.setCar(car);

            getAdvertisementsResponse.getAdvertisement().add(advertisement);
        }

        return getAdvertisementsResponse;

    }

}
