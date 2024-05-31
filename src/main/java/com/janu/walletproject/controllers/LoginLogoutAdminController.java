/**
 * 
 */
package com.janu.walletproject.controllers;

import com.janu.walletproject.exceptions.AdminException;
import com.janu.walletproject.exceptions.LoginException;
import com.janu.walletproject.exceptions.LogoutException;
import com.janu.walletproject.exceptions.UserException;
import com.janu.walletproject.model.Admin;
import com.janu.walletproject.model.CurrentAdminSession;
import com.janu.walletproject.model.User;
import com.janu.walletproject.services.LoginLogoutAdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class LoginLogoutAdminController {

	@Autowired
	private LoginLogoutAdminService loginLogoutAdminService;

	@PostMapping("/login")
	public ResponseEntity<CurrentAdminSession> loginAdminHandler(@Valid @RequestBody User user)
			throws LoginException, AdminException {

		CurrentAdminSession currentAdminSession = loginLogoutAdminService.loginAdmin(user);

		return new ResponseEntity<CurrentAdminSession>(currentAdminSession, HttpStatus.OK);
	}

	@PostMapping("/logout")
	public ResponseEntity<String> logoutAdminHandler(@RequestParam String key) throws LogoutException {

		String result = loginLogoutAdminService.logoutAdmin(key);

		return new ResponseEntity<String>(result, HttpStatus.OK);

	}

	@PostMapping("/authenticate")
	public ResponseEntity<User> authenticateAdminHandler(@Valid @RequestBody User user, @RequestParam String key)
			throws UserException, AdminException, LoginException {

		User validated_admin = loginLogoutAdminService.authenticateAdmin(user, key);

		return new ResponseEntity<User>(validated_admin, HttpStatus.OK);
	}

	@GetMapping("/validate")
	public ResponseEntity<Admin> validateAdminHandler(@RequestParam String key)
			throws AdminException, LoginException {

		Admin validated_admin = loginLogoutAdminService.validateAdmin(key);

		return new ResponseEntity<Admin>(validated_admin, HttpStatus.OK);
	}

}
