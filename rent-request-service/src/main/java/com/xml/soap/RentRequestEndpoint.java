package com.xml.soap;

import com.xml.dto.AdvertisementDto;
import com.xml.dto.RentRequestDto;
import com.xml.dto.UserDto;
import com.xml.feignClients.AdvertisementFeignClient;
import com.xml.repository.RentRequestRepository;
import com.xml.service.RentRequestService;
import localhost._8089.rent_request_service_schema.RentRequestRequest;
import localhost._8089.rent_request_service_schema.RentRequestResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Endpoint
public class RentRequestEndpoint {
    private static final String NAMESPACE_URI = "http://localhost:8089/rent-request-service-schema";

    @Autowired
    private RentRequestService rentRequestService;

    @Autowired
    private RentRequestRepository rentRequestRepository;

    @Autowired
    AdvertisementFeignClient advertisementFeignClient;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "rentRequestRequest")
    @ResponsePayload
    public RentRequestResponse createRentRequest(@RequestPayload RentRequestRequest request) throws ParseException, IOException {
        String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJic2VwIiwic3ViIjoiam92YW5qbyIsImF1ZCI6IndlYiIsImlhdCI6MTU5Mzk5NDc1NCwiZXhwIjoxNTk0MDgxMTU0LCJST0xFX1VTRVIiOnsiaWQiOjEsIm5hbWUiOiJST0xFX0FETUlOIiwicGVybWlzc2lvbnMiOlt7ImlkIjoyNiwibmFtZSI6IkNSRUFURV9DQVJfQlJBTkRTIiwiYXV0aG9yaXR5IjoiQ1JFQVRFX0NBUl9CUkFORFMifSx7ImlkIjozOCwibmFtZSI6IkNSRUFURV9UUkFOU01JU1NJT05fVFlQRVMiLCJhdXRob3JpdHkiOiJDUkVBVEVfVFJBTlNNSVNTSU9OX1RZUEVTIn0seyJpZCI6MTIsIm5hbWUiOiJSRUFEX1VTRVJfQURWRVJUSVNFTUVOVFMiLCJhdXRob3JpdHkiOiJSRUFEX1VTRVJfQURWRVJUSVNFTUVOVFMifSx7ImlkIjo0LCJuYW1lIjoiUkVBRF9VU0VSIiwiYXV0aG9yaXR5IjoiUkVBRF9VU0VSIn0seyJpZCI6MjIsIm5hbWUiOiJBUFBST1ZFX0NPTU1FTlQiLCJhdXRob3JpdHkiOiJBUFBST1ZFX0NPTU1FTlQifSx7ImlkIjozMSwibmFtZSI6IlJFQURfQ0FSX01PREVMUyIsImF1dGhvcml0eSI6IlJFQURfQ0FSX01PREVMUyJ9LHsiaWQiOjMsIm5hbWUiOiJDT05GSVJNX1JFR0lTVFJBVElPTl9SRVFVRVNUUyIsImF1dGhvcml0eSI6IkNPTkZJUk1fUkVHSVNUUkFUSU9OX1JFUVVFU1RTIn0seyJpZCI6MzAsIm5hbWUiOiJFRElUX0NBUl9DTEFTU0VTIiwiYXV0aG9yaXR5IjoiRURJVF9DQVJfQ0xBU1NFUyJ9LHsiaWQiOjI4LCJuYW1lIjoiUkVBRF9DQVJfQ0xBU1NFUyIsImF1dGhvcml0eSI6IlJFQURfQ0FSX0NMQVNTRVMifSx7ImlkIjo0MCwibmFtZSI6IlJFQURfUFJJQ0VMSVNUUyIsImF1dGhvcml0eSI6IlJFQURfUFJJQ0VMSVNUUyJ9LHsiaWQiOjE4LCJuYW1lIjoiUkVBRF9DT01NRU5UUyIsImF1dGhvcml0eSI6IlJFQURfQ09NTUVOVFMifSx7ImlkIjozNSwibmFtZSI6IkNSRUFURV9GVUVMX1RZUEVTIiwiYXV0aG9yaXR5IjoiQ1JFQVRFX0ZVRUxfVFlQRVMifSx7ImlkIjozOSwibmFtZSI6IkVESVRfVFJBTlNNSVNTSU9OX1RZUEVTIiwiYXV0aG9yaXR5IjoiRURJVF9UUkFOU01JU1NJT05fVFlQRVMifSx7ImlkIjo1LCJuYW1lIjoiV0hPX0FNX0kiLCJhdXRob3JpdHkiOiJXSE9fQU1fSSJ9LHsiaWQiOjExLCJuYW1lIjoiUkVBRF9BRFZFUlRJU0VNRU5UUyIsImF1dGhvcml0eSI6IlJFQURfQURWRVJUSVNFTUVOVFMifSx7ImlkIjoyLCJuYW1lIjoiREVMRVRFX1JFR0lTVFJBVElPTl9SRVFVRVNUUyIsImF1dGhvcml0eSI6IkRFTEVURV9SRUdJU1RSQVRJT05fUkVRVUVTVFMifSx7ImlkIjo5LCJuYW1lIjoiRURJVF9DVVNUT01FUlMiLCJhdXRob3JpdHkiOiJFRElUX0NVU1RPTUVSUyJ9LHsiaWQiOjMzLCJuYW1lIjoiRURJVF9DQVJfTU9ERUxTIiwiYXV0aG9yaXR5IjoiRURJVF9DQVJfTU9ERUxTIn0seyJpZCI6MjcsIm5hbWUiOiJFRElUX0NBUl9CUkFORFMiLCJhdXRob3JpdHkiOiJFRElUX0NBUl9CUkFORFMifSx7ImlkIjo0MSwibmFtZSI6IlBFT1BMRV9GT1JfQ0hBVCIsImF1dGhvcml0eSI6IlBFT1BMRV9GT1JfQ0hBVCJ9LHsiaWQiOjgsIm5hbWUiOiJSRUFEX0NVU1RPTUVSUyIsImF1dGhvcml0eSI6IlJFQURfQ1VTVE9NRVJTIn0seyJpZCI6MjUsIm5hbWUiOiJSRUFEX0NBUl9CUkFORFMiLCJhdXRob3JpdHkiOiJSRUFEX0NBUl9CUkFORFMifSx7ImlkIjoyOSwibmFtZSI6IkNSRUFURV9DQVJfQ0xBU1NFUyIsImF1dGhvcml0eSI6IkNSRUFURV9DQVJfQ0xBU1NFUyJ9LHsiaWQiOjM3LCJuYW1lIjoiUkVBRF9UUkFOU01JU1NJT05fVFlQRVMiLCJhdXRob3JpdHkiOiJSRUFEX1RSQU5TTUlTU0lPTl9UWVBFUyJ9LHsiaWQiOjEwLCJuYW1lIjoiQ1JFQVRFX0FHRU5UUyIsImF1dGhvcml0eSI6IkNSRUFURV9BR0VOVFMifSx7ImlkIjoyMywibmFtZSI6IkRFQ0xJTkVfQ09NTUVOVCIsImF1dGhvcml0eSI6IkRFQ0xJTkVfQ09NTUVOVCJ9LHsiaWQiOjM2LCJuYW1lIjoiRURJVF9GVUVMX1RZUEVTIiwiYXV0aG9yaXR5IjoiRURJVF9GVUVMX1RZUEVTIn0seyJpZCI6MTksIm5hbWUiOiJSRUFEX0FQUFJPVkVEX0NPTU1FTlRTIiwiYXV0aG9yaXR5IjoiUkVBRF9BUFBST1ZFRF9DT01NRU5UUyJ9LHsiaWQiOjMyLCJuYW1lIjoiQ1JFQVRFX0NBUl9NT0RFTFMiLCJhdXRob3JpdHkiOiJDUkVBVEVfQ0FSX01PREVMUyJ9LHsiaWQiOjEsIm5hbWUiOiJSRUFEX1JFR0lTVFJBVElPTl9SRVFVRVNUUyIsImF1dGhvcml0eSI6IlJFQURfUkVHSVNUUkFUSU9OX1JFUVVFU1RTIn0seyJpZCI6MzQsIm5hbWUiOiJSRUFEX0ZVRUxfVFlQRVMiLCJhdXRob3JpdHkiOiJSRUFEX0ZVRUxfVFlQRVMifV19fQ.X6oXyp3SQYlC5Id71JbV3Gz5Sc82uVpKtOkZH2prd1IcvFWoVy2WlLONed5ZLdft7H-t9NCeU3lhNvWt7bYMfw";
        System.out.println("SOAP - Create Rent Request");
        System.out.println("IDs: " + request.getAdvertisementsForRent());
        RentRequestResponse response = new RentRequestResponse();
        RentRequestDto rentRequestDto = new RentRequestDto();

        rentRequestDto.setReservedFrom(LocalDateTime.parse(request.getReservedFrom()));
        rentRequestDto.setReservedTo(LocalDateTime.parse(request.getReservedTo()));
        Set<AdvertisementDto> advertisements = new HashSet<>();
        List<Long> advertisementsIds = request.getAdvertisementsForRent();
        for(Long id : advertisementsIds){
            advertisements.add(this.advertisementFeignClient.getOne(id, token));
        }

        rentRequestDto.setAdvertisementsForRent(advertisements);

        UserDto userDto = new UserDto();
        userDto.setType(request.getUser().getType());
        userDto.setFirstName(request.getUser().getFirstName());
        userDto.setLastName(request.getUser().getLastName());
        userDto.setCountry(request.getUser().getCountry());
        userDto.setCity(request.getUser().getCity());
        userDto.setAddress(request.getUser().getEmail());
        userDto.setEmail(request.getUser().getEmail());
        userDto.setPhone(request.getUser().getPhone());

        rentRequestDto.setCustomer(userDto);

        this.rentRequestService.createRentRequest(rentRequestDto, token);

        return response;
    }

}
