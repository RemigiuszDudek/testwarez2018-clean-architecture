package prv.dudekre.warehouse.domain;

import java.util.Objects;

public final class Product {
    private final String id;
    private final int volume;

    public Product(String id, int volume) {
        this.id = id;
        this.volume = volume;
    }

    public String getId() {
        return id;
    }

    public int getVolume() {
        return volume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Product product = (Product) o;
        return volume == product.volume
                && Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, volume);
    }

    @Override
    public String toString() {
        return "Product{"
                + "id='" + id + '\''
                + ", volume=" + volume
                + '}';
    }
}
