package prv.dudekre.warehouse.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static prv.dudekre.warehouse.Stubs.id;
import static prv.dudekre.warehouse.domain.PlacementStatus.FAILURE;
import static prv.dudekre.warehouse.domain.PlacementStatus.SUCCESS;

public class WarehouseTest {
    private static final int WAREHOUSE_VOLUME = 10;
    private final Warehouse warehouse = new Warehouse(WAREHOUSE_VOLUME);

    @Test
    public void addingProductToWarehouseDecreasesRemainingVolume() {
        int productVolume = 2;
        Product product = new Product(id(), productVolume);

        WarehousePlacementResult result = warehouse.placeProduct(product, 1);

        assertThat(warehouse.getRemainingVolume()).isEqualTo(WAREHOUSE_VOLUME - productVolume);
        assertThat(result.getStatus()).isEqualTo(SUCCESS);
        assertThat(result.getMessage()).isNullOrEmpty();
    }

    @Test
    public void itIsNotPossibleToAddProductWithVolumeBeyondRemainingVolume() {
        int productVolume = WAREHOUSE_VOLUME + 1;
        Product product = new Product(id(), productVolume);

        WarehousePlacementResult result = warehouse.placeProduct(product, 1);

        assertThat(warehouse.getRemainingVolume()).isEqualTo(WAREHOUSE_VOLUME);
        assertThat(result.getStatus()).isEqualTo(FAILURE);
        assertThat(result.getMessage())
                .isEqualTo(String.format("Lack of space. Required space %s, remaining space %s.", productVolume, WAREHOUSE_VOLUME));
    }

    @Test
    @SuppressWarnings("checkstyle:MagicNumber")
    public void itIsNotPossibleToAddMultipleSmallProductsWhenTotalVolumeExceedsRemainingVolume() {
        int productVolume = 1;
        int productQuantity = 11;
        int totalRequiredVolume = productVolume * productQuantity;
        Product product = new Product(id(), productVolume);

        WarehousePlacementResult result = warehouse.placeProduct(product, productQuantity);

        assertThat(result.getStatus()).isEqualTo(FAILURE);
        assertThat(warehouse.getRemainingVolume()).isEqualTo(WAREHOUSE_VOLUME);
        assertThat(result.getMessage())
                .isEqualTo(String.format("Lack of space. Required space %s, remaining space %s.", totalRequiredVolume, WAREHOUSE_VOLUME));
    }

    @Test
    public void itIsPossibleToAddProductWithVolumeEqualToRemainingVolume() {
        Product product = new Product(id(), WAREHOUSE_VOLUME);

        WarehousePlacementResult result = warehouse.placeProduct(product, 1);

        assertThat(result.getStatus()).isEqualTo(SUCCESS);
        assertThat(warehouse.getRemainingVolume()).isEqualTo(0);
    }
}