package model;

import exceptions.ElementsInSameBoxException;
import exceptions.MapException;

import java.util.HashMap;

public class TreasureMap {
    private final int length;
    private final int width;
    private final HashMap<Box, MapElement> objectMap = new HashMap<>();

    private final HashMap<Box, Adventurer> adventurersMap = new HashMap<>();

    public TreasureMap(int length, int width) {
        this.length = length;
        this.width = width;
    }

    /**
     * add an element to the map
     * checks if there is no other element at the place
     * @param element element to add
     * @throws ElementsInSameBoxException, MapException
     */
    public void addElement(MapElement element) throws ElementsInSameBoxException, MapException {
        guardAgainstElementNotInMapBoundaries(element);

        final var coordinates = getCoordinates(element);

        if (objectMap.containsKey(coordinates)
                || (adventurersMap.containsKey(coordinates) && element.getType().equals("M"))) {
            throw new ElementsInSameBoxException("Cannot add two elements on same box");
        }
        objectMap.put(coordinates, element);
    }

    /**
     * add an adventurer to the map
     * checks there is no mountain and if there is a treasure
     * @param adventurer
     * @throws ElementsInSameBoxException, MapException
     */
    public void addAdventurer(Adventurer adventurer) throws ElementsInSameBoxException, MapException {
        guardAgainstElementNotInMapBoundaries(adventurer);

        final var coordinates = getCoordinates(adventurer);

        if (adventurersMap.containsKey(coordinates)
                || (objectMap.containsKey(coordinates) && objectMap.get(coordinates).getType().equals("M"))) {
            throw new ElementsInSameBoxException("Cannot add two elements on same box");
        }
        checkAndCollectTreasures(adventurer);
        adventurersMap.put(coordinates, adventurer);
    }

    /**
     * check if there is a treasure on the box of the adventurer
     * if it is the case it collects it
     * @param adventurer
     */
    private void checkAndCollectTreasures(Adventurer adventurer) {
        final var box = new Box(adventurer.x(), adventurer.y());

        if (objectMap.containsKey(box)) {
            if (objectMap.get(box).getType().equals("T")) {
                final var treasure = (Treasure) objectMap.get(box);
                adventurer.increaseNbTreasures(treasure.collect());
            }
        }
    }

    private Box getCoordinates(MapElement element) {
        return new Box(element.x(), element.y());
    }

    /**
     * checks if the element is contained on the map
     * @param element element to check
     * @throws MapException
     */
    private void guardAgainstElementNotInMapBoundaries(MapElement element) throws MapException {
        if (element.x() >= length || element.x() < 0 || element.y() >= width || element.y() < 0)
            throw new MapException("Not in map boundaries");
    }

    public HashMap<Box, MapElement> getObjectMap() {
        return objectMap;
    }

    public HashMap<Box, Adventurer> getAdventurersMap() {
        return adventurersMap;
    }

    /**
     * check if there is an adventurer on the box
     * @param box box of the adventurer
     * @return true if there is no obstacles
     */
    public boolean checkBoxAddAdventurer(Box box) {
        if (this.adventurersMap.containsKey(box))
            return false;
        if (this.objectMap.get(box) != null && this.objectMap.get(box).getType().equals("M"))
            return false;
        return true;
    }

    /**
     * remove an adventurer of the map
     * @param adventurer to remove
     */
    public void removeAdventurer(Adventurer adventurer) {
        final var box = new Box(adventurer.x(), adventurer.y());
        this.adventurersMap.remove(box, adventurer);
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public String toString() {
        return "C - " + this.length + " - " + this.width;
    }
}
