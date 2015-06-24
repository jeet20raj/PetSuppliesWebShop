package com.example.petsupplies.core.service;

import com.example.petsupplies.core.backend.entity.AddressEntity;
import com.example.petsupplies.core.backend.entity.UserEntity;

/**
 * UserSessionService is used expose methods to login into the system Its also used to create user and user address.
 * @author Jeetendra
 * @version 1.0
 * @since 2015-06-12
 */

public interface UserSessionService
{
   public UserEntity login(String userName, String password);

   public UserEntity createUser(UserEntity userEntity);

   public AddressEntity saveAddress(AddressEntity addressEntity);
}
