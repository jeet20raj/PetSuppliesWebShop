package com.example.petsupplies.core.backend.dao;

import com.example.petsupplies.core.backend.entity.OrderEntity;

/**
 * PurchaseOrderDAO exposing the method to process the order.
 * @author Jeetendra
 * @version 1.0
 * @since 2015-06-22
 */

public interface PurchaseOrderDAO
{
   public boolean processOrder(OrderEntity orderEntity);
}
