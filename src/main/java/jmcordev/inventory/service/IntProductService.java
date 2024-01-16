package jmcordev.inventory.service;

import jmcordev.inventory.models.Product;

import java.util.List;

public interface IntProductService {
    public List<Product> listProducts();
    public Product searchProductbyId(Integer idProduct);
    public Product saveProduct(Product product);
    public void deteleteProductbyId(Integer idProduct);
}
