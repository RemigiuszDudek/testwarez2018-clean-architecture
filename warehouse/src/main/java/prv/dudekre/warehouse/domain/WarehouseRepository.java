package prv.dudekre.warehouse.domain;

public interface WarehouseRepository {
    Warehouse get();
    void save(Warehouse warehouse);
}
