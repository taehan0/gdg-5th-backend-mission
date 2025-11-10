package gdg.hongik.mission.product.controller;

import gdg.hongik.mission.product.dto.*;
import gdg.hongik.mission.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ProductDto getProduct(@RequestParam String name) {
        return productService.getByName(name);
    }

    @PostMapping
    public ProductDto create(@RequestBody ProductRequestDto req) {
        return productService.create(req);
    }

    @PatchMapping("/{id}/increase")
    public ProductDto increase(@PathVariable Long id, @RequestBody ProductUpdateDto req) {
        return productService.increaseStock(id, req);
    }

    @PatchMapping("/{id}/decrease")
    public ProductDto decrease(@PathVariable Long id, @RequestBody ProductUpdateDto req) {
        return productService.decreaseStock(id, req);
    }

    @PostMapping("/purchase")
    public PurchaseDto purchase(@RequestBody PurchaseDto req) {
        return productService.purchase(req);
    }

    @DeleteMapping("/batch")
    public Map<String, Object> deleteProducts(@RequestBody ProductDeleteRequestDto req) {
        return productService.deleteProducts(req);
    }
}