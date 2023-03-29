package com.jgj.byl_process.product.controller;

import com.jgj.byl_process.product.controller.dto.*;
import com.jgj.byl_process.product.entity.Product;
import com.jgj.byl_process.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class ProductController {

    final private ProductService productService;

    @PostMapping(value = "/register",
            consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public void productRegister(
            @RequestPart(value = "imageFileList", required = false) List<MultipartFile> imageFileList,
            @RequestPart(value = "productInfo") RequestProductInfo productRequest) {
        log.info("productRegister()");

      productService.register(imageFileList, productRequest);
    }

    @GetMapping("/list")
    public List<ProductListResponse> productList () {
        log.info("boardList()");

        return productService.list();
    }

    @GetMapping("/{productId}")
    public ProductReadResponse productRead(@PathVariable("productId") Long productId) {
        log.info("productRead()");

        return productService.read(productId);
    }

    @DeleteMapping("/{productId}")
    public void productRemove(@PathVariable("productId") Long productId) {
        log.info("productRemove()");

        productService.remove(productId);
    }

    @PutMapping("/{productId}")
    public Product productModify(@PathVariable("productId") Long productId,
                                 @RequestBody ProductRequest productRequest) {

        log.info("productModify(): " + productRequest + "id: " + productId);

        return productService.modify(productId, productRequest);
    }

    @GetMapping("/imageList/{productId}")
    public List<ImageResourceResponse> readProductImageResource(
            @PathVariable("productId") Long productId) {

        log.info("readProductImageResource(): " + productId);

        return productService.findProductImage(productId);
    }

    @GetMapping("/all")
    public List<AllProductResponse> allProductList () {
        log.info("allProductList()");

        return productService.all();
    }
}
