package com.example.petsupplies.core.service;

import java.util.List;

import com.example.petsupplies.core.backend.entity.OrderEntity;
import com.example.petsupplies.core.model.OrderVO;

/**
 * PurchaseOrderSessionService is used expose method to process order.
 * @author Jeetendra
 * @version 1.0
 * @since 2015-06-22
 */

public interface PurchaseOrderSessionService
{
   boolean processOrder(OrderEntity orderEntity);
   List<OrderVO> showOrders();
   boolean editOrder(OrderEntity orderEntity);
}
