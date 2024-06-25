package com.company.inventary.controller;
import com.company.inventary.model.Category;
import com.company.inventary.response.CategoryRespondeRest;
import com.company.inventary.service.IcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


}
