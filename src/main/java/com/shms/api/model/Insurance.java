package com.shms.api.model;


import com.shms.api.dto.InsuranceDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "insurance")
@Data
@NoArgsConstructor
public class Insurance extends TrackedEntity {

    @Column(nullable = false, name = "provider_name")
    private String providerName;

    @Column(nullable = false, name = "policy_number")
    private String policyNumber;

    @Column(name = "coverage_details")
    private String coverageDetails;

    public Insurance(InsuranceDTO insuranceDTO) {
        this.id = insuranceDTO.getId();
        this.providerName = insuranceDTO.getProviderName();
        this.policyNumber = insuranceDTO.getPolicyNumber();
        this.coverageDetails = insuranceDTO.getCoverageDetails();
    }

}
