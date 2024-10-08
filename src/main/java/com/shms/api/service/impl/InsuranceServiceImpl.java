package com.shms.api.service.impl;


import com.shms.api.dao.InsuranceRepository;
import com.shms.api.dto.InsuranceDTO;
import com.shms.api.model.Insurance;
import com.shms.api.service.InsuranceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InsuranceServiceImpl implements InsuranceService {

    private final InsuranceRepository insuranceRepository;

    @Override
    public Insurance create(InsuranceDTO insuranceDTO) {
        Insurance insurance = new Insurance(insuranceDTO);
        return insuranceRepository.save(insurance);
    }

    @Override
    public void update(Insurance insurance, InsuranceDTO insuranceDTO) {

    }

    @Override
    public Insurance getById(String id) {
        return null;
    }

    @Override
    public List<Insurance> getAll(List<InsuranceDTO> insurances) {
        return List.of();
    }
}
