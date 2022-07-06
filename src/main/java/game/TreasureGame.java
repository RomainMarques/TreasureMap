package game;

import exceptions.AdventurerMovementException;
import exceptions.ElementsInSameBoxException;
import exceptions.MapException;
import model.Adventurer;
import model.Box;
import model.TreasureMap;

import java.util.HashMap;
import java.util.Map;

public class TreasureGame {
    private final TreasureMap map;

    public TreasureGame(TreasureMap map) {
        this.map = map;
    }

    public void execute() throws MapException, ElementsInSameBoxException, AdventurerMovementException {
        for (int i = 0; i < getMaxMovements(map.getAdventurersMap()); i++) {
            for (Map.Entry<Box, Adventurer> adventurer : map.getAdventurersMap().entrySet()) {
                if (adventurer.getValue().getMovements().length() <= i) {
                    continue;
                }
                switch (adventurer.getValue().getMovements().charAt(i)) {
                    case 'G' -> adventurer.getValue().moveLeft();
                    case 'D' -> adventurer.getValue().moveRight();
                    case 'A' -> {
                        Box box = adventurer.getValue().move(adventurer.getKey());
                        if (this.map.checkBoxAddAdventurer(box)) {
                            this.map.removeAdventurer(adventurer.getValue());
                            adventurer.getValue().moveAdventurer();
                            this.map.addAdventurer(adventurer.getValue());
                        }
                    }
                }
            }
        }
    }

    /**
     *  get the max of movements of the adventurers
     * @param adventurers hashmap which contains adventurers
     * @return the maximum of movements of the adventurers
     */
    public int getMaxMovements(HashMap<Box, Adventurer> adventurers) {
        return adventurers.values().stream()
                .mapToInt(adventurer -> adventurer.getMovements().length())
                .max()
                .orElse(0);
    }
}
