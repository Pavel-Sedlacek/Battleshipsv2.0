public class Field {
    private final String field;

    private Field(String type) {
        this.field = type;
    }

    public static Field water() {
        return new Field("water");
    }

    public static Field hit() {
        return new Field("Hit");
    }

    public static Field miss() {
        return new Field("Miss");
    }

    public static Field ship() {
        return new Field("ship");
    }

    public String getField() {
        return this.field;
    }
}
