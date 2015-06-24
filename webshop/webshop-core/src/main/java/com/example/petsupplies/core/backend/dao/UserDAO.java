package com.example.petsupplies.core.backend.dao;

import com.example.petsupplies.core.backend.entity.AddressEntity;
import com.example.petsupplies.core.backend.entity.UserEntity;

/**
 * @author Jeetendra UserDAO exposing the method to login and create the user.
 */

public interface UserDAO
{
   public UserEntity login(String userName, String password);

   public UserEntity createUser(UserEntity userEntity);

   public AddressEntity saveAddress(AddressEntity addressEntity);
}
