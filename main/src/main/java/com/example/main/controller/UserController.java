package com.example.main.controller;

import com.example.main.domain.LoginVO;
import com.example.main.service.UserService;
import com.example.main.util.ControllerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String loginProcess(@ModelAttribute LoginVO loginVO,
                               HttpServletResponse response) {
        String errorMsg = userService.login(loginVO);

        if (errorMsg != null) {
            throw new RuntimeException(errorMsg);
        }

        return "redirect:/";
    }

    @RequestMapping("logout")
    public String logout() {
        userService.logout();

        return "redirect:/";
    }

}
