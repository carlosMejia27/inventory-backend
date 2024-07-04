package com.company.inventary.response;


import com.company.inventary.model.Category;
import lombok.Data;

import java.util.List;
@Data
public class CategoryResponse {
    private List <Category> categories;
}
