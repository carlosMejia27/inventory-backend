package com.company.inventary.controller;

import com.company.inventary.model.Product;
import com.company.inventary.response.ProductRespondeRest;
import com.company.inventary.service.IProductService;
import com.company.inventary.util.Util;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class ProductRestController {

    private IProductService productService;

    public ProductRestController(IProductService productService) {
        this.productService = productService;
    }

    @PostMapping("products")
    public ResponseEntity<ProductRespondeRest>  save(
            @RequestParam("picture")MultipartFile picture,
            @RequestParam("name") String name,
            @RequestParam("price") int price,
            @RequestParam("account") int account,
            @RequestParam("categoryId") Long categoryId
            )throws IOException {

        Product product=new Product();
        product.setName(name);
        product.setAccount(price);
        product.setAccount(account);
        product.setPicture(Util.compressZLib(picture.getBytes()));
        ResponseEntity<ProductRespondeRest> responde=productService.save(product,categoryId);
        return responde;
    }

    @GetMapping("products/{id}")
    public ResponseEntity<ProductRespondeRest> searchbyId(@PathVariable long id){
         ResponseEntity<ProductRespondeRest> responde=productService.searchById(id);
        return responde;
    }

    @GetMapping("products/filter/{name}")
    public ResponseEntity<ProductRespondeRest> searchbyName(@PathVariable String name){
        ResponseEntity<ProductRespondeRest> responde=productService.searchByName(name);
        return responde;
    }

    @DeleteMapping("products/{id}")
    public ResponseEntity<ProductRespondeRest> deleteById(@PathVariable long id){
        ResponseEntity<ProductRespondeRest> responde=productService.deletById(id);
        return responde;
    }
}
