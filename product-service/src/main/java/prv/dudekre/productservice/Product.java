package prv.dudekre.productservice;

public class Product {
    private final String productId;
    private final String name;
    private final int volume;

    public Product(String productId, String name, int volume) {
        this.productId = productId;
        this.name = name;
        this.volume = volume;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public int getVolume() {
        return volume;
    }
}
