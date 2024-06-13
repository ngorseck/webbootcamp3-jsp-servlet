package com.samanecorporation.webbootcamp3.service;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.samanecorporation.webbootcamp3.dao.IUserAccountDao;
import com.samanecorporation.webbootcamp3.dao.UserAccountDao;
import com.samanecorporation.webbootcamp3.dto.UserAccountDTO;
import com.samanecorporation.webbootcamp3.entities.UserAccountEntity;
import com.samanecorporation.webbootcamp3.exception.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UserAccountServiceTest {

	@InjectMocks
	private UserAccountService accountService;
	@Mock
	private IUserAccountDao accountDao;
	
	@BeforeEach
	void intit() {
		accountDao = mock(IUserAccountDao.class);
		accountService.setUserAccountDao(accountDao);
	}
	
	@Test
	void loginSuccess() {
		UserAccountEntity userAccountEntity = new UserAccountEntity();
		userAccountEntity.setId(1L);
		userAccountEntity.setEmail("seck@samanecorp.com");
		userAccountEntity.setPassword("passer");
		userAccountEntity.setState(true);
		
		when(accountDao.login(anyString(), anyString())).thenReturn(Optional.of(userAccountEntity));
		
		Optional<UserAccountDTO> userDto = accountService.login("seck@samanecorp.com", "passer");
		
		Assertions.assertTrue(userDto.isPresent());
	}
	
	@Test
	void loginFailed() {
		
		when(accountDao.login(anyString(), anyString())).thenReturn(Optional.empty());
		
		EntityNotFoundException exception = Assertions.assertThrows(
				EntityNotFoundException.class, () -> accountService.login("seck@samanecorp.com", "passer")
				);
				
		Assertions.assertEquals("email ou mot de passe incorrect !", exception.getMessage());
	}
}
