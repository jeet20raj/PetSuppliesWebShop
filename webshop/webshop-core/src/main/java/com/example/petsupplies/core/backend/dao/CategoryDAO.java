package com.example.petsupplies.core.backend.dao;

import java.util.List;

import com.example.petsupplies.core.backend.entity.CategoryEntity;

/**
 * CategoryDAO exposing the methods to add/edit/delete/display categories.
 * @author Jeetendra
 * @version 1.0
 * @since 2015-06-11
 */

public interface CategoryDAO
{
   public List<CategoryEntity> getCategories();

   public boolean createCategory(CategoryEntity categoryEntity);

   public boolean editCategory(CategoryEntity categoryEntity);

   public boolean deleteCategory(CategoryEntity categoryEntity);

}
