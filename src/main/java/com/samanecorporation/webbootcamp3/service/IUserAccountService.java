package com.samanecorporation.webbootcamp3.service;

import java.util.List;
import java.util.Optional;

import com.samanecorporation.webbootcamp3.dto.UserAccountDTO;

public interface IUserAccountService {

	Optional<UserAccountDTO> login(String email, String password);
	List<UserAccountDTO> getAll();
	Optional<UserAccountDTO> save(UserAccountDTO userDto);
}
