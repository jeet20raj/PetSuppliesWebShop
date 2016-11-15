package com.example.petsupplies.core.backend.dao.impl;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.petsupplies.core.backend.entity.CategoryEntity;
import com.example.petsupplies.core.backend.entity.ProductEntity;

public class CategoryDAOImplTest {

	@Mock
	private EntityManager  entityManager = null;
	
	@Mock
	private Logger logger;
	
	@InjectMocks
    private CategoryDAOImpl categoryDAOImpl;

	@Before
    public void setUp() {
		categoryDAOImpl = new CategoryDAOImpl();
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testGetCategories(){
		CategoryEntity productEntity =  new CategoryEntity();
		List<CategoryEntity> categoriesList =  new ArrayList<CategoryEntity>();
		categoriesList.add(productEntity);
		TypedQuery mockedQuery = mock(TypedQuery.class);
		doNothing().when(logger).info(any(String.class));
		doNothing().when(logger).log(any(Level.class),anyString(),any(Throwable.class));
        when(mockedQuery.getResultList()).thenReturn(categoriesList);
        when(entityManager.createNamedQuery(anyString(), Matchers.<Class<Object>>anyObject())).thenReturn(mockedQuery);
        List<CategoryEntity> resultsList = categoryDAOImpl.getCategories();
        Assert.assertNotNull(resultsList);
		Assert.assertEquals(1, resultsList.size());
	}
	
	@Test
	public void testGetCategoriesWithException(){
		CategoryEntity productEntity =  new CategoryEntity();
		List<CategoryEntity> categoriesList =  new ArrayList<CategoryEntity>();
		categoriesList.add(productEntity);
		TypedQuery mockedQuery = mock(TypedQuery.class);
		doNothing().when(logger).info(any(String.class));
		doNothing().when(logger).log(any(Level.class),anyString(),any(Throwable.class));
        when(mockedQuery.getResultList()).thenThrow(new PersistenceException());
        when(entityManager.createNamedQuery(anyString(), Matchers.<Class<Object>>anyObject())).thenReturn(mockedQuery);
        List<CategoryEntity> resultsList = categoryDAOImpl.getCategories();
        Assert.assertNotNull(resultsList);
		Assert.assertEquals(1, resultsList.size());
	}
	
	
	
	@Test
	public void testCreateCategory(){
		CategoryEntity categoryEntity =  new CategoryEntity();
		doNothing().when(logger).info(any(String.class));
		doNothing().when(logger).log(any(Level.class),anyString(),any(Throwable.class));
		doNothing().when(entityManager).persist(any(ProductEntity.class));
        boolean isSaved = categoryDAOImpl.createCategory(categoryEntity);
        Assert.assertTrue(isSaved);
	}
	
	@Test
	public void testCreateProductWithException(){
		CategoryEntity categoryEntity =  new CategoryEntity();
		doNothing().when(logger).info(any(String.class));
		doNothing().when(logger).log(any(Level.class),anyString(),any(Throwable.class));
		doThrow(new PersistenceException()).when(entityManager).persist(any(CategoryEntity.class));
        boolean isSaved = categoryDAOImpl.createCategory(categoryEntity);
        Assert.assertFalse(isSaved);
	}
	
	@Test
	public void testEditCategory(){
		CategoryEntity categoryEntity =  new CategoryEntity();
		doNothing().when(logger).info(any(String.class));
		doNothing().when(logger).log(any(Level.class),anyString(),any(Throwable.class));
		doNothing().when(entityManager).merge(any(ProductEntity.class));
        boolean isSaved = categoryDAOImpl.deleteCategory(categoryEntity);
        Assert.assertTrue(isSaved);
	}
	
	@Test
	public void testEditCategorytWithException(){
		CategoryEntity categoryEntity =  new CategoryEntity();
		doNothing().when(logger).info(any(String.class));
		doNothing().when(logger).log(any(Level.class),anyString(),any(Throwable.class));
		doThrow(new PersistenceException()).when(entityManager).merge(any(CategoryEntity.class));
        boolean isSaved = categoryDAOImpl.deleteCategory(categoryEntity);
        Assert.assertFalse(isSaved);
	}
}
