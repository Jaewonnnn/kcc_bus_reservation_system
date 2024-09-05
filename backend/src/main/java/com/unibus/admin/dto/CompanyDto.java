package com.unibus.admin.dto;

import com.unibus.admin.domain.Bus;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CompanyDto {
    private int companyId;
    private String companyName;
    private String companyTel;
}