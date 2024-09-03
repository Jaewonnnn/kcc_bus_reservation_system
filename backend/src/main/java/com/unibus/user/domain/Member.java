package com.unibus.user.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    private Integer memberSeq;
    private String memberId;
    private String memberPass;
    private String memberName;
    private String memberTel;
    private String memberEmail;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date memberBirth;
    private String memberRole;
    private Date createDate;
    private boolean withdraw;
}
