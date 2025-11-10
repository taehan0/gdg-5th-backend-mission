package gdg.hongik.mission.product.controller;

import gdg.hongik.mission.product.dto.*;
import gdg.hongik.mission.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 상품 관련 요청을 처리하는 컨트롤러입니다.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    /**
     * 이름을 받아 재고를 검색하는 메소드입니다.
     * @param name 이름을 입력합니다.
     * @return 재고 검색결과를 출력합니다.
     */
    @GetMapping
    public ProductDto getProduct(@RequestParam String name) {
        return productService.getByName(name);
    }

    /**
     * 재고를 등록하는 메소드입니다.
     * @param req 물품정보를 등록합니다.
     * @return 등록된 물품정보를 반환합니다.
     */
    @PostMapping
    public ProductDto create(@RequestBody ProductRequestDto req) {
        return productService.create(req);
    }

    /**
     * 등록된 재고수량을 증가시키는 메소드입니다.
     * @param id 재고 id를 입력합니다.
     * @param req 재고정보를 입력합니다.
     * @return 변경된 재고수량을 반환합니다.
     */
    @PatchMapping("/{id}/increase")
    public ProductDto increase(@PathVariable Long id, @RequestBody ProductUpdateDto req) {
        return productService.increaseStock(id, req);
    }

    /**
     * 등록된 재고수량을 감소시키는 메소드입니다.
     * @param id 재고 id를 입력합니다.
     * @param req 재고정보를 입력합니다.
     * @return 변경된 재고수량을 반환합니다.
     */
    @PatchMapping("/{id}/decrease")
    public ProductDto decrease(@PathVariable Long id, @RequestBody ProductUpdateDto req) {
        return productService.decreaseStock(id, req);
    }

    /**
     * 장바구니 물품들을 구매하는 매소드입니다.
     * @param req 구매할 물품의 정보를 입력합니다.
     * @return 구매한 물품의 정보를 반환합니다.
     */
    @PostMapping("/purchase")
    public PurchaseDto purchase(@RequestBody PurchaseDto req) {
        return productService.purchase(req);
    }

    /**
     * 재고를 삭제하는 메소드입니다.
     * @param req 삭제할 재고의 정보를 입력합니다.
     * @return 삭제한 재고의 정보를 반환합니다.
     */
    @DeleteMapping("/batch")
    public Map<String, Object> deleteProducts(@RequestBody ProductDeleteRequestDto req) {
        return productService.deleteProducts(req);
    }
}