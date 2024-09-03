package com.unibus.reservation.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Terminal {
    private String terminalId;
    private String terminalName;
    private int cityId;
    private String address;
    private String tel;
    private int terminalStatus;
}
