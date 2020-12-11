public class Ship {
    private final boolean horizontal;
    private final int x;
    private final int y;
    private final ShipTypes type;

    public Ship(boolean horizontal, int x, int y, ShipTypes type) {
        this.horizontal = horizontal;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public boolean isHorizontal() {
        return this.horizontal;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public ShipTypes getType() {
        return this.type;
    }
}
