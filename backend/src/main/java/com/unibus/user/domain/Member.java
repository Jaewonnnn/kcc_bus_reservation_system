package com.unibus.user.domain;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    private Integer memberSeq;
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
