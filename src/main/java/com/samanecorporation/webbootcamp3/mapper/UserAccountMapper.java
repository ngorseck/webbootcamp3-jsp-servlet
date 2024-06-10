package com.samanecorporation.webbootcamp3.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.samanecorporation.webbootcamp3.dto.UserAccountDTO;
import com.samanecorporation.webbootcamp3.entities.UserAccountEntity;

public class UserAccountMapper {
	
	public static UserAccountEntity toUserAccountEntity(UserAccountDTO userDTO) {
		
		UserAccountEntity userEntity = new UserAccountEntity();
		userEntity.setId(userDTO.getId());
		userEntity.setEmail(userDTO.getEmail());
		userEntity.setPassword(userDTO.getPassword());
		
		return userEntity;
	}
	
	public static UserAccountDTO toUserAccountDto(UserAccountEntity userEntity) {
		
		UserAccountDTO userDto = new UserAccountDTO();
		userDto.setId(userEntity.getId());
		userDto.setEmail(userEntity.getEmail());
		userDto.setPassword(userEntity.getPassword());
		
		return userDto;
	}
	
	public static List<UserAccountDTO> toListUserAccountDto(List<UserAccountEntity> userEntities) {
		
		
		return userEntities.stream() 
				.map(UserAccountMapper::toUserAccountDto)
				.toList();
		 
		
		/*
		 * return userEntities.stream() .map(userEntity -> toUserAccountDto(userEntity))
		 * .collect(Collectors.toList());
		 */
	}

}
