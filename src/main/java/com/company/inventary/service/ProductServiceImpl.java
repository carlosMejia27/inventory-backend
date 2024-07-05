package com.company.inventary.service;

import com.company.inventary.model.Category;
import com.company.inventary.model.Product;
import com.company.inventary.repository.CategoryDao;
import com.company.inventary.repository.ProductDao;
import com.company.inventary.response.CategoryRespondeRest;
import com.company.inventary.response.ProductRespondeRest;
import com.company.inventary.util.Util;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public ResponseEntity<ProductRespondeRest> save(Product product, Long categoryId) {
        ProductRespondeRest response=new ProductRespondeRest();
        List<Product> list=new ArrayList<>();

        try{
            Optional<Category> category=categoryDao.findById(categoryId);
            if(category.isPresent()){
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

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ProductRespondeRest> searchById(Long id) {
        ProductRespondeRest response=new ProductRespondeRest();
        List<Product> list=new ArrayList<>();

        try{
            // buscar por id
            Optional<Product> product=productDao.findById(id);
            if(product.isPresent()){

                byte[] imagenDescomprimida= Util.decompressZLib(product.get().getPicture());
                product.get().setPicture(imagenDescomprimida);
                list.add(product.get());
                response.getProduct().setProducts(list);
                response.setMetada("respuesta ok","00","producto encontrado");

            }else{
                response.setMetada("respuesta no ok" ,"-1","Producto no encontrada en la asociacion");
                return new ResponseEntity<ProductRespondeRest>(response, HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            e.getStackTrace();
            response.setMetada("respuesta no ok" ,"-1","Error al guardar producto");
            return new ResponseEntity<ProductRespondeRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return  new ResponseEntity<ProductRespondeRest>(response, HttpStatus.OK);
    }



    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ProductRespondeRest> searchByName(String name) {
        ProductRespondeRest response=new ProductRespondeRest();
        List<Product> list=new ArrayList<>();
        List<Product> listAxi=new ArrayList<>();

        try{
            // buscar por id
            listAxi=productDao.findByNameContainingIgnoreCase(name);

            if(listAxi.size()>0){

              listAxi.stream().forEach((p)->{
                  byte[] imagenDescomprimida= Util.decompressZLib(p.getPicture());
                  p.setPicture(imagenDescomprimida);
                  list.add(p);

              });
                response.getProduct().setProducts(list);
                response.setMetada("respuesta ok","00","producto encontrado");

            }else{
                response.setMetada("respuesta no ok" ,"-1","Producto al buscar producto por nombre");
                return new ResponseEntity<ProductRespondeRest>(response, HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            e.getStackTrace();
            response.setMetada("respuesta no ok" ,"-1","Error al encontrar el producto");
            return new ResponseEntity<ProductRespondeRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return  new ResponseEntity<ProductRespondeRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<ProductRespondeRest> deletById(Long id) {
        ProductRespondeRest response=new ProductRespondeRest();

        try{
            // delete product por id
           productDao.deleteById(id);
            response.setMetada("respuesta ok","00","producto eliminado");

        }catch (Exception e){
            e.getStackTrace();
            response.setMetada("respuesta no ok" ,"-1","Error al eliminar producto");
            return new ResponseEntity<ProductRespondeRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return  new ResponseEntity<ProductRespondeRest>(response, HttpStatus.OK);
    }

}
