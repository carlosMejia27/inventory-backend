package com.company.inventary.controller;
import com.company.inventary.response.CategoryRespondeRest;
import com.company.inventary.service.IcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
