package com.sx_report.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/socket")
@Controller
public class SocketController {

	@RequestMapping("/test1")
	public ModelAndView test1(HttpServletRequest request, HttpServletResponse response, String param)
			throws Exception {

		return new ModelAndView("/webSocket");

	}
}
