package com.samanecorporation.webbootcamp3.dao;

import java.util.Optional;

import com.samanecorporation.webbootcamp3.entities.UserAccountEntity;

public interface IUserAccountDao extends Repository<UserAccountEntity> {

	Optional<UserAccountEntity> login(String email, String password);
}
