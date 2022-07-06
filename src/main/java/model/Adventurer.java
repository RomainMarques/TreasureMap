package model;

import exceptions.AdventurerMovementException;

/**
 * Class which represents an adventurer
 */
public class Adventurer implements MapElement {
    private static final String SYMBOL = "A";
    String name;
    private int x, y;
    private char direction;
    private final String movements;

    private int nbTreasures = 0;

    /**
     * Constructor model.Adventurer
     * @param name name of the adventurer
     * @param x longitude of the adventurer
     * @param y latitude of the adventurer
     * @param direction direction of the adventurer
     * @param movements movements of the adventurer
     */
    public Adventurer(String name, int x, int y, char direction, String movements) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.direction = direction;
        this.movements = movements;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return SYMBOL;
    }

    public char getDirection() {
        return direction;
    }

    public int getNbTreasures() {
        return nbTreasures;
    }

    public String getMovements() {
        return movements;
    }

    private void setDirection(char direction) {
        this.direction = direction;
    }

    /**
     * increase the number of collected treasures
     * @param nbTreasures the amount of treasure to increase
     */
    public void increaseNbTreasures(int nbTreasures) {
        this.nbTreasures += nbTreasures;
    }

    public String toString() {
        return "A - " + this.getName() + " - " + this.x() + " - " + this.y() + " - " + this.getDirection() + " - "
                + this.getNbTreasures();
    }

    /**
     * change the direction of the adventurer
     * @throws AdventurerMovementException
     */
    public void moveLeft() throws AdventurerMovementException {
        switch (getDirection()) {
            case 'S' -> setDirection('O');
            case 'E' -> setDirection('S');
            case 'N' -> setDirection('E');
            case 'O' -> setDirection('N');
            default -> throw new AdventurerMovementException("Unclear direction");
        }
    }

    /**
     * change the direction of the adventurer
     * @throws AdventurerMovementException
     */
    public void moveRight() throws AdventurerMovementException {
        switch (getDirection()) {
            case 'S' -> setDirection('E');
            case 'E' -> setDirection('N');
            case 'N' -> setDirection('O');
            case 'O' -> setDirection('S');
            default -> throw new AdventurerMovementException("Unclear direction");
        }
    }

    /**
     * create a new box with the movement of the adventurer moved
     * @param initialPos the position of the box before movement
     * @return the box moved
     * @throws AdventurerMovementException
     */
    public Box move(Box initialPos) throws AdventurerMovementException {
        return switch (getDirection()) {
            case 'S' -> new Box(initialPos.x(), initialPos.y() + 1);
            case 'E' -> new Box(initialPos.x() - 1, initialPos.y());
            case 'N' -> new Box(initialPos.x(), initialPos.y() - 1);
            case 'O' -> new Box(initialPos.x() + 1, initialPos.y());
            default -> throw new AdventurerMovementException("Unclear direction");
        };
    }

    /**
     * move the adventurer
     * @throws AdventurerMovementException
     */
    public void moveAdventurer() throws AdventurerMovementException {
        switch (getDirection()) {
            case 'S' -> y++;
            case 'E' -> x--;
            case 'N' -> y--;
            case 'O' -> x++;
            default -> throw new AdventurerMovementException("Unclear direction");
        };
    }
}
