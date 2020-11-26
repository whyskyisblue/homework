package com.example.main.service;

import com.example.main.domain.LoginVO;

public interface UserService {

    String loginId();

    String login(LoginVO loginVO);

    void logout();

}
