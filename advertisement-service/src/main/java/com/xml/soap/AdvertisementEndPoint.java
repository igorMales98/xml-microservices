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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.io.IOException;
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

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAdvertisementsRequest")
    @ResponsePayload
    public GetAdvertisementsResponse getAdvertisements(@RequestPayload GetAdvertisementsRequest request) throws ParseException, IOException {
        System.out.println("SOAP - Get Advertisements");

        String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJic2VwIiwic3ViIjoiam92YW5qbyIsImF1ZCI6IndlYiIsImlhdCI6MTU5NDQwNDk0MSwiZXhwIjoxNTk0NDkxMzQxLCJST0xFX1VTRVIiOnsiaWQiOjEsIm5hbWUiOiJST0xFX0FETUlOIiwicGVybWlzc2lvbnMiOlt7ImlkIjo0LCJuYW1lIjoiUkVBRF9VU0VSIiwiYXV0aG9yaXR5IjoiUkVBRF9VU0VSIn0seyJpZCI6MzUsIm5hbWUiOiJDUkVBVEVfRlVFTF9UWVBFUyIsImF1dGhvcml0eSI6IkNSRUFURV9GVUVMX1RZUEVTIn0seyJpZCI6MTEsIm5hbWUiOiJSRUFEX0FEVkVSVElTRU1FTlRTIiwiYXV0aG9yaXR5IjoiUkVBRF9BRFZFUlRJU0VNRU5UUyJ9LHsiaWQiOjEwLCJuYW1lIjoiQ1JFQVRFX0FHRU5UUyIsImF1dGhvcml0eSI6IkNSRUFURV9BR0VOVFMifSx7ImlkIjoyMywibmFtZSI6IkRFQ0xJTkVfQ09NTUVOVCIsImF1dGhvcml0eSI6IkRFQ0xJTkVfQ09NTUVOVCJ9LHsiaWQiOjMxLCJuYW1lIjoiUkVBRF9DQVJfTU9ERUxTIiwiYXV0aG9yaXR5IjoiUkVBRF9DQVJfTU9ERUxTIn0seyJpZCI6MSwibmFtZSI6IlJFQURfUkVHSVNUUkFUSU9OX1JFUVVFU1RTIiwiYXV0aG9yaXR5IjoiUkVBRF9SRUdJU1RSQVRJT05fUkVRVUVTVFMifSx7ImlkIjoxMiwibmFtZSI6IlJFQURfVVNFUl9BRFZFUlRJU0VNRU5UUyIsImF1dGhvcml0eSI6IlJFQURfVVNFUl9BRFZFUlRJU0VNRU5UUyJ9LHsiaWQiOjUsIm5hbWUiOiJXSE9fQU1fSSIsImF1dGhvcml0eSI6IldIT19BTV9JIn0seyJpZCI6MjcsIm5hbWUiOiJFRElUX0NBUl9CUkFORFMiLCJhdXRob3JpdHkiOiJFRElUX0NBUl9CUkFORFMifSx7ImlkIjoxOCwibmFtZSI6IlJFQURfQ09NTUVOVFMiLCJhdXRob3JpdHkiOiJSRUFEX0NPTU1FTlRTIn0seyJpZCI6MjgsIm5hbWUiOiJSRUFEX0NBUl9DTEFTU0VTIiwiYXV0aG9yaXR5IjoiUkVBRF9DQVJfQ0xBU1NFUyJ9LHsiaWQiOjQwLCJuYW1lIjoiUkVBRF9QUklDRUxJU1RTIiwiYXV0aG9yaXR5IjoiUkVBRF9QUklDRUxJU1RTIn0seyJpZCI6MzAsIm5hbWUiOiJFRElUX0NBUl9DTEFTU0VTIiwiYXV0aG9yaXR5IjoiRURJVF9DQVJfQ0xBU1NFUyJ9LHsiaWQiOjksIm5hbWUiOiJFRElUX0NVU1RPTUVSUyIsImF1dGhvcml0eSI6IkVESVRfQ1VTVE9NRVJTIn0seyJpZCI6NDEsIm5hbWUiOiJQRU9QTEVfRk9SX0NIQVQiLCJhdXRob3JpdHkiOiJQRU9QTEVfRk9SX0NIQVQifSx7ImlkIjozNywibmFtZSI6IlJFQURfVFJBTlNNSVNTSU9OX1RZUEVTIiwiYXV0aG9yaXR5IjoiUkVBRF9UUkFOU01JU1NJT05fVFlQRVMifSx7ImlkIjozNiwibmFtZSI6IkVESVRfRlVFTF9UWVBFUyIsImF1dGhvcml0eSI6IkVESVRfRlVFTF9UWVBFUyJ9LHsiaWQiOjgsIm5hbWUiOiJSRUFEX0NVU1RPTUVSUyIsImF1dGhvcml0eSI6IlJFQURfQ1VTVE9NRVJTIn0seyJpZCI6MjksIm5hbWUiOiJDUkVBVEVfQ0FSX0NMQVNTRVMiLCJhdXRob3JpdHkiOiJDUkVBVEVfQ0FSX0NMQVNTRVMifSx7ImlkIjozMywibmFtZSI6IkVESVRfQ0FSX01PREVMUyIsImF1dGhvcml0eSI6IkVESVRfQ0FSX01PREVMUyJ9LHsiaWQiOjM0LCJuYW1lIjoiUkVBRF9GVUVMX1RZUEVTIiwiYXV0aG9yaXR5IjoiUkVBRF9GVUVMX1RZUEVTIn0seyJpZCI6MzgsIm5hbWUiOiJDUkVBVEVfVFJBTlNNSVNTSU9OX1RZUEVTIiwiYXV0aG9yaXR5IjoiQ1JFQVRFX1RSQU5TTUlTU0lPTl9UWVBFUyJ9LHsiaWQiOjMyLCJuYW1lIjoiQ1JFQVRFX0NBUl9NT0RFTFMiLCJhdXRob3JpdHkiOiJDUkVBVEVfQ0FSX01PREVMUyJ9LHsiaWQiOjI1LCJuYW1lIjoiUkVBRF9DQVJfQlJBTkRTIiwiYXV0aG9yaXR5IjoiUkVBRF9DQVJfQlJBTkRTIn0seyJpZCI6MywibmFtZSI6IkNPTkZJUk1fUkVHSVNUUkFUSU9OX1JFUVVFU1RTIiwiYXV0aG9yaXR5IjoiQ09ORklSTV9SRUdJU1RSQVRJT05fUkVRVUVTVFMifSx7ImlkIjoxOSwibmFtZSI6IlJFQURfQVBQUk9WRURfQ09NTUVOVFMiLCJhdXRob3JpdHkiOiJSRUFEX0FQUFJPVkVEX0NPTU1FTlRTIn0seyJpZCI6MjIsIm5hbWUiOiJBUFBST1ZFX0NPTU1FTlQiLCJhdXRob3JpdHkiOiJBUFBST1ZFX0NPTU1FTlQifSx7ImlkIjozOSwibmFtZSI6IkVESVRfVFJBTlNNSVNTSU9OX1RZUEVTIiwiYXV0aG9yaXR5IjoiRURJVF9UUkFOU01JU1NJT05fVFlQRVMifSx7ImlkIjoyNiwibmFtZSI6IkNSRUFURV9DQVJfQlJBTkRTIiwiYXV0aG9yaXR5IjoiQ1JFQVRFX0NBUl9CUkFORFMifSx7ImlkIjoyLCJuYW1lIjoiREVMRVRFX1JFR0lTVFJBVElPTl9SRVFVRVNUUyIsImF1dGhvcml0eSI6IkRFTEVURV9SRUdJU1RSQVRJT05fUkVRVUVTVFMifV19fQ.845YoOVtkkjKBOLwZVoI9TaYOlINhXcOf4fCT2EHk3Izhmrtc0luS-XG5Qb0uXADI0yXBijzRF7mJHlbvh5JnQ";
        GetAdvertisementsResponse getAdvertisementsResponse = new GetAdvertisementsResponse();
        List<AdvertisementDto> advertisementDtos = new ArrayList<>();
        try {
             advertisementDtos = this.advertisementService.getAgentAdvertisementsDtos(2L, token);
        } catch (Exception e){
            e.printStackTrace();
        }
        for(AdvertisementDto advertisementDto : advertisementDtos){
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
