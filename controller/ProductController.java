package com.sathya.mvcproject.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.sathya.mvcproject.entity.ProductEntity;
import com.sathya.mvcproject.model.ProductModel;
import com.sathya.mvcproject.service.ProductService;

import jakarta.validation.Valid;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    // Render the form to add a new product
    @GetMapping("/productform")
    public String getForm(Model model) {
        ProductModel productModel = new ProductModel();
        productModel.setPrice(100);
        productModel.setQuantity(1);
        productModel.setMadeIn("India");
        model.addAttribute("productModel", productModel);
        return "add-product";  // Thymeleaf template for adding product
    }

    // Save product from form
    @PostMapping("/saveProduct")
    public String saveProduct(@Valid @ModelAttribute ProductModel productModel,BindingResult bindingResult) {
    	if(bindingResult.hasErrors())
    	{
    		return "add-product";
    	}
        productService.saveProduct(productModel);
        return "success";  // Redirect or display success page
    }

    // Get all products
    @GetMapping("/getallproducts")
    public String getAllProducts(Model model) {
        List<ProductEntity> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "product-list";
    }

    // Get product by id
    @GetMapping("/getproduct/{id}")
    public String getProductById(@PathVariable long id, Model model) {
        Optional<ProductEntity> product = productService.getProductById(id);
        if (product.isPresent()) {
        	ProductEntity productEntity=product.get();
            model.addAttribute("product", productEntity);
        } else {
            model.addAttribute("errorMessage", "Product with ID " + id + " not found.");
        }
        return "searchproduct";
    }

    // Delete product by ID
    @GetMapping("/deleteproduct/{id}")
    public String deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
        return "redirect:/getallproducts";
    }

  
    // Update product
    
    @GetMapping("editproduct/{id}")
    public String editproduct(@PathVariable long id,Model model)
    {
    	ProductModel  productModel= productService.editproduct(id);
    	model.addAttribute("productModel" ,productModel);
    	model.addAttribute("id",id);
    	return "editproduct";
    }
  @PostMapping("/updateProduct/{id}")
    public String updateProduct(@PathVariable long id, @ModelAttribute("products") ProductModel productModel) {
        productService.updateProduct(id, productModel);
        return "redirect:/getallproducts";
    }
}
