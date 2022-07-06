package model;

public record Mountain(int x, int y) implements MapElement {
    private static final String SYMBOL = "M";

    public String getType() {
        return SYMBOL;
    }

    @Override
    public String toString() {
        return "M - " + this.x() + " - " + this.y();
    }
}
