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

        String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJic2VwIiwic3ViIjoiam92YW5qbyIsImF1ZCI6IndlYiIsImlhdCI6MTU5Mzk5NDc1NCwiZXhwIjoxNTk0MDgxMTU0LCJST0xFX1VTRVIiOnsiaWQiOjEsIm5hbWUiOiJST0xFX0FETUlOIiwicGVybWlzc2lvbnMiOlt7ImlkIjoyNiwibmFtZSI6IkNSRUFURV9DQVJfQlJBTkRTIiwiYXV0aG9yaXR5IjoiQ1JFQVRFX0NBUl9CUkFORFMifSx7ImlkIjozOCwibmFtZSI6IkNSRUFURV9UUkFOU01JU1NJT05fVFlQRVMiLCJhdXRob3JpdHkiOiJDUkVBVEVfVFJBTlNNSVNTSU9OX1RZUEVTIn0seyJpZCI6MTIsIm5hbWUiOiJSRUFEX1VTRVJfQURWRVJUSVNFTUVOVFMiLCJhdXRob3JpdHkiOiJSRUFEX1VTRVJfQURWRVJUSVNFTUVOVFMifSx7ImlkIjo0LCJuYW1lIjoiUkVBRF9VU0VSIiwiYXV0aG9yaXR5IjoiUkVBRF9VU0VSIn0seyJpZCI6MjIsIm5hbWUiOiJBUFBST1ZFX0NPTU1FTlQiLCJhdXRob3JpdHkiOiJBUFBST1ZFX0NPTU1FTlQifSx7ImlkIjozMSwibmFtZSI6IlJFQURfQ0FSX01PREVMUyIsImF1dGhvcml0eSI6IlJFQURfQ0FSX01PREVMUyJ9LHsiaWQiOjMsIm5hbWUiOiJDT05GSVJNX1JFR0lTVFJBVElPTl9SRVFVRVNUUyIsImF1dGhvcml0eSI6IkNPTkZJUk1fUkVHSVNUUkFUSU9OX1JFUVVFU1RTIn0seyJpZCI6MzAsIm5hbWUiOiJFRElUX0NBUl9DTEFTU0VTIiwiYXV0aG9yaXR5IjoiRURJVF9DQVJfQ0xBU1NFUyJ9LHsiaWQiOjI4LCJuYW1lIjoiUkVBRF9DQVJfQ0xBU1NFUyIsImF1dGhvcml0eSI6IlJFQURfQ0FSX0NMQVNTRVMifSx7ImlkIjo0MCwibmFtZSI6IlJFQURfUFJJQ0VMSVNUUyIsImF1dGhvcml0eSI6IlJFQURfUFJJQ0VMSVNUUyJ9LHsiaWQiOjE4LCJuYW1lIjoiUkVBRF9DT01NRU5UUyIsImF1dGhvcml0eSI6IlJFQURfQ09NTUVOVFMifSx7ImlkIjozNSwibmFtZSI6IkNSRUFURV9GVUVMX1RZUEVTIiwiYXV0aG9yaXR5IjoiQ1JFQVRFX0ZVRUxfVFlQRVMifSx7ImlkIjozOSwibmFtZSI6IkVESVRfVFJBTlNNSVNTSU9OX1RZUEVTIiwiYXV0aG9yaXR5IjoiRURJVF9UUkFOU01JU1NJT05fVFlQRVMifSx7ImlkIjo1LCJuYW1lIjoiV0hPX0FNX0kiLCJhdXRob3JpdHkiOiJXSE9fQU1fSSJ9LHsiaWQiOjExLCJuYW1lIjoiUkVBRF9BRFZFUlRJU0VNRU5UUyIsImF1dGhvcml0eSI6IlJFQURfQURWRVJUSVNFTUVOVFMifSx7ImlkIjoyLCJuYW1lIjoiREVMRVRFX1JFR0lTVFJBVElPTl9SRVFVRVNUUyIsImF1dGhvcml0eSI6IkRFTEVURV9SRUdJU1RSQVRJT05fUkVRVUVTVFMifSx7ImlkIjo5LCJuYW1lIjoiRURJVF9DVVNUT01FUlMiLCJhdXRob3JpdHkiOiJFRElUX0NVU1RPTUVSUyJ9LHsiaWQiOjMzLCJuYW1lIjoiRURJVF9DQVJfTU9ERUxTIiwiYXV0aG9yaXR5IjoiRURJVF9DQVJfTU9ERUxTIn0seyJpZCI6MjcsIm5hbWUiOiJFRElUX0NBUl9CUkFORFMiLCJhdXRob3JpdHkiOiJFRElUX0NBUl9CUkFORFMifSx7ImlkIjo0MSwibmFtZSI6IlBFT1BMRV9GT1JfQ0hBVCIsImF1dGhvcml0eSI6IlBFT1BMRV9GT1JfQ0hBVCJ9LHsiaWQiOjgsIm5hbWUiOiJSRUFEX0NVU1RPTUVSUyIsImF1dGhvcml0eSI6IlJFQURfQ1VTVE9NRVJTIn0seyJpZCI6MjUsIm5hbWUiOiJSRUFEX0NBUl9CUkFORFMiLCJhdXRob3JpdHkiOiJSRUFEX0NBUl9CUkFORFMifSx7ImlkIjoyOSwibmFtZSI6IkNSRUFURV9DQVJfQ0xBU1NFUyIsImF1dGhvcml0eSI6IkNSRUFURV9DQVJfQ0xBU1NFUyJ9LHsiaWQiOjM3LCJuYW1lIjoiUkVBRF9UUkFOU01JU1NJT05fVFlQRVMiLCJhdXRob3JpdHkiOiJSRUFEX1RSQU5TTUlTU0lPTl9UWVBFUyJ9LHsiaWQiOjEwLCJuYW1lIjoiQ1JFQVRFX0FHRU5UUyIsImF1dGhvcml0eSI6IkNSRUFURV9BR0VOVFMifSx7ImlkIjoyMywibmFtZSI6IkRFQ0xJTkVfQ09NTUVOVCIsImF1dGhvcml0eSI6IkRFQ0xJTkVfQ09NTUVOVCJ9LHsiaWQiOjM2LCJuYW1lIjoiRURJVF9GVUVMX1RZUEVTIiwiYXV0aG9yaXR5IjoiRURJVF9GVUVMX1RZUEVTIn0seyJpZCI6MTksIm5hbWUiOiJSRUFEX0FQUFJPVkVEX0NPTU1FTlRTIiwiYXV0aG9yaXR5IjoiUkVBRF9BUFBST1ZFRF9DT01NRU5UUyJ9LHsiaWQiOjMyLCJuYW1lIjoiQ1JFQVRFX0NBUl9NT0RFTFMiLCJhdXRob3JpdHkiOiJDUkVBVEVfQ0FSX01PREVMUyJ9LHsiaWQiOjEsIm5hbWUiOiJSRUFEX1JFR0lTVFJBVElPTl9SRVFVRVNUUyIsImF1dGhvcml0eSI6IlJFQURfUkVHSVNUUkFUSU9OX1JFUVVFU1RTIn0seyJpZCI6MzQsIm5hbWUiOiJSRUFEX0ZVRUxfVFlQRVMiLCJhdXRob3JpdHkiOiJSRUFEX0ZVRUxfVFlQRVMifV19fQ.X6oXyp3SQYlC5Id71JbV3Gz5Sc82uVpKtOkZH2prd1IcvFWoVy2WlLONed5ZLdft7H-t9NCeU3lhNvWt7bYMfw";
        GetAdvertisementsResponse getAdvertisementsResponse = new GetAdvertisementsResponse();
        System.out.println("OS PUCI");
        List<AdvertisementDto> advertisementDtos = this.advertisementService.getAgentAdvertisementsDtos(2L, token);
        System.out.println("OS NARAVNO");
        for(AdvertisementDto advertisementDto : advertisementDtos){
            Advertisement advertisement = new Advertisement();
            advertisement.setAdvertiserId(advertisementDto.getAdvertiser().getId());
            advertisement.setAvailableFrom(advertisementDto.getAvailableFrom().toString());
            advertisement.setAvailableTo(advertisementDto.getAvailableTo().toString());
            advertisement.setPricelistId(advertisementDto.getPricelist().getId());

            com.xml.soap.code.Car car = new com.xml.soap.code.Car();
            car.setCarBrandId(advertisementDto.getCar().getCarBrand().getId());
            car.setCarModelId(advertisementDto.getCar().getCarModel().getId());
            car.setCarClassId(advertisementDto.getCar().getCarClass().getId());
            car.setTransmissionTypeId(advertisementDto.getCar().getTransmissionType().getId());
            car.setCollisionDamageWaiverExists(advertisementDto.getCar().isCollisionDamageWaiverExists());
            car.setHasAndroid(advertisementDto.getCar().isHasAndroid());
            car.setMileage(advertisementDto.getCar().getMileage());
            car.setAllowedDistance(advertisementDto.getCar().getAllowedDistance());
            car.setAverageRating(advertisementDto.getCar().getAverageRating());
            car.setTimesRated(advertisementDto.getCar().getTimesRated());

            advertisement.setCar(car);

            getAdvertisementsResponse.getAdvertisement().add(advertisement);
        }

        System.out.println("Zavrsio. :)");
        return getAdvertisementsResponse;

    }

}
