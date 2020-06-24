package com.xml.controller;

import com.xml.dto.FuelTypeDto;
import com.xml.dto.TransmissionTypeDto;
import com.xml.mapper.TransmissionTypeDtoMapper;
import com.xml.service.TransmissionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(value = "https://localhost:4200")
@RequestMapping(value = "/api/transmission-types", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransmissionTypeController {

    @Autowired
    private TransmissionTypeService transmissionTypeService;

    @Autowired
    private TransmissionTypeDtoMapper transmissionTypeDtoMapper;

    @GetMapping(value = "")
    @PreAuthorize("hasAuthority('READ_TRANSMISSION_TYPES')")
    public ResponseEntity<List<TransmissionTypeDto>> getAll() {
        try {
            List<TransmissionTypeDto> transmissionTypeDtos = this.transmissionTypeService.getAll().stream()
                    .map(transmissionTypeDtoMapper::toDto).collect(Collectors.toList());
            return new ResponseEntity<>(transmissionTypeDtos, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "")
    @PreAuthorize("hasAuthority('CREATE_TRANSMISSION_TYPES')")
    public ResponseEntity<?> addTransmissionType(@Valid @RequestBody TransmissionTypeDto transmissionTypeDto) {
        System.out.println("Stampa: " + transmissionTypeDto.getName());
        try {
            this.transmissionTypeService.saveTransmissionType(transmissionTypeDto);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('EDIT_TRANSMISSION_TYPES')")
    public ResponseEntity<?> deleteTransmissionType(@PathVariable Long id) throws ParseException {

        try {
            this.transmissionTypeService.deleteTransmissionType(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "")
    @PreAuthorize("hasAuthority('EDIT_TRANSMISSION_TYPES')")
    public ResponseEntity<?> editTransmissionType(@Valid @RequestBody TransmissionTypeDto transmissionTypeDto) {
        System.out.println(transmissionTypeDto.toString());
        try {
            this.transmissionTypeService.editTransmissionType(transmissionTypeDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Transmission type already exists.", HttpStatus.BAD_REQUEST);
        }
    }
}
