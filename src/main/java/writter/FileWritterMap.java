package writter;

import exceptions.MapException;
import model.Adventurer;
import model.Mountain;
import model.Treasure;
import model.TreasureMap;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class FileWritterMap {
    public static final String ADVENTURERS_DOC_LINE = "# {A comme Aventurier} - {Nom de l’aventurier} " +
            "- {Axe horizontal} - {Axe" + "vertical} - {Orientation} - {Nb. trésors ramassés}";
    public static final String TREASURE_DOC_LINE = "# {T comme Trésor} - {Axe horizontal} - {Axe vertical} - " +
            "{Nb. de trésors restants}";
    private final TreasureMap map;

    /**
     *
     * @param map map which contains the map elements and the adventurers
     */
    public FileWritterMap(TreasureMap map) {
        this.map = map;
    }

    /**
     * method which writes into a file
     * @param filename name of the file to write
     * @return true if there is no problem
     * @throws MapException
     */
    public boolean writeFile(String filename) throws MapException {
        try {
            PrintWriter writer = new PrintWriter(filename, StandardCharsets.UTF_8);

            writeMap(writer);
            writeMountains(writer);
            writeTreasures(writer);
            writeAdventurers(writer);

            writer.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    private void writeMap(PrintWriter writer) throws MapException {
        if (this.map == null) {
            throw new MapException("There is no map");
        }
        writer.println(map);
    }

    private void writeAdventurers(PrintWriter writer) {
        List<Adventurer> adventurers = getAdventurers();
        if (adventurers.size() > 0) {
            writer.println(ADVENTURERS_DOC_LINE);
        }
        for (Adventurer a : adventurers) {
            writer.println(a);
        }
    }

    private void writeTreasures(PrintWriter writer) {
        List<Treasure> treasures = getTreasures();
        if (treasures.size() > 0) {
            writer.println(TREASURE_DOC_LINE);
        }
        for (Treasure t : treasures) {
            writer.println(t);
        }
    }

    private void writeMountains(PrintWriter writer) {
        for (Mountain m : getMountains()) {
            writer.println(m);
        }
    }

    /**
     * @return a list the mountains of the map
     */
    private List<Mountain> getMountains() {
        return map.getObjectMap().values().stream()
                .filter(o -> o.getType().equals("M"))
                .map(m -> (Mountain) m)
                .toList();
    }

    /**
     * @return a list of the treasures of the map
     */
    private List<Treasure> getTreasures() {
        return map.getObjectMap().values().stream()
                .filter(o -> o.getType().equals("T"))
                .map(m -> (Treasure) m)
                .toList();
    }

    /**
     * @return a list of the adventurers of the map
     */
    private List<Adventurer> getAdventurers() {
        return map.getAdventurersMap().values().stream()
                .toList();
    }
}
