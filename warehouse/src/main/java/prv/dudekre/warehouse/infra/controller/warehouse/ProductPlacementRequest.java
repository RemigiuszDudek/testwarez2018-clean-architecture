package prv.dudekre.warehouse.infra.controller.warehouse;

public final class ProductPlacementRequest {
    private String productId;
    private int quantity;

    public ProductPlacementRequest() {
        //required to create the object from json
    }

    public ProductPlacementRequest(String productId, int quantity) {
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
