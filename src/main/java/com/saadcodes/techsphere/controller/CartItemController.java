package com.saadcodes.techsphere.controller;

import com.saadcodes.techsphere.model.Cart;
import com.saadcodes.techsphere.model.User;
import com.saadcodes.techsphere.response.ApiResponse;
import com.saadcodes.techsphere.service.cart.ICartItemService;
import com.saadcodes.techsphere.service.cart.ICartService;
import com.saadcodes.techsphere.service.user.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/cartItems")
public class CartItemController {
    private final ICartItemService cartItemService;
    private final IUserService userService;
    private final ICartService cartService;

    @PostMapping("/item/add")
    public ResponseEntity<ApiResponse> addItemToCart(Long userId, @RequestParam Long productId, @RequestParam int quantity) {
        User user = userService.getUserById(userId);
        Cart cart = cartService.initializeNewCartForUser(user);
        cartItemService.addItemToCart(cart.getId(), productId, quantity);
        return ResponseEntity.ok(new ApiResponse("Item added to cart successfully", null));
    }

    @DeleteMapping("cart/{cartId}/item/{itemId}/remove")
    public ResponseEntity<ApiResponse> removeItemFromCart(@PathVariable Long cartId, @PathVariable Long itemId) {
        cartItemService.removeItemFromCart(cartId, itemId);
        return ResponseEntity.ok(new ApiResponse("Item removed from cart successfully", null));
    }

    @PutMapping("cart/{cartId}/item/{itemId}/update")
    public ResponseEntity<ApiResponse> updateCartItem(@PathVariable Long cartId, @PathVariable Long itemId, @RequestParam int quantity) {
        cartItemService.updateItemQuantity(cartId, itemId, quantity);
        return ResponseEntity.ok(new ApiResponse("Cart item updated successfully", null));
    }
}
