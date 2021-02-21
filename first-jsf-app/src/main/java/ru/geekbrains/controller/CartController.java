package ru.geekbrains.controller;

import ru.geekbrains.persist.Product;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Named
@SessionScoped
public class CartController implements Serializable {

    private Map<Long, Product> cart = new HashMap<>();

    public List<Product> getProductsInCart() {
        return cart.entrySet().stream().map(entry -> entry.getValue()).collect(Collectors.toList());
    }

    public void addToCart(Product product) {
        cart.put(product.getId(), product);
    }

    public void removeFromCart(Product product) {
        cart.remove(product.getId());
    }

    public int getSize() {
        return cart.size();
    }

}
