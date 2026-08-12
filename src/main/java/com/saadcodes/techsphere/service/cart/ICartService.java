package com.saadcodes.techsphere.service.cart;

import com.saadcodes.techsphere.model.Cart;
import com.saadcodes.techsphere.model.User;

import java.math.BigDecimal;

public interface ICartService {
    Cart getCart(Long cartId);

    Cart getCartByUserId(Long userId);

    void clearCart(Long cartId);

    Cart initializeNewCartForUser(User user);

    BigDecimal getTotalPrice(Long cartId);

}
