package com.unibus.admin.dto;

import com.unibus.admin.domain.Bus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {
    private int companyId;
    private String companyName;
    private String companyTel;
    private List<Bus> busList;
}