package com.example.petsupplies.core.backend.dao;

import com.example.petsupplies.core.backend.entity.OrderEntity;

/**
 * @author Jeetendra PurchaseOrderDAO exposing the method to process the order.
 */

public interface PurchaseOrderDAO
{
   public boolean processOrder(OrderEntity orderEntity);
}
