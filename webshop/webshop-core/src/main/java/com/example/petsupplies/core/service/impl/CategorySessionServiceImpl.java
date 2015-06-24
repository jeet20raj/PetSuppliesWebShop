package com.example.petsupplies.core.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.example.petsupplies.core.backend.dao.CategoryDAO;
import com.example.petsupplies.core.backend.entity.CategoryEntity;
import com.example.petsupplies.core.service.CategorySessionService;

@Stateless
public class CategorySessionServiceImpl implements CategorySessionService, Serializable
{

   @Inject
   private CategoryDAO categoryDAO;

   public List<CategoryEntity> getCategories()
   {
      return categoryDAO.getCategories();
   }

   public boolean createCategory(CategoryEntity categoryEntity)
   {
      return categoryDAO.createCategory(categoryEntity);
   }

   public boolean editCategory(CategoryEntity categoryEntity)
   {
      return categoryDAO.createCategory(categoryEntity);
   }

   public boolean deleteCategory(CategoryEntity categoryEntity)
   {
      return categoryDAO.deleteCategory(categoryEntity);
   }

}
