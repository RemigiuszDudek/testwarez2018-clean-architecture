package prv.dudekre.warehouse.domain;

import prv.dudekre.warehouse.domain.util.ValueObject;

import static prv.dudekre.warehouse.domain.PlacementStatus.FAILURE;
import static prv.dudekre.warehouse.domain.PlacementStatus.SUCCESS;

@ValueObject
public final class WarehousePlacementResult {
    private final String productId;
    private final PlacementStatus status;
    private final String message;

    private WarehousePlacementResult(String productId,
                                     PlacementStatus status,
                                     String message) {
        this.productId = productId;
        this.status = status;
        this.message = message;
    }

    public static WarehousePlacementResult success(String productId) {
        return new WarehousePlacementResult(productId, SUCCESS, "");
    }

    public static WarehousePlacementResult failure(String productId, String message) {
        return new WarehousePlacementResult(productId, FAILURE, message);
    }

    public PlacementStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getProductId() {
        return productId;
    }

    @Override
    public String toString() {
        return "WarehousePlacementResult{"
                + "productId='" + productId + '\''
                + ", status=" + status
                + ", message='" + message + '\''
                + '}';
    }
}
