package com.example.main.service;

import com.example.main.domain.LoginVO;
import com.example.main.util.RequestUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public String loginId() {
        HttpSession session = RequestUtil.request().getSession();

        return (String) session.getAttribute("loginId");
    }

    @Override
    public String login(LoginVO loginVO) {
        String id = loginVO.getId();
        String pass = loginVO.getPass();

        if (id == null || id.isEmpty())
            return "アカウントを入力してください。";
        else if (id.length() < 4 || id.length() > 20)
        	return "4 ~ 20字以内でお願いいたします。";
        else if (!Pattern.matches("^[a-zA-Z0-9]*$", id))
        	return "英文字と数字のみ入力してください。";

        if (pass == null || pass.isEmpty())
        	return "パスワードを入力してください。";
        else if (pass.length() < 8 || pass.length() > 32)
        	return "8 ~ 32字以内でお願いいたします。";
        else if (!Pattern.matches("^[a-zA-Z0-9]*$", pass))
        	return "英文字と数字のみ入力してください。";
        else {
            boolean containsUppercase = false;

            for (int i = 0; i < pass.length(); i++) {
                char c = pass.charAt(i);

                if (c >= 'A' && c <= 'Z') {
                    containsUppercase = true;
                    break;
                }
            }

            if (!containsUppercase)
                return "１字以上を大文字でお願いいたします。";
        }

        if (!id.equals("admin") || !pass.equals("Admin1234")) {
            return "アカウントとかパスワードが間違っております。";
        }

        HttpSession session = RequestUtil.request().getSession();

        session.setAttribute("loginId", id);

        return null;
    }

    @Override
    public void logout() {
        HttpSession session = RequestUtil.request().getSession();

        session.removeAttribute("loginId");
    }

}
