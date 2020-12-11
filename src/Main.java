import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {

    private static int fieldSize;
    private static int shipCount;
    private static ShipTypes[] shipTypes;
    private static Scanner sc;

    public static void main(String[] args) {
        sc = new Scanner(System.in);

        System.out.print("Choose field size : ");
        fieldSize = sc.nextInt();

        System.out.print("Choose ship count : ");
        shipCount = sc.nextInt();

        System.out.println("Randomizing ships");
        shipTypes = new ShipTypes[shipCount];
        IntStream.range(0, shipCount).forEach(i -> shipTypes[i] = ShipTypes.pickRandom());

        System.out.println("Press any key to place ships for Pl1");
        sc.nextLine();
        sc.nextLine();
        Player p1 = new Player(fieldSize, chooseShips());
        reportField(p1);

        System.out.println("press any key to hide map");
        sc.nextLine();
        sc.nextLine();

        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\nPress any key to place ships for Pl2");
        sc.nextLine();
        Player p2 = new Player(fieldSize, chooseShips());
        reportField(p2);

        System.out.println("press any key to hide map");
        sc.nextLine();
        sc.nextLine();

        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\nPress any key to begin fight");
        sc.nextLine();

        int x;
        int y;
        while (p1.isAlive() && p2.isAlive()) {
            System.out.println("Player 1 choose target location (x,y)");
            do {
                x = sc.nextInt();
                y = sc.nextInt();
                if (isValid(x, y, p2)) {
                    System.out.println("You " + p2.receiveFire(x, y));
                    break;
                }
            }
            while (!isValid(x, y, p2));
            reportEnemyField(p2);

            System.out.println("Player 2 choose target location (x,y)");
            do {
                x = sc.nextInt();
                y = sc.nextInt();
                if (isValid(x, y, p1)) {
                    System.out.println("You " + p1.receiveFire(x, y));
                    break;
                }
            }
            while (!isValid(x, y, p1));
            reportEnemyField(p1);
        }
        System.out.println((p1.isAlive()) ? "Player 1 wins!" : "Player 2 wins!");
    }

    private static void reportEnemyField(Player pl) {
        Field[][] x = pl.reportField();
        for (Field[] d : x) {
            for (Field f : d) {
                if (f.getField().equals("HIT") || f.getField().equals("Miss")) {
                    System.out.print(f.toString().charAt(0) + "  |  ");
                } else {
                    System.out.print("?  |  ");
                }
            }
            System.out.println("\n-------------------------------------");
        }
    }

    private static Ship[] chooseShips() {
        System.out.println("Input format : \n    - [BOOLEAN] for direction (true > vertical / false > horizontal) \n    - [0 - " + (fieldSize - 1) + "] for top left x \n    - [0 - " + (fieldSize - 1) + "] for top left y \npay attention when using low x or y values on your rotation");
        Ship[] ships = new Ship[shipCount];
        for (int i = 0; i < ships.length; i++) {
            System.out.println("Choose [direction], [x] and [y] for : " + shipTypes[i].getShipSize());
            ships[i] = new Ship(sc.nextBoolean(), sc.nextInt(), sc.nextInt(), shipTypes[i]);
            if (checkValid(ships)) {
                i--;
                System.out.println("Invalid placement");
            }
        }
        return ships;
    }

    private static boolean checkValid(Ship[] ships) {
        FieldType[][] test = new FieldType[fieldSize][fieldSize];
        try {
            for (Ship s : ships) {
                if (s != null) {
                    if (!s.isHorizontal()) {
                        for (int i = 0; i < s.getType().getShipSize(); i++) {
                            if (test[s.getX()][s.getY() + i] == FieldType.SHIP) {
                                return true;
                            }
                            test[s.getX()][s.getY() + i] = FieldType.SHIP;
                        }
                    } else {
                        for (int i = 0; i < s.getType().getShipSize(); i++) {
                            if (test[s.getX() + i][s.getY()] == FieldType.SHIP) {
                                return true;
                            }
                            test[s.getX() + i][s.getY()] = FieldType.SHIP;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
        return false;
    }

    public static boolean isValid(int x, int y, Player targeted) {
        return (x >= 0 && x < fieldSize && y >= 0 && y < fieldSize) && targeted.isValid(x, y);
    }

    public static void reportField(Player pl) {
        Field[][] x = pl.reportField();
        for (Field[] d : x) {
            for (Field f : d) {
                System.out.print(f.toString().charAt(0) + "  |  ");
            }
            System.out.println("\n-------------------------------------");
        }
    }
}
