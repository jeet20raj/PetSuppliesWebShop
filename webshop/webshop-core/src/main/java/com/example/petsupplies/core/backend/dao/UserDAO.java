package com.example.petsupplies.core.backend.dao;

import com.example.petsupplies.core.backend.entity.AddressEntity;
import com.example.petsupplies.core.backend.entity.UserEntity;

/**
 * UserDAO exposing the method to login and create the user.
 * @author Jeetendra
 * @version 1.0
 * @since 2015-06-11
 */

public interface UserDAO
{
   public UserEntity login(String userName, String password);

   public UserEntity createUser(UserEntity userEntity);

   public AddressEntity saveAddress(AddressEntity addressEntity);
}
