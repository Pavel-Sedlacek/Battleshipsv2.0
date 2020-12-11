public class Player {
    private Battlefield b;

    public Player(int size, Ship[] ships) {
        b = new Battlefield(size, ships);
    }

    public Field[][] reportField() {
        return b.reportField();
    }

    public boolean isAlive() {
        return b.hasShips();
    }

    public String receiveFire(int x, int y) {
        b.takeHit(x,y);
        return b.reportField()[x][y].toString();
    }

    public boolean isValid(int x, int y) {
        return reportField()[x][y].getField().equals("Water") || reportField()[x][y].getField().equals("Ship");
    }
}
