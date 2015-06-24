package com.petsupplies.webshop.user.backingbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import com.example.petsupplies.core.backend.entity.AddressEntity;
import com.example.petsupplies.core.backend.entity.CartItemEntity;
import com.example.petsupplies.core.backend.entity.OrderEntity;
import com.example.petsupplies.core.backend.entity.UserEntity;
import com.example.petsupplies.core.common.enums.AddressType;
import com.example.petsupplies.core.common.enums.Status;
import com.example.petsupplies.core.common.utils.RoundOffUtil;
import com.example.petsupplies.core.service.PurchaseOrderSessionService;
import com.example.petsupplies.core.service.UserSessionService;
import com.petsupplies.webshop.user.constants.UserAppConstants;
import com.petsupplies.webshop.user.model.ProductVO;
import com.petsupplies.webshop.user.qualifiers.CustomerLoggedIn;

/**
 * ShopingCartBean will add/edit/delete/display the items being added to cart
 * 
 * @author Jeetendra
 * @version 1.0
 * @since 2015-06-18
 */

@Named
@ConversationScoped
public class ShoppingCartBean implements Serializable
{

   private List<CartItemEntity> cartItems = new ArrayList<CartItemEntity>();

   private Map<Long, CartItemEntity> itemsMap = new HashMap<Long, CartItemEntity>();

   private List<ProductVO> selectedProducts;

   private Double orderTotalPrice;

   @Inject
   private Conversation conversation;

   @Inject
   private transient Logger logger;

   @Named
   @Produces
   @RequestScoped
   private AddressEntity shippingAddress = new AddressEntity();

   @Inject
   @CustomerLoggedIn
   private UserEntity loggedInCustomer;

   @Inject
   private UserSessionService userSessionService;

   @Inject
   private PurchaseOrderSessionService orderSessionService;

   private OrderEntity orderEntity;

   /**
    * Add the product into cart
    * 
    * @param
    * @return the page view for navigation
    * @see Home page
    */
   public String addToCart()
   {
      logger.log(Level.INFO, "ShoppingCartBean :: addToCart starts");
      startConversation();
      orderEntity = new OrderEntity();
      for (ProductVO productVO : selectedProducts)
      {
         if (itemsMap.containsKey(productVO.getProductEntity().getProductId()))
         {
            CartItemEntity cartItemEntity = itemsMap.get(productVO.getProductEntity().getProductId());
            cartItemEntity.setQuantity(cartItemEntity.getQuantity() + 1);
            cartItemEntity.setTotalPrice(RoundOffUtil.getRoundOfValue(cartItemEntity.getTotalPrice() + productVO.getProductEntity().getDiscountedPrice(), 2));
         }
         else
         {
            CartItemEntity cartItemEntity = new CartItemEntity();
            cartItemEntity.setProductId(productVO.getProductEntity().getProductId());
            cartItemEntity.setProductName(productVO.getProductEntity().getProductName());
            cartItemEntity.setQuantity(1);
            cartItemEntity.setProductPrice(productVO.getProductEntity().getDiscountedPrice());
            cartItemEntity.setTotalPrice(RoundOffUtil.getRoundOfValue(productVO.getProductEntity().getDiscountedPrice(), 2));
            cartItemEntity.setOrderEntity(orderEntity);
            itemsMap.put(productVO.getProductEntity().getProductId(), cartItemEntity);
         }
      }
      setSelectedProducts(null);
      logger.log(Level.INFO, "ShoppingCartBean :: addToCart ends");
      return UserAppConstants.PAGE_VIEW_USER_HOME;
   }

   /**
    * Displaying cart view
    * 
    * @param
    * @return the page view for navigation
    * @see List of cart items
    */

   public String viewShoppingCartDetails()
   {
      logger.log(Level.INFO, "ShoppingCartBean :: viewShoppingCartDetails starts");
      cartItems.clear();
      cartItems.addAll(itemsMap.values());
      return UserAppConstants.PAGE_VIEW_CART_DETAILS;
   }

   /**
    * Update price based on the quantity changed
    * 
    * @param
    * @return the page view for navigation
    * @see Same page gets refreshed with changed values
    */

   public void updatePrice(RowEditEvent event)
   {
      logger.log(Level.INFO, "ShoppingCartBean :: updatePrice called");
      CartItemEntity cartItemEntity = (CartItemEntity) event.getObject();
      Double totalPrice = cartItemEntity.getProductPrice() * cartItemEntity.getQuantity();
      totalPrice = RoundOffUtil.getRoundOfValue(totalPrice, 2);
      cartItemEntity.setTotalPrice(totalPrice);
   }

