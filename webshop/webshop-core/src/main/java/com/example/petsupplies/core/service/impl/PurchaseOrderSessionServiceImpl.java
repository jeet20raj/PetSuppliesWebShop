package com.example.petsupplies.core.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.example.petsupplies.core.backend.dao.PurchaseOrderDAO;
import com.example.petsupplies.core.backend.entity.OrderEntity;
import com.example.petsupplies.core.model.OrderVO;
import com.example.petsupplies.core.service.PurchaseOrderSessionService;

/**
 * PurchaseOrderSessionServiceImpl is used to process order.
 * @author Jeetendra
 * @version 1.0
 * @since 2015-06-12
 */
@Stateless
public class PurchaseOrderSessionServiceImpl implements PurchaseOrderSessionService, Serializable
{
   
   private static final long serialVersionUID = 1L;
   @Inject
   private PurchaseOrderDAO purchaseOrderDAO;

   public boolean processOrder(OrderEntity orderEntity)
   {
      return purchaseOrderDAO.processOrder(orderEntity);
   }

   public List<OrderVO> showOrders()
   {
      return purchaseOrderDAO.showOrders();
   }

   public boolean editOrder(OrderEntity orderEntity)
   {
      return purchaseOrderDAO.editOrder(orderEntity);
   }
}
