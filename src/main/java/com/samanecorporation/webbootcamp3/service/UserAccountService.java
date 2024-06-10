package com.samanecorporation.webbootcamp3.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.samanecorporation.webbootcamp3.dao.IUserAccountDao;
import com.samanecorporation.webbootcamp3.dao.UserAccountDao;
import com.samanecorporation.webbootcamp3.dto.UserAccountDTO;
import com.samanecorporation.webbootcamp3.exception.EntityNotFoundException;
import com.samanecorporation.webbootcamp3.mapper.UserAccountMapper;


public class UserAccountService implements IUserAccountService {

	private static Logger logger = LoggerFactory.getLogger(UserAccountService.class);
	
	private IUserAccountDao userDao = new UserAccountDao();

	@Override
	public Optional<UserAccountDTO> login (String email, String password) {
		
		logger.info("Tentative email : {} ", email);
		
		return userDao.login(email, password)
					.map(user -> {
						UserAccountDTO userDto = UserAccountMapper.toUserAccountDto(user);
						
						return Optional.of(userDto);
					}).orElseThrow(() -> new EntityNotFoundException("email ou mort de passe incorrect !"));
	}

	public void setUserAccountDao(UserAccountDao accountDao) {
		this.userDao = accountDao;
	}
	
}
