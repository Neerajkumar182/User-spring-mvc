package org.jsp.userbootapp.controller;

import java.util.List;

import org.jsp.userbootapp.dto.Product;
import org.jsp.userbootapp.dto.ResponceStructure;
import org.jsp.userbootapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
	@Autowired
	private ProductService productService;

	@PostMapping("/products/{user_id}")
	public ResponseEntity<ResponceStructure<Product>> saveProduct(@RequestBody Product product,@PathVariable(name="user_id") int user_id) {
		return productService.saveProduct(product, user_id);
	}

	@PutMapping("/products")
	public ResponseEntity<ResponceStructure<Product>> updateProduct(@RequestBody Product product) {
		return productService.updateProduct(product);
	}

	@GetMapping("/products")
	public ResponseEntity<ResponceStructure<List<Product>>> findAll() {
		return productService.findAll();
	}

	@GetMapping("/products/{id}")
	public ResponseEntity<ResponceStructure<Product>> findById(@PathVariable(name = "id") int id) {
		return productService.findById(id);
	}

	@DeleteMapping("/products/{id}")
	public ResponseEntity<ResponceStructure<Boolean>> deleteById(@PathVariable(name = "id") int id) {
		return productService.deleteById(id);
	}
	@GetMapping("/products/by-brand-category/{brand}/{category}")
	public ResponseEntity<ResponceStructure<List<Product>>> find(@PathVariable(name = "brand") String brand,@PathVariable(name = "category") String category) {
		return productService.find(brand, category);
	}
	@GetMapping("/products/by-brand/{brand}")
	public ResponseEntity<ResponceStructure<List<Product>>> findByBrand(@PathVariable(name = "brand") String brand) {
		return productService.findByBrand(brand);
	}
	@GetMapping("/products/by-category/{category}")
	public ResponseEntity<ResponceStructure<List<Product>>> findByCategory(@PathVariable (name = "category") String category) {
		return productService.findByCategory(category);
	}


}
