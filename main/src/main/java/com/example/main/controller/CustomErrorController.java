package com.example.main.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {
	//Error 404
	@RequestMapping("error")
	public String handle() {
		return "error";
	}
	
	@Override
	public String getErrorPath() {
		return "error";
	}

}
