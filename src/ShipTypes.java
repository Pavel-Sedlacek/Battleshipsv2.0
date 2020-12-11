public class ShipTypes {

    private final int shipSize;

    private ShipTypes(int shipSize) {
        this.shipSize = shipSize;
    }

    public static ShipTypes ship5() {
        return new ShipTypes(5);
    }

    public static ShipTypes ship4() {
        return new ShipTypes(4);
    }

    public static ShipTypes ship3() {
        return new ShipTypes(3);
    }

    public static ShipTypes ship2() {
        return new ShipTypes(2);
    }

    public static ShipTypes pickRandom() {
        return new ShipTypes((int) (Math.random() * 3 + 2));
    }

    public int getShipSize() {
        return this.shipSize;
    }
}
