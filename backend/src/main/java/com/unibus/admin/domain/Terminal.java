package com.unibus.admin.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Terminal {
    private String terminalId;
    private int cityId;
    private String terminalName;
    private String address;
    private String tel;
    private boolean terminalStatus;
}
