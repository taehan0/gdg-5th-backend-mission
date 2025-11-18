package gdg.hongik.mission.product.service;

import gdg.hongik.mission.product.dto.*;
import gdg.hongik.mission.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductDto getByName(String name) {
        ProductDto dto = productRepository.findByName(name);
        return dto;
    }

    public ProductDto create(ProductRequestDto req) {
        ProductDto dto = new ProductDto(
                null,
                req.getName(),
                req.getPrice(),
                req.getStock()
        );
        return  productRepository.save(dto);
    }

    public ProductDto increaseStock(Long id, ProductUpdateDto req) {
        ProductDto dto = productRepository.findById(id);
        dto.setStock(dto.getStock() + req.getQuantity());
        return productRepository.save(dto);
    }

    public ProductDto decreaseStock(Long id, ProductUpdateDto req) {
        ProductDto dto = productRepository.findById(id);
        int newStock = dto.getStock() - req.getQuantity();
        dto.setStock(newStock);
        return productRepository.save(dto);
    }
    public PurchaseDto purchase(PurchaseDto req) {
        return new PurchaseDto(10000, req.getItems());
    }

    public Map<String, Object> deleteProducts(ProductDeleteRequestDto req) {
        productRepository.deleteAllByIds(req.getIds());
        return Map.of("totalProducts", req.getIds().size());
    }
}