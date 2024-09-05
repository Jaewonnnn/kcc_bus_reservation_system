package com.unibus.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRouteIdVo {
    private String departureName;
    private String arrivalName;
}
