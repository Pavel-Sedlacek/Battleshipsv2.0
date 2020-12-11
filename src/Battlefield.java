import java.util.Arrays;

public class Battlefield {

    private final Field[][] field;

    public Battlefield(int size, Ship[] ships) {
        this.field = new Field[size][size];
        for (Field[] field : field) {
            Arrays.fill(field, Field.water());
        }
        for (Ship s : ships) {
            addShip(s);
        }
    }

    private void addShip(Ship s) {
        if (s.isHorizontal()) {
            for (int i = 0; i < s.getType().getShipSize(); i++) {
                field[s.getX() + i][s.getY()] = Field.ship();
            }
        } else {
            for (int i = 0; i < s.getType().getShipSize(); i++) {
                field[s.getX()][s.getY() + i] = Field.ship();
            }
        }
    }

    public void takeHit(int x, int y) {
        if (field[x][y].getField().equals("Ship")) {
            field[x][y] = Field.hit();
        } else {
            field[x][y] = Field.miss();
        }
    }

    public Field[][] reportField() {
        return this.field;
    }

    public boolean hasShips() {
        for (Field[] fa : field) {
            for (Field f : fa) {
                if (f.getField().equals("Ship"))
                    return true;
            }
        }
        return false;
    }
}
