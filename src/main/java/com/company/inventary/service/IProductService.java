package com.company.inventary.service;

import com.company.inventary.model.Product;
import com.company.inventary.response.ProductRespondeRest;
import org.springframework.http.ResponseEntity;

public interface IProductService {

    public ResponseEntity<ProductRespondeRest> save(Product product ,Long categoryId);
}
