public enum ShipType {
    CARRIER (5),
    BATTLESHIP (4),
    DESTROYER (3),
    SUBMARINE (3),
    PATROLBOAT (2);

    private final int shipType;

    ShipType(int shipType) {
        this.shipType = shipType;
    }

    public int getShipType() {
        return this.shipType;
    }
}

