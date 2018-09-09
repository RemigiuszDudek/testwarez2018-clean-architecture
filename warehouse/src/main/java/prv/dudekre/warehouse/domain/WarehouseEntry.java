package prv.dudekre.warehouse.domain;

import prv.dudekre.warehouse.domain.util.ValueObject;

@ValueObject
public class WarehouseEntry {
    private final String productId;
    private final int quantity;

    public WarehouseEntry(String productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
}
