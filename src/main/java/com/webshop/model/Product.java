package com.webshop.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Must enter productPrice !!")
    private String productName;

    private int productPrice;

    @NotBlank(message = "Must enter productBrand !!")
    private String productBrand;

    @NotBlank(message = "Must enter productCategory !!")
    private String productCategory;

}
