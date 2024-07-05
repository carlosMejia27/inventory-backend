package com.company.inventary.repository;

import com.company.inventary.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductDao extends CrudRepository<Product,Long> {
}
