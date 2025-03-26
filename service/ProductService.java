package com.sathya.mvcproject.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sathya.mvcproject.entity.ProductEntity;
import com.sathya.mvcproject.model.ProductModel;
import com.sathya.mvcproject.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Save product data to the database
    public void saveProduct(ProductModel productModel) {
        ProductEntity productEntity = new ProductEntity();
        double totalAmount = productModel.getPrice() * productModel.getQuantity();
        double taxAmount = productModel.getPrice() * 18 / 100;

        productEntity.setName(productModel.getName());
        productEntity.setPrice(productModel.getPrice());
        productEntity.setQuantity(productModel.getQuantity());
        productEntity.setBrand(productModel.getBrand());
        productEntity.setMadeIn(productModel.getMadeIn());
        productEntity.setTaxAmount(taxAmount);
        productEntity.setTotalAmount(totalAmount);
        productEntity.setCretedAt(LocalDateTime.now());
        productEntity.setCreatedBy(System.getProperty("user.name"));

        productRepository.save(productEntity);
    }

    // Get all products
    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    // Get product by ID
    public Optional<ProductEntity> getProductById(long id) {
        return productRepository.findById(id);
    }

    // Delete product by ID
    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }

    // Update existing product
	public ProductModel editproduct(long id) {
      ProductEntity productEntity=productRepository.findById(id).get();
      ProductModel productModel=new ProductModel();
      productModel.setName(productEntity.getName());
      productModel.setPrice(productEntity.getPrice());
      productModel.setQuantity(productEntity.getQuantity());
      productModel.setMadeIn(productEntity.getMadeIn());
      productModel.setBrand(productEntity.getBrand());
      return productModel;
	}
	  public void updateProduct(long id, ProductModel productModel) {
    Optional<ProductEntity> productEntityOpt = productRepository.findById(id);
       ProductEntity Product = productEntityOpt.get();
       Product.setName(productModel.getName());
       Product.setPrice(productModel.getPrice());
       Product.setQuantity(productModel.getQuantity());
       Product.setBrand(productModel.getBrand());
       Product.setMadeIn(productModel.getMadeIn());
        double totalAmount = productModel.getPrice() * productModel.getQuantity();
        Product.setTotalAmount(totalAmount);
        double taxAmount = productModel.getPrice() * 18 / 100;
        Product.setTaxAmount(taxAmount);
        Product.setCretedAt(LocalDateTime.now());
        Product.setCreatedBy(System.getProperty("user.name"));

        productRepository.save(Product);
}
