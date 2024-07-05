package com.company.inventary.service;

import com.company.inventary.model.Product;
import com.company.inventary.response.ProductRespondeRest;
import org.springframework.http.ResponseEntity;

public interface IProductService {

    public ResponseEntity<ProductRespondeRest> save(Product product ,Long categoryId);
    public ResponseEntity<ProductRespondeRest> searchById(Long id);

    public ResponseEntity<ProductRespondeRest> searchByName(String name);

    public ResponseEntity<ProductRespondeRest> deletById(Long id);

    public ResponseEntity<ProductRespondeRest> search();


}