   /**
    * Remove the item from cart
    * 
    * @param
    * @return the page view for navigation
    * @see Same page gets refreshed with changed values
    */
   public void removeItem(CartItemEntity cartItemEntity)
   {
      logger.log(Level.INFO, "ShoppingCartBean :: removeItem starts");
      cartItems.clear();
      itemsMap.remove(cartItemEntity.getProductId());
      cartItems.addAll(itemsMap.values());
      logger.log(Level.INFO, "ShoppingCartBean :: removeItem ends");
   }

   public void onRowCancel(RowEditEvent event)
   {
      logger.log(Level.INFO, "ShoppingCartBean :: onRowCancel called");
   }

   /**
    * Checkout the cart
    * 
    * @param
    * @return the page view for navigation
    * @see Checkout page with list of items and address screen
    */

   public String checkOut()
   {
      logger.log(Level.INFO, "ShoppingCartBean :: checkOut starts");
      orderTotalPrice = 0.0;
      for (CartItemEntity cartItemEntity : cartItems)
      {
         orderTotalPrice = orderTotalPrice + cartItemEntity.getTotalPrice();
      }
      orderTotalPrice = RoundOffUtil.getRoundOfValue(orderTotalPrice, 2);
      return UserAppConstants.PAGE_VIEW_CHECKOUT;
   }

   /**
    * Place order
    * 
    * @param
    * @return the page view for navigation
    * @see Home screen
    */

   public String placeOrder()
   {
      logger.log(Level.INFO, "ShoppingCartBean :: placeOrder starts");
      shippingAddress.setUserEntity(loggedInCustomer);
      shippingAddress.setAddressType(AddressType.SHIPPING_ADDRESS);
      shippingAddress = userSessionService.saveAddress(shippingAddress);
      //OrderEntity orderEntity = new OrderEntity();\
      orderEntity.setCartItems(cartItems);
      orderEntity.setStatus(Status.INPROGRESS);
      orderEntity.setUserId(loggedInCustomer.getUserId());
      orderEntity.setAddressId(shippingAddress.getAddressId());
      orderSessionService.processOrder(orderEntity);
      cartItems.clear();
      itemsMap.clear();
      endConversation();
      logger.log(Level.INFO, "ShoppingCartBean :: placeOrder ends");
      return UserAppConstants.PAGE_VIEW_USER_HOME;
   }

   /**
    * showViewCartButons
    */

   public String getShowViewCartLink()
   {
      if (null != itemsMap && itemsMap.size() > 0)
      {
         return "true";
      }
      return null;
   }

   public List<CartItemEntity> getCartItems()
   {
      return cartItems;
   }

   public void setCartItems(List<CartItemEntity> cartItems)
   {
      this.cartItems = cartItems;
   }

   public Map<Long, CartItemEntity> getItemsMap()
   {
      return itemsMap;
   }

   public void setItemsMap(Map<Long, CartItemEntity> itemsMap)
   {
      this.itemsMap = itemsMap;
   }

   public Conversation getConversation()
   {
      return conversation;
   }

   public void setConversation(Conversation conversation)
   {
      this.conversation = conversation;
   }

   /*
    * This method starts conversion before adding item to cart
    */
   private void startConversation()
   {
      if (conversation.isTransient())
      {
         conversation.begin();
      }
   }

   /*
    * This method ends conversion on checkout
    */
   private void endConversation()
   {
      if (!conversation.isTransient())
      {
         conversation.end();
      }
   }

   public List<ProductVO> getSelectedProducts()
   {
      return selectedProducts;
   }

   public void setSelectedProducts(List<ProductVO> selectedProducts)
   {
      this.selectedProducts = selectedProducts;
   }

   public UserSessionService getUserSessionService()
   {
      return userSessionService;
   }

   public void setUserSessionService(UserSessionService userSessionService)
   {
      this.userSessionService = userSessionService;
   }

   public PurchaseOrderSessionService getOrderSessionService()
   {
      return orderSessionService;
   }

   public void setOrderSessionService(PurchaseOrderSessionService orderSessionService)
   {
      this.orderSessionService = orderSessionService;
   }

   public Double getOrderTotalPrice()
   {
      return orderTotalPrice;
   }

   public void setOrderTotalPrice(Double orderTotalPrice)
   {
      this.orderTotalPrice = orderTotalPrice;
   }

   public AddressEntity getShippingAddress()
   {
      return shippingAddress;
   }

   public void setShippingAddress(AddressEntity shippingAddress)
   {
      this.shippingAddress = shippingAddress;
   }

   public OrderEntity getOrderEntity()
   {
      return orderEntity;
   }

   public void setOrderEntity(OrderEntity orderEntity)
   {
      this.orderEntity = orderEntity;
   }

}
