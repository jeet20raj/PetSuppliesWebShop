package com.example.petsupplies.core.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.example.petsupplies.core.backend.dao.ProductDAO;
import com.example.petsupplies.core.backend.entity.CategoryEntity;
import com.example.petsupplies.core.backend.entity.ProductEntity;
import com.example.petsupplies.core.model.ProductSearchFilter;
import com.example.petsupplies.core.service.impl.ProductSessionServiceImpl;

public class ProductSessionServiceTest {
	
	@Mock
	private ProductDAO  productDAO = null;
	
	@InjectMocks
    private ProductSessionService productSessionService;

	@Before
    public void setUp() {
		productSessionService = new ProductSessionServiceImpl();
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testGetAllProducts(){
		ProductEntity productEntity =  new ProductEntity();
		List<ProductEntity> productEntities =  new ArrayList<ProductEntity>();
		productEntities.add(productEntity);
		Mockito.when(productDAO.getAllProducts()).thenReturn(productEntities);
		List<ProductEntity> resultsList = productSessionService.getAllProducts();
		Assert.assertNotNull(resultsList);
		Assert.assertEquals(1, resultsList.size());
	}
	
	@Test
	public void testGetProducts(){
		ProductSearchFilter searchFilter = new ProductSearchFilter();
		searchFilter.setCategoryName("Dog products");
		ProductEntity productEntity =  new ProductEntity();
		List<ProductEntity> productEntities =  new ArrayList<ProductEntity>();
		productEntities.add(productEntity);
		Mockito.when(productDAO.getProducts(Mockito.any(ProductSearchFilter.class))).thenReturn(productEntities);
		ArgumentCaptor<ProductSearchFilter> argumentCaptor = ArgumentCaptor.forClass(ProductSearchFilter.class);
		List<ProductEntity> resultsList = productSessionService.getProducts(searchFilter);
		Mockito.verify(productDAO).getProducts(argumentCaptor.capture());
		ProductSearchFilter psFilter = argumentCaptor.getValue();
		Assert.assertNotNull(psFilter);
		Assert.assertNotNull(psFilter.getCategoryName());
		Assert.assertNotNull(resultsList);
		Assert.assertEquals(1, resultsList.size());
	}
	
	@Test
	public void testCreateProduct(){
		ProductEntity productEntity = new ProductEntity();
		CategoryEntity categoryEntity = new CategoryEntity();
		productEntity.setProductId(null);
		productEntity.setCategoryEntity(categoryEntity);
		productEntity.setProductName("DogBuiscuits");
		productEntity.setPrice(100.0);
		Mockito.when(productDAO.createProduct(Mockito.any(ProductEntity.class))).thenReturn(true);
		ArgumentCaptor<ProductEntity> argumentCaptor = ArgumentCaptor.forClass(ProductEntity.class);
		boolean isSaved = productSessionService.createProduct(productEntity);
		Mockito.verify(productDAO).createProduct(argumentCaptor.capture());
		ProductEntity pEntity = argumentCaptor.getValue();
		Assert.assertNotNull(pEntity);
		Assert.assertNull(pEntity.getProductId());
		Assert.assertNotNull(pEntity.getCategoryEntity());
		Assert.assertNotNull(pEntity.getProductName());
		Assert.assertNotNull(pEntity.getPrice());
		Assert.assertTrue(isSaved);
	}
	
	@Test
	public void testEditProduct(){
		ProductEntity productEntity = new ProductEntity();
		CategoryEntity categoryEntity = new CategoryEntity();
		productEntity.setProductId(Long.valueOf("101"));
		productEntity.setCategoryEntity(categoryEntity);
		productEntity.setProductName("DogBuiscuits");
		productEntity.setPrice(100.0);
		Mockito.when(productDAO.editProduct(Mockito.any(ProductEntity.class))).thenReturn(true);
		ArgumentCaptor<ProductEntity> argumentCaptor = ArgumentCaptor.forClass(ProductEntity.class);
		boolean isSaved = productSessionService.editProduct(productEntity);
		Mockito.verify(productDAO).editProduct(argumentCaptor.capture());
		ProductEntity pEntity = argumentCaptor.getValue();
		Assert.assertNotNull(pEntity);
		Assert.assertNotNull(pEntity.getProductId());
		Assert.assertNotNull(pEntity.getCategoryEntity());
		Assert.assertNotNull(pEntity.getProductName());
		Assert.assertNotNull(pEntity.getPrice());
		Assert.assertTrue(isSaved);
	}
	
	@Test
	public void testDeleteProduct(){
		ProductEntity productEntity = new ProductEntity();
		productEntity.setProductId(Long.valueOf("101"));
		Mockito.when(productDAO.deleteProduct(Mockito.any(ProductEntity.class))).thenReturn(true);
		ArgumentCaptor<ProductEntity> argumentCaptor = ArgumentCaptor.forClass(ProductEntity.class);
		boolean isSaved = productSessionService.deleteProduct(productEntity);
		Mockito.verify(productDAO).deleteProduct(argumentCaptor.capture());
		ProductEntity pEntity = argumentCaptor.getValue();
		Assert.assertNotNull(pEntity);
		Assert.assertNotNull(pEntity.getProductId());
		Assert.assertTrue(isSaved);
	}
}
