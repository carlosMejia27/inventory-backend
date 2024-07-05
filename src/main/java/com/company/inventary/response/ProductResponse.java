package com.company.inventary.response;

import com.company.inventary.model.Product;
import lombok.Data;

import java.util.List;

@Data
public class ProductResponse {

    List<Product> products;
}
