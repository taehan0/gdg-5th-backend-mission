package gdg.hongik.mission.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDto {
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Item {
        private String name;
        private int quantity;
        private int amount;
    }

    private int totalAmount;
    private List<Item> items;
}
