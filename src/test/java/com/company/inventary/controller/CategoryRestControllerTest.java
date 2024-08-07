package com.company.inventary.controller;

import com.company.inventary.model.Category;
import com.company.inventary.response.CategoryRespondeRest;
import com.company.inventary.service.IcategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CategoryRestControllerTest {

    @InjectMocks // informa de  todos los mock que se van a utilizar
    CategoryRestController categoryRestController;
    @Mock // es un doble de servicio como una simulacion
    private IcategoryService servicio;

    @BeforeEach //esto significa antes de cada test
    public void init(){
        // con esta configuracion hago que los mock de arriba se me habilite se me haga efectiva
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void saveTest() {
        MockHttpServletRequest request=new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Category category = new Category();


        when((servicio.save(any(Category.class)))).thenReturn(new ResponseEntity< CategoryRespondeRest >(HttpStatus.OK));

        ResponseEntity<CategoryRespondeRest> response=categoryRestController.save(category);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        //verify(response.getStatusCodeValue()), times(1));
    }
}