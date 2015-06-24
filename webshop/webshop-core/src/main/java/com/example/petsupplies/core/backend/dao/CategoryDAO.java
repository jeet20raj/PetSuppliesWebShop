package com.example.petsupplies.core.backend.dao;

import java.util.List;

import com.example.petsupplies.core.backend.entity.CategoryEntity;

/**
 * @author Jeetendra CategoryDAO exposing the methods to add/edit/delete/display categories.
 */

public interface CategoryDAO
{
   public List<CategoryEntity> getCategories();

   public boolean createCategory(CategoryEntity categoryEntity);

   public boolean editCategory(CategoryEntity categoryEntity);

   public boolean deleteCategory(CategoryEntity categoryEntity);

}
