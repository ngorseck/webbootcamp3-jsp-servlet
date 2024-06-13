package com.samanecorporation.webbootcamp3.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.samanecorporation.webbootcamp3.dao.IUserAccountDao;
import com.samanecorporation.webbootcamp3.dao.UserAccountDao;
import com.samanecorporation.webbootcamp3.dto.UserAccountDTO;
import com.samanecorporation.webbootcamp3.entities.UserAccountEntity;
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
					}).orElseThrow(() -> new EntityNotFoundException("email ou mot de passe incorrect !"));
	}

	public void setUserAccountDao(IUserAccountDao accountDao) {
		this.userDao = accountDao;
	}

	@Override
	public List<UserAccountDTO> getAll() {
		
		List<UserAccountEntity> userEntities= userDao.list(new UserAccountEntity());
		
		return UserAccountMapper.toListUserAccountDto(userEntities);
	}

	@Override
	public Optional<UserAccountDTO> save(UserAccountDTO userDto) {
		boolean result = userDao.add(UserAccountMapper.toUserAccountEntity(userDto));
		
		return (result)? Optional.of(userDto) : Optional.empty();
	}
	
}
