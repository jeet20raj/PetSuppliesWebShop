package com.example.petsupplies.core.service;

import com.example.petsupplies.core.backend.entity.OrderEntity;

/**
 * PurchaseOrderSessionService is used expose method to process order.
 * @author Jeetendra
 * @version 1.0
 * @since 2015-06-22
 */

public interface PurchaseOrderSessionService
{

   public boolean processOrder(OrderEntity orderEntity);

}
