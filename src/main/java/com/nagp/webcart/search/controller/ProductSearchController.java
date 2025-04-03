package com.nagp.webcart.search.controller;

import com.nagp.webcart.search.model.Product;
import com.nagp.webcart.search.service.ProductSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/search")
@CrossOrigin(origins = "*")
public class ProductSearchController {

    @Autowired
    private ProductSearchService productSearchService;

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productSearchService.getAllProducts();
    }
    @PostMapping("products/bulk")
    public ResponseEntity<String> bulkProductscreate(@RequestBody List<Product> products){
        productSearchService.saveProductsBulk(products);
        return ResponseEntity.ok().body("bulk products saved");
    }

//    @GetMapping("/products/{id}")
//    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
//        Optional<Product> product = productSearchService.getProductById(id);
//        return product.map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productSearchService.saveProduct(product));
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        productSearchService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/name")
    public List<Product> searchByName(@RequestParam String name) {
        return productSearchService.searchByName(name);
    }

    @GetMapping("/brand")
    public List<Product> searchByBrand(@RequestParam String brand) {
        return productSearchService.searchByBrand(brand);
    }

    @GetMapping("/category")
    public List<Product> searchByCategory(@RequestParam String category) {
        return productSearchService.searchByCategory(category);
    }
}
