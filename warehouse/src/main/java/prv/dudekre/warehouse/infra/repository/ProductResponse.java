package prv.dudekre.warehouse.infra.repository;

public final class ProductResponse {
    private String productId;
    private int volume;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
}
