package com.example.petsupplies.core.service;

import com.example.petsupplies.core.backend.entity.OrderEntity;

/**
 * PurchaseOrderSessionService is used expose method to process order.
 * @author Jeetendra
 */

public interface PurchaseOrderSessionService
{

   public boolean processOrder(OrderEntity orderEntity);

}
