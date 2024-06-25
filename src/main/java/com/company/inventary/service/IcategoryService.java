package com.company.inventary.service;

import com.company.inventary.model.Category;
import com.company.inventary.response.CategoryRespondeRest;
import org.springframework.http.ResponseEntity;

public interface IcategoryService {

    public ResponseEntity<CategoryRespondeRest> search();
    public ResponseEntity<CategoryRespondeRest> searchByID(Long id);

    public ResponseEntity<CategoryRespondeRest> save( Category category);
}
