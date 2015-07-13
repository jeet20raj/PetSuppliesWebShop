package com.example.petsupplies.core.backend.dao;

import java.util.List;

import com.example.petsupplies.core.backend.entity.OrderEntity;
import com.example.petsupplies.core.model.OrderVO;

/**
 * PurchaseOrderDAO exposing the method to process the order.
 * @author Jeetendra
 * @version 1.0
 * @since 2015-06-22
 */

public interface PurchaseOrderDAO
{
   boolean processOrder(OrderEntity orderEntity);
   List<OrderVO> showOrders();
   boolean editOrder(OrderEntity orderEntity);
}
