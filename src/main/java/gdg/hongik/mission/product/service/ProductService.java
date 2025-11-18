package gdg.hongik.mission.product.service;

import gdg.hongik.mission.product.dto.*;
import gdg.hongik.mission.product.entity.Product;
import gdg.hongik.mission.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private ProductDto toDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStock()
        );
    }

    public ProductDto getByName(String name) {
        Product product = productRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다. name=" + name));
        return toDto(product);
    }

    public ProductDto create(ProductRequestDto req) {
        Product product = Product.builder()
                .name(req.getName())
                .price(req.getPrice())
                .stock(req.getStock())
                .build();

        Product saved = productRepository.save(product);
        return toDto(saved);
    }

    public ProductDto increaseStock(Long id, ProductUpdateDto req) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다. id=" + id));

        product.setStock(product.getStock() + req.getQuantity());
        Product saved = productRepository.save(product);
        return toDto(saved);
    }

    public ProductDto decreaseStock(Long id, ProductUpdateDto req) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다. id=" + id));

        int newStock = product.getStock() - req.getQuantity();
        if (newStock < 0) {
            throw new IllegalArgumentException("재고가 부족합니다. 현재 재고=" + product.getStock());
        }

        product.setStock(newStock);
        Product saved = productRepository.save(product);
        return toDto(saved);
    }

    public PurchaseDto purchase(PurchaseDto req) {
        return new PurchaseDto(10000, req.getItems());
    }

    public Map<String, Object> deleteProducts(ProductDeleteRequestDto req) {
        productRepository.deleteAllById(req.getIds());
        return Map.of("totalProducts", req.getIds().size());
    }
}