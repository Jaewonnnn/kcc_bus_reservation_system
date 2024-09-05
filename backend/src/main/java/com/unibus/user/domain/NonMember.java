package com.unibus.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NonMember {
    private int nonUserCode;
    private String nonUserTel;
}
