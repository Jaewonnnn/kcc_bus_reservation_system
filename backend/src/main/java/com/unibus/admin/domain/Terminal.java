package com.unibus.admin.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Terminal {
    private String terminalId;
    private String cityId;
    private String terminalName;
    private String address;
    private String tel;
    private boolean terminalStatus;
}
