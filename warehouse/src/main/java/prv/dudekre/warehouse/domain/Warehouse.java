package prv.dudekre.warehouse.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static java.util.Collections.unmodifiableMap;
import static prv.dudekre.warehouse.domain.WarehousePlacementResult.failure;
import static prv.dudekre.warehouse.domain.WarehousePlacementResult.success;

public final class Warehouse implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final String LACK_OF_SPACE_TEMPLATE = "Lack of space. Required space %s, remaining space %s.";
    private final Map<String, Integer> content;
    private int remainingVolume;

    public Warehouse(int initialVolume) {
        this.remainingVolume = initialVolume;
        this.content = new HashMap<>();
    }

    public int getRemainingVolume() {
        return remainingVolume;
    }

    public WarehousePlacementResult placeProduct(Product product, int quantity) {
        WarehousePlacementResult result;
        int totalRequiredVolume = product.getVolume() * quantity;
        if (remainingVolume >= totalRequiredVolume) {
            remainingVolume = remainingVolume - totalRequiredVolume;
            addToContent(product, quantity);
            result = success(product.getId());
        } else {
            String errorMessage = format(LACK_OF_SPACE_TEMPLATE, totalRequiredVolume, remainingVolume);
            result = failure(product.getId(), errorMessage);
        }
        return result;
    }

    private void addToContent(Product product, int quantity) {
        String productId = product.getId();
        if (content.containsKey(productId)) {
            content.put(productId, content.get(productId) + quantity);
        } else {
            content.put(productId, quantity);
        }
    }

    public List<WarehouseEntry> getContent() {
        return unmodifiableMap(content).entrySet().stream()
                .map(item -> new WarehouseEntry(item.getKey(), item.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Warehouse warehouse = (Warehouse) o;
        return remainingVolume == warehouse.remainingVolume
                && Objects.equals(content, warehouse.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, remainingVolume);
    }
}
