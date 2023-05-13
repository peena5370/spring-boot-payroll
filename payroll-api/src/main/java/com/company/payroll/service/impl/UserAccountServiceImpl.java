package com.company.payroll.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.company.payroll.mapper.AccountMapper;
import com.company.payroll.model.Account;

/**
 * load account from database and wrapped as UserDetails object
 * Created 1 May 2023
 */
@Service
public class UserAccountServiceImpl implements UserDetailsService {

	@Autowired
	private AccountMapper accountMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountMapper.selectByUsername(username);
		if(account == null) {
			throw new UsernameNotFoundException(username);
		}
		return new User(username, account.getPassword(), new ArrayList<>());
	}
}