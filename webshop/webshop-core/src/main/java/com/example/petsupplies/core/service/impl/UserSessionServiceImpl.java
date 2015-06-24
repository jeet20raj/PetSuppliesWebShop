package com.example.petsupplies.core.service.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.example.petsupplies.core.backend.dao.UserDAO;
import com.example.petsupplies.core.backend.entity.AddressEntity;
import com.example.petsupplies.core.backend.entity.UserEntity;
import com.example.petsupplies.core.service.UserSessionService;

@Stateless
public class UserSessionServiceImpl implements UserSessionService
{

   @Inject
   private UserDAO userDAO;

   public UserEntity login(String userName, String password)
   {
      return userDAO.login(userName, password);
   }

   public UserEntity createUser(UserEntity userEntity)
   {
      return userDAO.createUser(userEntity);
   }

   public AddressEntity saveAddress(AddressEntity addressEntity)
   {
      return userDAO.saveAddress(addressEntity);
   }
}
