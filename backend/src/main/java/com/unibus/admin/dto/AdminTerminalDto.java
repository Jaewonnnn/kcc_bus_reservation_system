package com.unibus.admin.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class AdminTerminalDto {
    private String terminalId;
    private int cityId;
    private String terminalName;
    private String address;
    private String tel;
}
