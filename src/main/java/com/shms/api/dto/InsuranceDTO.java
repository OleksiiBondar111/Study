package com.shms.api.dto;

import com.shms.api.model.Insurance;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class InsuranceDTO extends EntityDTO {

    @NotNull
    private String providerName;

    @NotNull
    private String policyNumber;

    @NotNull
    private String coverageDetails;

    public InsuranceDTO(Insurance insurance) {
        this.id = insurance.getId();
        this.providerName = insurance.getProviderName();
        this.policyNumber = insurance.getPolicyNumber();
        this.coverageDetails = insurance.getCoverageDetails();
        this.lastModified = insurance.getLastModified();
        this.createdAt = insurance.getCreatedAt();
    }
}
