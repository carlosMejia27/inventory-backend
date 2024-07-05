package com.company.inventary.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRespondeRest extends RespondeRest{

    private ProductResponse product=new ProductResponse();
}
