package prv.dudekre.productservice;

public class Product {
    private final String id;
    private final String name;
    private final int volume;

    public Product(String id, String name, int volume) {
        this.id = id;
        this.name = name;
        this.volume = volume;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getVolume() {
        return volume;
    }
}
