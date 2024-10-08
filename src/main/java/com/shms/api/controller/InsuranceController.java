package com.shms.api.controller;


import com.shms.api.dto.InsuranceDTO;
import com.shms.api.mapper.InsuranceMapper;
import com.shms.api.service.InsuranceService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/insurance")
public class InsuranceController {
    
    private final InsuranceMapper insuranceMapper;
    private final InsuranceService insuranceService;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InsuranceDTO create(@RequestBody @Valid InsuranceDTO departmentDTO) {
        return insuranceMapper.map(insuranceService.create(departmentDTO));
    }

}
