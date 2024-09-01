package com.unibus.admin.dto;

import com.unibus.admin.domain.User;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
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
