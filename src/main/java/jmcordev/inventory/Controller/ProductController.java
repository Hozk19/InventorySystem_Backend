package jmcordev.inventory.Controller;

import jmcordev.inventory.exception.ResourceNotFound;
import jmcordev.inventory.models.Product;
import jmcordev.inventory.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//localhost:8080/inventory-app
@RequestMapping("inventory-app")
@CrossOrigin(value = "http://localhost:4200")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private ProductService productService;
    //http://localhost:8080/inventory-app/products
    @GetMapping("/products")
    public List<Product> getProducts(){
        List<Product> products = this.productService.listProducts();
        logger.info("Productos Obtenidos");
        products.forEach((product -> logger.info(product.toString())));
        return products;
    }
    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product){
        logger.info("Product adding: "+ product);
        return this.productService.saveProduct(product);
    }
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id){
        Product product = this.productService.searchProductbyId(id);
        if(product!= null)
            return ResponseEntity.ok(product);
        else
            throw new ResourceNotFound("Id not found!");
    }
    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product productReceived){
        Product product = this.productService.searchProductbyId(id);
        product.setDescription(productReceived.getDescription());
        product.setPrice(productReceived.getPrice());
        product.setExistences(productReceived.getExistences());
        this.productService.saveProduct(product);
        return ResponseEntity.ok(product);
    }
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteProduct(@PathVariable int id){
        Product product = productService.searchProductbyId(id);
        if (product == null)
            throw new ResourceNotFound("Id: "+id+" not found!");
        this.productService.deteleteProductbyId(product.getIdProduct());
        Map<String ,Boolean> answer = new HashMap<>();
        answer.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(answer);
    }
}
