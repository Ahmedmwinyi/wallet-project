/**
 * 
 */
package com.janu.walletproject.services;

import com.janu.walletproject.exceptions.AdminException;
import com.janu.walletproject.exceptions.LoginException;
import com.janu.walletproject.exceptions.LogoutException;
import com.janu.walletproject.exceptions.UserException;
import com.janu.walletproject.model.Admin;
import com.janu.walletproject.model.CurrentAdminSession;
import com.janu.walletproject.model.User;

public interface LoginLogoutAdminService {

	public CurrentAdminSession loginAdmin(User user) throws LoginException, AdminException;

	public String logoutAdmin(String key) throws LogoutException;

	public User authenticateAdmin(User user, String key) throws UserException, AdminException, LoginException;

	public Admin validateAdmin(String key) throws AdminException, LoginException;

}
