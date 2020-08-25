package com.cos.instagram.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.instagram.domain.user.User;
import com.cos.instagram.service.UserService;
import com.cos.instagram.web.dto.JoinReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
//인증 안 되있는 사람이 들어오는 컨트롤러
@Controller
public class AuthController {
	
	private static final Logger log = LoggerFactory.getLogger(AuthController.class);
	private final UserService userService;
	
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		log.info("/auth/loginFrom 진입");
		return "auth/loginForm";
	}
	@GetMapping("/auth/joinForm")
	public String joinForm() {
		log.info("/auth/joinForm 진입");
		return "auth/joinForm";
	}
	@PostMapping("/auth/join")
	public String join(JoinReqDto joinReqDto) { //validation 체크를 위해서 dto로 받는다.
		log.info(joinReqDto.toString());
		userService.회원가입(joinReqDto);
		return "redirect:/auth/loginForm";
	}
	
	@PostMapping("/login")
	public String login() { //validation 체크를 위해서 dto로 받는다.
		return "redirect:/image/feed";
	}
}
