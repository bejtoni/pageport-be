package com.pageport.pageport.controller;

import com.pageport.pageport.dto.AddToCartDto;
import com.pageport.pageport.dto.CartDto;
import com.pageport.pageport.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping
    public void addToCart(@RequestBody AddToCartDto dto) {
        cartService.addToCart(dto);
    }

    @GetMapping("/{userId}")
    public List<CartDto> getCartItems(@PathVariable UUID userId) {
        return cartService.getCartByUserId(userId);
    }

    // Bonus metoda za brisanje stavke iz korpe
    @DeleteMapping("/{cartId}")
    public void removeItem(@PathVariable UUID cartId) {
        cartService.removeFromCart(cartId);
    }

    @PatchMapping("/{cartId}")
    public void updateQuantity(@PathVariable UUID cartId, @RequestBody int delta) {
        System.out.println("POZVAO SAM UPDATE JAKO");
        cartService.updateQuantity(cartId, delta);
    }

}
