package com.company.inventary.service;

import com.company.inventary.repository.CategoryDao;
import com.company.inventary.model.Category;
import com.company.inventary.response.CategoryRespondeRest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class CategoryServiceImpl implements IcategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoryRespondeRest> search() {
       CategoryRespondeRest response=new CategoryRespondeRest();
       try {
           List<Category> category= (List<Category>) categoryDao.findAll();
           response.getCategoriaResponse().setCategory(category);
           response.setMetada("Respuesta ok","00","respuesta existosa");
           return new ResponseEntity<CategoryRespondeRest>(response, HttpStatus.OK);
       }catch (Exception e){
           response.setMetada("no ok","-1","Error ");
           e.getStackTrace();
           return new ResponseEntity<CategoryRespondeRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

       }

    }
}
