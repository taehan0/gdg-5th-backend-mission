package gdg.hongik.mission.product.service;

import gdg.hongik.mission.product.dto.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    public ProductDto getByName(String name) {
        return new ProductDto(1L, "apple", 1000, 100);
    }

    public ProductDto create(ProductRequestDto req) {
        return new ProductDto(2L, req.getName(), req.getPrice(), req.getStock());
    }

    public ProductDto increaseStock(Long id, ProductUpdateDto req) {
        return new ProductDto(id, "apple", 1000, 100 + req.getQuantity());
    }

    public ProductDto decreaseStock(Long id, ProductUpdateDto req) {
        return new ProductDto(id, "apple", 1000, 100 - req.getQuantity());
    }

    public PurchaseDto purchase(PurchaseDto req) {
        return new PurchaseDto(10000, req.getItems());
    }

    public Map<String, Object> deleteProducts(ProductDeleteRequestDto req) {
        return Map.of("totalProducts", req.getIds());
    }
}