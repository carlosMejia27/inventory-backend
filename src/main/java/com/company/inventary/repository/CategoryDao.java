package com.company.inventary.repository;

import com.company.inventary.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryDao extends CrudRepository<Category,Long> {
}
