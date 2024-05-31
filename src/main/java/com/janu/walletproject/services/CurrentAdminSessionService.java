package com.janu.walletproject.services;

import com.janu.walletproject.exceptions.AdminException;
import com.janu.walletproject.exceptions.CurrentAdminSessionException;
import com.janu.walletproject.model.Admin;
import com.janu.walletproject.model.CurrentAdminSession;


public interface CurrentAdminSessionService {

	public CurrentAdminSession getCurrentAdminSession(String key) throws CurrentAdminSessionException;

	public Admin getAdminDetails(String key) throws AdminException, CurrentAdminSessionException;

	public Integer getAdminId(String key) throws CurrentAdminSessionException;

}
