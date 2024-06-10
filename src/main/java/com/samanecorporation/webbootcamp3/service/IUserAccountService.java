package com.samanecorporation.webbootcamp3.service;

import java.util.Optional;

import com.samanecorporation.webbootcamp3.dto.UserAccountDTO;

public interface IUserAccountService {

	Optional<UserAccountDTO> login(String email, String password);
}
