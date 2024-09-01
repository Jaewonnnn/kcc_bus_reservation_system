package com.unibus.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CompanyDto {
    private int companyId;
    private String companyName;
    private String companyTel;
}
