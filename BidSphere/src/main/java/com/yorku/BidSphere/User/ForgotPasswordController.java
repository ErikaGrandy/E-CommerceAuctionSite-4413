package com.yorku.BidSphere.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/forget-password")
public class ForgotPasswordController {

	private UserService userService = new UserService();
	
	@Autowired
	public ForgotPasswordController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/forgot")
	public ResponseEntity<String> forgotPasswordByQuestion(@RequestParam String userName,
			@RequestParam String securityAnswer, @RequestParam String newPassword) {
		User user = userService.findByUsername(userName);
		if (user != null && user.getSecurityAnswer() != null
				&& user.getSecurityAnswer().equalsIgnoreCase(securityAnswer)) {
			user.setPassword(newPassword);
			userService.saveUser(user);
			return ResponseEntity.ok("Password reset successfully");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid security answer");
		}
	}

}
