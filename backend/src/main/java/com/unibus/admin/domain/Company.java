package com.unibus.admin.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Company {
    private int companyId;
    private String companyName;
    private String companyTel;
}
