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
        String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJic2VwIiwic3ViIjoibWFyaWNtIiwiYXVkIjoid2ViIiwiaWF0IjoxNTk0NDk5OTQzLCJleHAiOjE1OTQ1ODYzNDMsIlJPTEVfVVNFUiI6eyJpZCI6MiwibmFtZSI6IlJPTEVfQUdFTlQiLCJwZXJtaXNzaW9ucyI6W3siaWQiOjQ1LCJuYW1lIjoiUkVBRF9SRU5UX1JFUVVFU1RTIiwiYXV0aG9yaXR5IjoiUkVBRF9SRU5UX1JFUVVFU1RTIn0seyJpZCI6NCwibmFtZSI6IlJFQURfVVNFUiIsImF1dGhvcml0eSI6IlJFQURfVVNFUiJ9LHsiaWQiOjE5LCJuYW1lIjoiUkVBRF9BUFBST1ZFRF9DT01NRU5UUyIsImF1dGhvcml0eSI6IlJFQURfQVBQUk9WRURfQ09NTUVOVFMifSx7ImlkIjoyOCwibmFtZSI6IlJFQURfQ0FSX0NMQVNTRVMiLCJhdXRob3JpdHkiOiJSRUFEX0NBUl9DTEFTU0VTIn0seyJpZCI6NDAsIm5hbWUiOiJSRUFEX1BSSUNFTElTVFMiLCJhdXRob3JpdHkiOiJSRUFEX1BSSUNFTElTVFMifSx7ImlkIjoyMSwibmFtZSI6IkNSRUFURV9DT01NRU5UIiwiYXV0aG9yaXR5IjoiQ1JFQVRFX0NPTU1FTlQifSx7ImlkIjozMSwibmFtZSI6IlJFQURfQ0FSX01PREVMUyIsImF1dGhvcml0eSI6IlJFQURfQ0FSX01PREVMUyJ9LHsiaWQiOjQyLCJuYW1lIjoiUkVBRF9NRVNTQUdFUyIsImF1dGhvcml0eSI6IlJFQURfTUVTU0FHRVMifSx7ImlkIjo0NiwibmFtZSI6IkVESVRfUkVOVF9SRVFVRVNUUyIsImF1dGhvcml0eSI6IkVESVRfUkVOVF9SRVFVRVNUUyJ9LHsiaWQiOjQxLCJuYW1lIjoiUEVPUExFX0ZPUl9DSEFUIiwiYXV0aG9yaXR5IjoiUEVPUExFX0ZPUl9DSEFUIn0seyJpZCI6NDMsIm5hbWUiOiJDUkVBVEVfTUVTU0FHRSIsImF1dGhvcml0eSI6IkNSRUFURV9NRVNTQUdFIn0seyJpZCI6MTQsIm5hbWUiOiJVUExPQURfUEhPVE9TIiwiYXV0aG9yaXR5IjoiVVBMT0FEX1BIT1RPUyJ9LHsiaWQiOjEzLCJuYW1lIjoiQ1JFQVRFX0FEVkVSVElTRU1FTlRTIiwiYXV0aG9yaXR5IjoiQ1JFQVRFX0FEVkVSVElTRU1FTlRTIn0seyJpZCI6MTYsIm5hbWUiOiJCQVNJQ19TRUFSQ0hfVVNFUiIsImF1dGhvcml0eSI6IkJBU0lDX1NFQVJDSF9VU0VSIn0seyJpZCI6MzcsIm5hbWUiOiJSRUFEX1RSQU5TTUlTU0lPTl9UWVBFUyIsImF1dGhvcml0eSI6IlJFQURfVFJBTlNNSVNTSU9OX1RZUEVTIn0seyJpZCI6MTIsIm5hbWUiOiJSRUFEX1VTRVJfQURWRVJUSVNFTUVOVFMiLCJhdXRob3JpdHkiOiJSRUFEX1VTRVJfQURWRVJUSVNFTUVOVFMifSx7ImlkIjoxMSwibmFtZSI6IlJFQURfQURWRVJUSVNFTUVOVFMiLCJhdXRob3JpdHkiOiJSRUFEX0FEVkVSVElTRU1FTlRTIn0seyJpZCI6MTUsIm5hbWUiOiJCQVNJQ19TRUFSQ0giLCJhdXRob3JpdHkiOiJCQVNJQ19TRUFSQ0gifSx7ImlkIjoyNSwibmFtZSI6IlJFQURfQ0FSX0JSQU5EUyIsImF1dGhvcml0eSI6IlJFQURfQ0FSX0JSQU5EUyJ9LHsiaWQiOjUsIm5hbWUiOiJXSE9fQU1fSSIsImF1dGhvcml0eSI6IldIT19BTV9JIn0seyJpZCI6NywibmFtZSI6IkNSRUFURV9QSFlTSUNBTF9VU0VSIiwiYXV0aG9yaXR5IjoiQ1JFQVRFX1BIWVNJQ0FMX1VTRVIifSx7ImlkIjoyMCwibmFtZSI6IlJFUExZX0NPTU1FTlQiLCJhdXRob3JpdHkiOiJSRVBMWV9DT01NRU5UIn0seyJpZCI6NDQsIm5hbWUiOiJDUkVBVEVfUkVOVF9SRVFVRVNUUyIsImF1dGhvcml0eSI6IkNSRUFURV9SRU5UX1JFUVVFU1RTIn0seyJpZCI6MzQsIm5hbWUiOiJSRUFEX0ZVRUxfVFlQRVMiLCJhdXRob3JpdHkiOiJSRUFEX0ZVRUxfVFlQRVMifV19fQ.uvAOe8C6wPKrPMznzUcGlZ4Vny3T1Xu4JdkQyFejqYlttHkBX418s5g3cjvWDpWaq6IKwMecGIKGAcumr-Tyxg";
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
