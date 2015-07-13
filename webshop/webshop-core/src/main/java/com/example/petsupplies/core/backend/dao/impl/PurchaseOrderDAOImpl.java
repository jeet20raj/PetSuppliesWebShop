package com.example.petsupplies.core.backend.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.example.petsupplies.core.backend.dao.BaseDAO;
import com.example.petsupplies.core.backend.dao.PurchaseOrderDAO;
import com.example.petsupplies.core.backend.entity.OrderEntity;
import com.example.petsupplies.core.common.constants.Constants;
import com.example.petsupplies.core.model.OrderVO;

/**
 * PurchaseOrderDAOImpl is used process order.
 * @author Jeetendra
 * @version 1.0
 * @since 2015-06-22
 */

public class PurchaseOrderDAOImpl extends BaseDAO implements PurchaseOrderDAO
{

   /**
    * @param OrderEntity
    * @return boolean This method is used to save order in Database.
    */
   public boolean processOrder(OrderEntity orderEntity)
   {
      logger.log(Level.INFO, "PurchaseOrderDAOImpl :: processOrder method called");
      try
      {
         entityManager.persist(orderEntity);
         return true;
      }
      catch (PersistenceException e)
      {
         logger.log(Level.INFO, "PurchaseOrderDAOImpl :: processOrder method ends with exception", e);
         return false;
      }

   }

   /**
    * @param
    * @return List of orders.
    */
   public List<OrderVO> showOrders()
   {
      logger.log(Level.INFO, "PurchaseOrderDAOImpl :: showOrders method called");
      TypedQuery<OrderVO> typedQuery = entityManager.createNamedQuery(Constants.QueryConstants.SHOW_ORDERS, OrderVO.class);
      try
      {
         return typedQuery.getResultList();
      }
      catch (NoResultException e)
      {
         logger.log(Level.INFO, "PurchaseOrderDAOImpl :: showOrders method ends with exception", e);
         return new ArrayList<OrderVO>();
      }
   }

   /**
    * This method is used to edit order in Database.
    * @param OrderEntity
    * @return boolean 
    */
   public boolean editOrder(OrderEntity orderEntity)
   {
      logger.log(Level.INFO, "PurchaseOrderDAOImpl :: editOrder method called");
      try
      {
         entityManager.merge(orderEntity);
         return true;
      }
      catch (PersistenceException e)
      {
         logger.log(Level.INFO, "PurchaseOrderDAOImpl :: editOrder method ends with exception", e);
         return false;
      }
   }
}
