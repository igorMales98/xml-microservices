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
        String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJic2VwIiwic3ViIjoiam92YW5qbyIsImF1ZCI6IndlYiIsImlhdCI6MTU5NDQwNDk0MSwiZXhwIjoxNTk0NDkxMzQxLCJST0xFX1VTRVIiOnsiaWQiOjEsIm5hbWUiOiJST0xFX0FETUlOIiwicGVybWlzc2lvbnMiOlt7ImlkIjo0LCJuYW1lIjoiUkVBRF9VU0VSIiwiYXV0aG9yaXR5IjoiUkVBRF9VU0VSIn0seyJpZCI6MzUsIm5hbWUiOiJDUkVBVEVfRlVFTF9UWVBFUyIsImF1dGhvcml0eSI6IkNSRUFURV9GVUVMX1RZUEVTIn0seyJpZCI6MTEsIm5hbWUiOiJSRUFEX0FEVkVSVElTRU1FTlRTIiwiYXV0aG9yaXR5IjoiUkVBRF9BRFZFUlRJU0VNRU5UUyJ9LHsiaWQiOjEwLCJuYW1lIjoiQ1JFQVRFX0FHRU5UUyIsImF1dGhvcml0eSI6IkNSRUFURV9BR0VOVFMifSx7ImlkIjoyMywibmFtZSI6IkRFQ0xJTkVfQ09NTUVOVCIsImF1dGhvcml0eSI6IkRFQ0xJTkVfQ09NTUVOVCJ9LHsiaWQiOjMxLCJuYW1lIjoiUkVBRF9DQVJfTU9ERUxTIiwiYXV0aG9yaXR5IjoiUkVBRF9DQVJfTU9ERUxTIn0seyJpZCI6MSwibmFtZSI6IlJFQURfUkVHSVNUUkFUSU9OX1JFUVVFU1RTIiwiYXV0aG9yaXR5IjoiUkVBRF9SRUdJU1RSQVRJT05fUkVRVUVTVFMifSx7ImlkIjoxMiwibmFtZSI6IlJFQURfVVNFUl9BRFZFUlRJU0VNRU5UUyIsImF1dGhvcml0eSI6IlJFQURfVVNFUl9BRFZFUlRJU0VNRU5UUyJ9LHsiaWQiOjUsIm5hbWUiOiJXSE9fQU1fSSIsImF1dGhvcml0eSI6IldIT19BTV9JIn0seyJpZCI6MjcsIm5hbWUiOiJFRElUX0NBUl9CUkFORFMiLCJhdXRob3JpdHkiOiJFRElUX0NBUl9CUkFORFMifSx7ImlkIjoxOCwibmFtZSI6IlJFQURfQ09NTUVOVFMiLCJhdXRob3JpdHkiOiJSRUFEX0NPTU1FTlRTIn0seyJpZCI6MjgsIm5hbWUiOiJSRUFEX0NBUl9DTEFTU0VTIiwiYXV0aG9yaXR5IjoiUkVBRF9DQVJfQ0xBU1NFUyJ9LHsiaWQiOjQwLCJuYW1lIjoiUkVBRF9QUklDRUxJU1RTIiwiYXV0aG9yaXR5IjoiUkVBRF9QUklDRUxJU1RTIn0seyJpZCI6MzAsIm5hbWUiOiJFRElUX0NBUl9DTEFTU0VTIiwiYXV0aG9yaXR5IjoiRURJVF9DQVJfQ0xBU1NFUyJ9LHsiaWQiOjksIm5hbWUiOiJFRElUX0NVU1RPTUVSUyIsImF1dGhvcml0eSI6IkVESVRfQ1VTVE9NRVJTIn0seyJpZCI6NDEsIm5hbWUiOiJQRU9QTEVfRk9SX0NIQVQiLCJhdXRob3JpdHkiOiJQRU9QTEVfRk9SX0NIQVQifSx7ImlkIjozNywibmFtZSI6IlJFQURfVFJBTlNNSVNTSU9OX1RZUEVTIiwiYXV0aG9yaXR5IjoiUkVBRF9UUkFOU01JU1NJT05fVFlQRVMifSx7ImlkIjozNiwibmFtZSI6IkVESVRfRlVFTF9UWVBFUyIsImF1dGhvcml0eSI6IkVESVRfRlVFTF9UWVBFUyJ9LHsiaWQiOjgsIm5hbWUiOiJSRUFEX0NVU1RPTUVSUyIsImF1dGhvcml0eSI6IlJFQURfQ1VTVE9NRVJTIn0seyJpZCI6MjksIm5hbWUiOiJDUkVBVEVfQ0FSX0NMQVNTRVMiLCJhdXRob3JpdHkiOiJDUkVBVEVfQ0FSX0NMQVNTRVMifSx7ImlkIjozMywibmFtZSI6IkVESVRfQ0FSX01PREVMUyIsImF1dGhvcml0eSI6IkVESVRfQ0FSX01PREVMUyJ9LHsiaWQiOjM0LCJuYW1lIjoiUkVBRF9GVUVMX1RZUEVTIiwiYXV0aG9yaXR5IjoiUkVBRF9GVUVMX1RZUEVTIn0seyJpZCI6MzgsIm5hbWUiOiJDUkVBVEVfVFJBTlNNSVNTSU9OX1RZUEVTIiwiYXV0aG9yaXR5IjoiQ1JFQVRFX1RSQU5TTUlTU0lPTl9UWVBFUyJ9LHsiaWQiOjMyLCJuYW1lIjoiQ1JFQVRFX0NBUl9NT0RFTFMiLCJhdXRob3JpdHkiOiJDUkVBVEVfQ0FSX01PREVMUyJ9LHsiaWQiOjI1LCJuYW1lIjoiUkVBRF9DQVJfQlJBTkRTIiwiYXV0aG9yaXR5IjoiUkVBRF9DQVJfQlJBTkRTIn0seyJpZCI6MywibmFtZSI6IkNPTkZJUk1fUkVHSVNUUkFUSU9OX1JFUVVFU1RTIiwiYXV0aG9yaXR5IjoiQ09ORklSTV9SRUdJU1RSQVRJT05fUkVRVUVTVFMifSx7ImlkIjoxOSwibmFtZSI6IlJFQURfQVBQUk9WRURfQ09NTUVOVFMiLCJhdXRob3JpdHkiOiJSRUFEX0FQUFJPVkVEX0NPTU1FTlRTIn0seyJpZCI6MjIsIm5hbWUiOiJBUFBST1ZFX0NPTU1FTlQiLCJhdXRob3JpdHkiOiJBUFBST1ZFX0NPTU1FTlQifSx7ImlkIjozOSwibmFtZSI6IkVESVRfVFJBTlNNSVNTSU9OX1RZUEVTIiwiYXV0aG9yaXR5IjoiRURJVF9UUkFOU01JU1NJT05fVFlQRVMifSx7ImlkIjoyNiwibmFtZSI6IkNSRUFURV9DQVJfQlJBTkRTIiwiYXV0aG9yaXR5IjoiQ1JFQVRFX0NBUl9CUkFORFMifSx7ImlkIjoyLCJuYW1lIjoiREVMRVRFX1JFR0lTVFJBVElPTl9SRVFVRVNUUyIsImF1dGhvcml0eSI6IkRFTEVURV9SRUdJU1RSQVRJT05fUkVRVUVTVFMifV19fQ.845YoOVtkkjKBOLwZVoI9TaYOlINhXcOf4fCT2EHk3Izhmrtc0luS-XG5Qb0uXADI0yXBijzRF7mJHlbvh5JnQ";
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
