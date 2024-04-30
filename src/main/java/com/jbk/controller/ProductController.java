
package com.jbk.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jbk.model.ProductModel;
import com.jbk.model.Product_Supplier_Category;
import com.jbk.service.ProductService;


@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService service;

		
		@PostMapping("/add-product")
		public ResponseEntity<String> addProduct(@RequestBody @Valid ProductModel product) {
			service.addProduct(product);
			
			return ResponseEntity.ok("Product Added !!");
			
		}

		@GetMapping("/get-product-by-id/{productid}")
		public ResponseEntity<ProductModel> getProductById(@PathVariable long productid) {
			
				ProductModel productModel = service.getProductById(productid);

				return ResponseEntity.ok(productModel);

		}	
		
			
			
			
			
		
		
		@GetMapping("get-all-products")
		public ResponseEntity<List<ProductModel>>getAllProducts(){
			return ResponseEntity.ok(service.getAllProducts());
		}
			
		@GetMapping("sort-products")
		public ResponseEntity<List<ProductModel>> sortProducts(@RequestParam String orderType,
				@RequestParam String propertyName) {
			return ResponseEntity.ok(service.sortProduct(orderType, propertyName));
		}	
		

		@DeleteMapping("/delete-product-by-id")
		public String deleteProductById(@RequestParam("productid") long productid) {

			
			return null;

		}

		@PutMapping("/update-product")
		public String updateProduct(@RequestBody ProductModel product) {

			
			return null;

		}
		@GetMapping("/max-price")
		public ResponseEntity<Double>maxPrice(){
			return ResponseEntity.ok(service.getMaxProductPrice());
		}
			
		
		
		@GetMapping("/max-price-product")
		public Object getMaxPriceProduct() {
			return null;
			
			
			
			
			
		}
		
		@GetMapping("/get-product-by-name/{productnm}")
		public Object getProductName(@PathVariable String productnm) {
			return ResponseEntity.ok(service.getProductByName(productnm));
		}
			
			
			
			
			
		
		@PostMapping("upload-sheet")
		public ResponseEntity<Map<String, Object>> uploadSheet(@RequestParam MultipartFile myfile){

			 Map<String, Object> finalMap = service.uploadedSheet(myfile);
			return ResponseEntity.ok(finalMap);
			
		}
		
		@GetMapping("/get-product-with-sc/{productid}")
		public ResponseEntity <Product_Supplier_Category> getProductByIdWithSC(@PathVariable  long productid) {
			Product_Supplier_Category psc=service.getProductWithSCByPId(productid);
			return ResponseEntity.ok(psc);
		}
		
	
	
		
	
}
