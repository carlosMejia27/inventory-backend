package com.company.inventary.controller;

import com.company.inventary.model.Product;
import com.company.inventary.response.CategoryRespondeRest;
import com.company.inventary.response.ProductRespondeRest;
import com.company.inventary.service.IProductService;
import com.company.inventary.util.CategoryExcelExport;
import com.company.inventary.util.ProductExcelExport;
import com.company.inventary.util.Util;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = {"*"})
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
        product.setPrecio(price);
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

    @GetMapping("/products")
    public ResponseEntity<ProductRespondeRest> search(){
        ResponseEntity<ProductRespondeRest> responde=productService.search();
        return responde;
    }


    @PutMapping("/products/{id}")
    public ResponseEntity<ProductRespondeRest>  Update(
            @RequestParam("picture")MultipartFile picture,
            @RequestParam("name") String name,
            @RequestParam("price") int price,
            @RequestParam("account") int account,
            @RequestParam("categoryId") Long categoryId,
            @PathVariable Long id
    )throws IOException {

        Product product=new Product();
        product.setName(name);
        product.setPrecio(price);
        product.setAccount(account);
        product.setPicture(Util.compressZLib(picture.getBytes()));
        ResponseEntity<ProductRespondeRest> responde=productService.update(product,categoryId,id);
        return responde;
    }


    @GetMapping("/products/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey="Content-Disposition";
        String headerValue = "attachment; filename=result_products.xlsx";
        response.setHeader(headerKey,headerValue);
        ResponseEntity<ProductRespondeRest> products=productService.search();
        ProductExcelExport productExcelExport=new ProductExcelExport(products.getBody().getProduct().getProducts());

        productExcelExport.export(response);
    }

}
