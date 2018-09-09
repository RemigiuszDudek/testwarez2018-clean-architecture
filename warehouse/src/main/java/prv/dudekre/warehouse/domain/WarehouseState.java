package prv.dudekre.warehouse.domain;

public final class WarehouseState {
    private final int remainingVolume;

    public WarehouseState(int remainingVolume) {
        this.remainingVolume = remainingVolume;
    }

    public int getRemainingVolume() {
        return remainingVolume;
    }
}
