package com.unibus.admin.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private int memberSeq;
    private String memberId;
    private String memberPass;
    private String memberName;
    private String memberTel;
    private String memberEmail;
    private Date memberBirth;
    private String memberRole;
    private Date createDate;
    private boolean withdraw;
}
