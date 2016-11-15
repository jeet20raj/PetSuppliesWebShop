package com.example.petsupplies.core.backend.dao.impl;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.petsupplies.core.backend.entity.ProductEntity;
import com.example.petsupplies.core.common.constants.Constants;
import com.example.petsupplies.core.model.ProductSearchFilter;

public class ProductDAOImplTest {

	@Mock
	private EntityManager  entityManager = null;
	
	@Mock
	private Logger logger;
	
	@InjectMocks
    private ProductDAOImpl productDAOImpl;

	@Before
    public void setUp() {
		productDAOImpl = new ProductDAOImpl();
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testGetAllProducts(){
		ProductEntity productEntity =  new ProductEntity();
		List<ProductEntity> productEntities =  new ArrayList<ProductEntity>();
		productEntities.add(productEntity);
		TypedQuery mockedQuery = mock(TypedQuery.class);
		doNothing().when(logger).info(any(String.class));
		doNothing().when(logger).log(any(Level.class),anyString(),any(Throwable.class));
        when(mockedQuery.getResultList()).thenReturn(productEntities);
        when(entityManager.createNamedQuery(anyString(), Matchers.<Class<Object>>anyObject())).thenReturn(mockedQuery);
        List<ProductEntity> resultsList = productDAOImpl.getAllProducts();
        Assert.assertNotNull(resultsList);
		Assert.assertEquals(1, resultsList.size());
	}
	
	@Test
	public void testGetAllProductsWithException(){
		ProductEntity productEntity =  new ProductEntity();
		List<ProductEntity> productEntities =  new ArrayList<ProductEntity>();
		productEntities.add(productEntity);
		TypedQuery mockedQuery = mock(TypedQuery.class);
		doNothing().when(logger).info(any(String.class));
		doNothing().when(logger).log(any(Level.class),anyString(),any(Throwable.class));
        when(mockedQuery.getResultList()).thenThrow(new PersistenceException());
        when(entityManager.createNamedQuery(anyString(), Matchers.<Class<Object>>anyObject())).thenReturn(mockedQuery);
        List<ProductEntity> resultsList = productDAOImpl.getAllProducts();
        Assert.assertNotNull(resultsList);
		Assert.assertEquals(0, resultsList.size());
	}
	
	@Test
	public void testGetProducts(){
		ProductSearchFilter searchFilter = new ProductSearchFilter();
		ProductEntity productEntity =  new ProductEntity();
		List<ProductEntity> productEntities =  new ArrayList<ProductEntity>();
		productEntities.add(productEntity);
		TypedQuery mockedQuery = mock(TypedQuery.class);
		doNothing().when(logger).info(any(String.class));
		doNothing().when(logger).log(any(Level.class),anyString(),any(Throwable.class));
        when(mockedQuery.getResultList()).thenReturn(productEntities);
        when(entityManager.createNamedQuery(anyString(), Matchers.<Class<Object>>anyObject())).thenReturn(mockedQuery);
        List<ProductEntity> resultsList = productDAOImpl.getProducts(searchFilter);
        Assert.assertNotNull(resultsList);
		Assert.assertEquals(1, resultsList.size());
	}
	
	@Test
	public void testGetProductsWithException(){
		ProductSearchFilter searchFilter = new ProductSearchFilter();
		ProductEntity productEntity =  new ProductEntity();
		List<ProductEntity> productEntities =  new ArrayList<ProductEntity>();
		productEntities.add(productEntity);
		TypedQuery mockedQuery = mock(TypedQuery.class);
		doNothing().when(logger).info(any(String.class));
		doNothing().when(logger).log(any(Level.class),anyString(),any(Throwable.class));
		when(mockedQuery.getResultList()).thenThrow(new PersistenceException());
        when(entityManager.createNamedQuery(anyString(), Matchers.<Class<Object>>anyObject())).thenReturn(mockedQuery);
        List<ProductEntity> resultsList = productDAOImpl.getProducts(searchFilter);
        Assert.assertNotNull(resultsList);
		Assert.assertEquals(0, resultsList.size());
	}
	
	@Test
	public void testCreateProduct(){
		ProductEntity productEntity =  new ProductEntity();
		doNothing().when(logger).info(any(String.class));
		doNothing().when(logger).log(any(Level.class),anyString(),any(Throwable.class));
		doNothing().when(entityManager).persist(any(ProductEntity.class));
        boolean isSaved = productDAOImpl.createProduct(productEntity);
        Assert.assertTrue(isSaved);
	}
	
	@Test
	public void testCreateProductWithException(){
		ProductEntity productEntity =  new ProductEntity();
		doNothing().when(logger).info(any(String.class));
		doNothing().when(logger).log(any(Level.class),anyString(),any(Throwable.class));
		doThrow(new PersistenceException()).when(entityManager).persist(any(ProductEntity.class));
        boolean isSaved = productDAOImpl.createProduct(productEntity);
        Assert.assertFalse(isSaved);
	}
	
	@Test
	public void testEditProduct(){
		ProductEntity productEntity =  new ProductEntity();
		doNothing().when(logger).info(any(String.class));
		doNothing().when(logger).log(any(Level.class),anyString(),any(Throwable.class));
		when(entityManager.merge(any(ProductEntity.class))).thenReturn(productEntity);
        boolean isSaved = productDAOImpl.editProduct(productEntity);
        Assert.assertTrue(isSaved);
	}
	
	@Test
	public void testEditProductWithException(){
		ProductEntity productEntity =  new ProductEntity();
		doNothing().when(logger).info(any(String.class));
		doNothing().when(logger).log(any(Level.class),anyString(),any(Throwable.class));
		doThrow(new PersistenceException()).when(entityManager).merge(any(ProductEntity.class));
        boolean isSaved = productDAOImpl.editProduct(productEntity);
        Assert.assertFalse(isSaved);
	}
	
	@Test
	public void testDeleteProduct(){
		ProductEntity productEntity =  new ProductEntity();
		doNothing().when(logger).info(any(String.class));
		doNothing().when(logger).log(any(Level.class),anyString(),any(Throwable.class));
		doNothing().when(entityManager).remove(any(ProductEntity.class));
        boolean isDeleted = productDAOImpl.deleteProduct(productEntity);
        Assert.assertTrue(isDeleted);
	}
	
	@Test
	public void testDeleteProductWithException(){
		ProductEntity productEntity =  new ProductEntity();
		doNothing().when(logger).info(any(String.class));
		doNothing().when(logger).log(any(Level.class),anyString(),any(Throwable.class));
		doThrow(new PersistenceException()).when(entityManager).remove(any(ProductEntity.class));
        boolean isDeleted = productDAOImpl.deleteProduct(productEntity);
        Assert.assertFalse(isDeleted);
	}
}
