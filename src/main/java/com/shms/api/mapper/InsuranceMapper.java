package com.shms.api.mapper;

import com.shms.api.dto.InsuranceDTO;
import com.shms.api.model.Insurance;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface InsuranceMapper {
    InsuranceDTO map(Insurance insurance);

    List<InsuranceDTO> map(List<Insurance> insurances);
}
