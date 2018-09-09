package prv.dudekre.warehouse.util;

import java.util.Objects;

public class WarehouseEntryForTest {
    private String productId;
    private int quantity;

    public WarehouseEntryForTest() {
        // required for spring framework when translating json to object
    }

    public WarehouseEntryForTest(String productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WarehouseEntryForTest that = (WarehouseEntryForTest) o;
        return quantity == that.quantity
                && Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, quantity);
    }

    @Override
    public String toString() {
        return "WarehouseEntryForTest{productId='" + productId + '\''
                + ", quantity=" + quantity + '}';
    }
}
