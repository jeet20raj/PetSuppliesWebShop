package com.example.petsupplies.core.service;

import java.util.List;

import com.example.petsupplies.core.backend.entity.CategoryEntity;

/**
 * CategorySessionService is used expose methods to search/add/edit/delete categories.
 * @author Jeetendra
 * @version 1.0
 * @since 2015-06-12
 */

public interface CategorySessionService
{

   public List<CategoryEntity> getCategories();

   public boolean createCategory(CategoryEntity categoryEntity);

   public boolean editCategory(CategoryEntity categoryEntity);

   public boolean deleteCategory(CategoryEntity categoryId);
}
