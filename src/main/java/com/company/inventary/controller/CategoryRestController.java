package com.company.inventary.controller;
import com.company.inventary.model.Category;
import com.company.inventary.response.CategoryRespondeRest;
import com.company.inventary.service.IcategoryService;
import com.company.inventary.util.CategoryExcelExport;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class CategoryRestController {

    @Autowired
    private IcategoryService servicio;

    /**
     * get all categories
     * @return
     */
    @GetMapping("/categories")
    public ResponseEntity<CategoryRespondeRest> searchCategory(){
        ResponseEntity<CategoryRespondeRest> responde=servicio.search();
                return responde;
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryRespondeRest> searchCategoryById(@PathVariable long id){
        ResponseEntity<CategoryRespondeRest> responde=servicio.searchByID(id);
        return responde;
    }


    @PostMapping("/categories")
    public ResponseEntity<CategoryRespondeRest> save(@RequestBody Category category){
        ResponseEntity<CategoryRespondeRest> responde=servicio.save(category);
        return responde;
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<CategoryRespondeRest> update(@RequestBody Category category ,@PathVariable  Long id){
        ResponseEntity<CategoryRespondeRest> responde=servicio.update(category,id);
        return responde;
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<CategoryRespondeRest> delete(@PathVariable  Long id){
        ResponseEntity<CategoryRespondeRest> responde=servicio.deletId(id);
        return responde;
    }

    @GetMapping("/categories/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey="Content-Disposition";
        String headerValue = "attachment; filename=result_category.xlsx";
        response.setHeader(headerKey,headerValue);
        ResponseEntity<CategoryRespondeRest> categoriaResponse=servicio.search();
        CategoryExcelExport categoryExcelExport=new CategoryExcelExport(categoriaResponse.getBody().getCategoriaResponse().getCategories());

        categoryExcelExport.export(response);
    }

}
