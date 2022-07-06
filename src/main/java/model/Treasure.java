package model;

public class Treasure implements MapElement {
    private static final String SYMBOL = "T";
    private final int x, y;
    private int nbTreasures;

    public Treasure(int x, int y, int nbTreasures) {
        this.x = x;
        this.y = y;
        this.nbTreasures = nbTreasures;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public String getType() {
        return SYMBOL;
    }

    public int getNbTreasures() {
        return nbTreasures;
    }

    /**
     * Collect a treasure
     *
     * @return 1 if there is a treasure to collect, 0 else way
     */
    public int collect() {
        if (nbTreasures > 0) {
            nbTreasures--;
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "T - " + this.x() + " - " + this.y() + " - " + this.getNbTreasures();
    }
}
