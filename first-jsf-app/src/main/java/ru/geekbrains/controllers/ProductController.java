package ru.geekbrains.controllers;

import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ProductController implements Serializable {

    @EJB
    private ProductRepository productRepository;

    private Product product;

    private List<Product> products;

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        products = productRepository.findAll();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String createProduct() {
        this.product = new Product();
        return "/product_form.xhtml?faces-redirect=true";
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public String editProduct(Product product) {
        this.product = product;
        return "/product_form.xhtml?faces-redirect=true";
    }

    public void deleteProduct(Product product) {
        productRepository.deleteById(product.getId());
    }

    public String saveProduct() {
        productRepository.saveOrUpdate(product);
        return "/product.xhtml?faces-redirect=true";
    }
}
