package jmcordev.inventory.service;

import jmcordev.inventory.models.Product;
import jmcordev.inventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService implements  IntProductService{
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Product> listProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public Product searchProductbyId(Integer idProduct) {
        return this.productRepository.findById(idProduct).orElse(null);
    }

    @Override
    public Product saveProduct(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public void deteleteProductbyId(Integer idProduct) {
        this.productRepository.deleteById(idProduct);
    }
}
