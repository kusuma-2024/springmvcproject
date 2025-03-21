package com.sathya.mvcproject.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel {
   
	@NotEmpty(message = "Name cannot be emplty")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @Min(value = 0, message = "Price must be greater than or equal to 0")
    @NotNull(message = "Price cannot be null")
    private double price;

    @Min(value = 1, message = "Quantity must be greater than or equal to 1")
    private int quantity;

    @NotNull(message = "Made In cannot be null")
    @Size(min = 2, max = 100, message = "MadeIn must be between 2 and 100 characters")
    private String madeIn;

    @NotNull(message = "Brand cannot be null")
    @Size(min = 2, max = 50, message = "Brand must be between 2 and 50 characters")
    private String brand;

}
