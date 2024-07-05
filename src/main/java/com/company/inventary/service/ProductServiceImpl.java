package com.company.inventary.service;

import com.company.inventary.model.Category;
import com.company.inventary.model.Product;
import com.company.inventary.repository.CategoryDao;
import com.company.inventary.repository.ProductDao;
import com.company.inventary.response.CategoryRespondeRest;
import com.company.inventary.response.ProductRespondeRest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService{

  private CategoryDao categoryDao;
  private ProductDao productDao;


    public ProductServiceImpl(CategoryDao categoryDao, ProductDao productDao) {
        this.categoryDao = categoryDao;
        this.productDao = productDao;
    }

    @Override
    public ResponseEntity<ProductRespondeRest> save(Product product, Long categoryId) {
        ProductRespondeRest response=new ProductRespondeRest();
        List<Product> list=new ArrayList<>();

        try{
            Optional<Category> category=categoryDao.findById(categoryId);
            if(category.isPresent()){
                System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                product.setCategory(category.get());
            }else{
                response.setMetada("respuesta no ok" ,"-1","categoria no encontrada en la asociacion");
                return new ResponseEntity<ProductRespondeRest>(response, HttpStatus.NOT_FOUND);
            }
            Product productsave=productDao.save(product);
            if (productsave!=null){
                list.add(productsave);
                response.getProduct().setProducts(list);
                response.setMetada("respuesta ok","00","producto Guardado");
            }else{
                response.setMetada("respuesta no ok" ,"-1","Producto no guardado");
                return new ResponseEntity<ProductRespondeRest>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            e.getStackTrace();
            response.setMetada("respuesta no ok" ,"-1","Error al guardar producto");
            return new ResponseEntity<ProductRespondeRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return  new ResponseEntity<ProductRespondeRest>(response, HttpStatus.OK);

    }
}
