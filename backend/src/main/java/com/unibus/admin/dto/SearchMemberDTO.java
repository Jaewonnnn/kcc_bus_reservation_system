package com.unibus.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchMemberDTO {
    private int memberSeq;
    private String memberId;
    private String memberName;
    private String memberTel;
    private String memberEmail;
    private Date memberBirth;
    private String memberRole;
    private Date createDate;
    private int withdraw;
}
