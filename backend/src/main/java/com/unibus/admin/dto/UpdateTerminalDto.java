package com.unibus.admin.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdateTerminalDto {
    private String terminalId;
    private String terminalName;
    private String address;
    private String tel;
}
