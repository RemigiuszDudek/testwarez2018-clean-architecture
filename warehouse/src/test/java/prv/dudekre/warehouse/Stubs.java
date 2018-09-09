package prv.dudekre.warehouse;

import java.util.UUID;

public final class Stubs {
    private Stubs() {
    }

    public static String id() {
        return UUID.randomUUID().toString();
    }
}
