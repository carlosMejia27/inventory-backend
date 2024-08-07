package com.company.inventary.controller;

import com.company.inventary.model.Category;
import com.company.inventary.response.CategoryRespondeRest;
import com.company.inventary.service.CategoryServiceImpl;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@WebMvcTest(controllers = {CategoryRestController.class})
public class CategoryRestControllerIntegracionTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
   private  CategoryServiceImpl categoryServiceImpl;

    @BeforeEach
    public void setUp() {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
    }

    private static final String BASE_MAPPING = "/api/v1/categories";


    @Test
    void create_shouldReturnBadRequest_whenDataIsNotCorrect() {
        // Given: Categoría válida
        Category validCategory = new Category();
        validCategory.setName("11");
        validCategory.setDescripcion("22");

         CategoryRespondeRest response = new CategoryRespondeRest();
        ResponseEntity<CategoryRespondeRest> responseEntity = new ResponseEntity<>(response, BAD_REQUEST);
        when(categoryServiceImpl.save(any(Category.class))).thenReturn(responseEntity);

        RestAssuredMockMvc
                .given()
                .contentType(ContentType.JSON.withCharset(StandardCharsets.UTF_8))
                .body(validCategory)
                .when()
                .post(BASE_MAPPING)
                .then()
                .log()
                .ifValidationFails()
                .statusCode(BAD_REQUEST.value());

        // Then: Verificar que el método save() del servicio se llamó con la instancia de Category inválida
        // y asegurarse de que no se haya guardado ninguna categoría incorrecta en el servicio.
        // Utiliza Mockito.verify para asegurar que se llamó al método save() con la categoría inválida exactamente una vez.
//        verify(categoryServiceImpl, never()).save(validCategory);
        verify(this.categoryServiceImpl, times(1)).save(validCategory);
    }
}
