package com.practice.board.dto.response;

import com.practice.board.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserInformationResponse {
    private String username;
    public UserInformationResponse(User user){
        username = user.getUsername();
    }
}