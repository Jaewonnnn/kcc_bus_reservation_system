package com.unibus.admin.dto;

import com.unibus.admin.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDto {
    private int id;
    private String name;
    private String phoneNumber;
    private String email;

    public static UserDto toUserDto(User user) {
        return UserDto.builder()
                .id(user.getMemberSeq())
                .name(user.getMemberName())
                .phoneNumber(user.getMemberTel())
                .email(user.getMemberEmail())
                .build();
    }
}
