package com.unibus.user.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unibus.user.domain.Member;
import com.unibus.user.service.UserService;

import lombok.RequiredArgsConstructor;
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/*")
public class UserController {
    private final UserService userService;

    @GetMapping("/{user_id}")
    public ResponseEntity<Member> getUserInfo(@PathVariable("user_id") String memberId) {
        Member member = userService.userInfo(memberId);
        return member == null ? new ResponseEntity<>(member, HttpStatus.INTERNAL_SERVER_ERROR) :
                new ResponseEntity<>(member, HttpStatus.OK);
    }

    @PatchMapping("/{user_id}")
    public ResponseEntity<Integer> changeUserInfo(@RequestBody Member member,
                                                  @PathVariable("user_id") String memberId) {
        Member member2 = userService.userInfo(memberId);
        member2.setMemberEmail(member.getMemberEmail());
        member2.setMemberName(member.getMemberName());
        member2.setMemberTel(member.getMemberTel());
        int flag = userService.updateMember(member2);
        return flag > 0 ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/withdraw")
    public ResponseEntity<Integer> withdrawUser(@RequestBody Member member) {
        return userService.updateWithdraw(member) > 0 ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/check/{user_id}")
    public ResponseEntity<Boolean> checkUserId(@PathVariable("user_id") String memberId) {
        boolean flag = userService.checkMemberId(memberId);
        return flag == false ? new ResponseEntity<>(false, HttpStatus.CONFLICT)
                : new ResponseEntity<>(true, HttpStatus.OK);
    }
}
