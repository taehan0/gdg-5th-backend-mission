package gdg.hongik.mission.product.repository;

import gdg.hongik.mission.product.dto.ProductDto;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public class ProductRepository {

    // 메모리에서만 동작하는 가짜 DB
    private final Map<Long, ProductDto> store = new HashMap<>();
    private long sequence = 0L; // id 자동 증가용

    // 저장 (새로 생성/수정 둘 다 여기로)
    public ProductDto save(ProductDto dto) {
        if (dto.getId() == null) {        // 새로 생성
            dto.setId(++sequence);
        }
        store.put(dto.getId(), dto);
        return dto;
    }

    // id로 조회
    public ProductDto findById(Long id) {
        return store.get(id); // 없으면 null
    }

    // name으로 조회
    public ProductDto findByName(String name) {
        return store.values().stream()
                .filter(p -> p.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    // 여러 개 삭제
    public void deleteAllByIds(List<Long> ids) {
        for (Long id : ids) {
            store.remove(id);
        }
    }
}