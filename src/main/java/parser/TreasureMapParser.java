package parser;

import model.Adventurer;
import model.Mountain;
import model.Treasure;
import model.TreasureMap;

/**
 * Class Parser which returns an instance of the wanted element
 */
public class TreasureMapParser {

    public static final String PARSER_REGEX = " - ";

    /**
     * Method which takes a line and decomposes it into a map
     * @param line line to traduce into a map
     * @return the map
     */
    public static TreasureMap mapParser(String line) {
        final var str = splitLine(line);
        return new TreasureMap(Integer.parseInt(str[1]), Integer.parseInt(str[2]));
    }

    /**
     * Method which takes a line and decomposes it into a map
     * @param line line to traduce into coordinates
     * @return a mountain
     */
    public static Mountain mountainParser(String line) {
        final var str = splitLine(line);
        return new Mountain(Integer.parseInt(str[1]), Integer.parseInt(str[2]));
    }

    /**
     *  Method which takes a line and decomposes it into a treasure
     * @param line line to traduce in a treasure
     * @return a treasure
     */
    public static Treasure treasureParser(String line) {
        final var str = splitLine(line);
        return new Treasure(Integer.parseInt(str[1]), Integer.parseInt(str[2]), Integer.parseInt(str[3]));
    }

    /**
     * Method which takes a line and decomposes it into an adventurer
     * @param line line to traduce in an adventurer
     * @return an adventurer
     */
    public static Adventurer adventurerParser(String line) {
        final var str = splitLine(line);
        return new Adventurer(str[1], Integer.parseInt(str[2]), Integer.parseInt(str[3]), str[4].charAt(0), str[5]);
    }

    /**
     * Takes a line and split it according to the regex
     * @param line to split into an array
     * @return the array split
     */
    private static String[] splitLine(String line) {
        return line.split(PARSER_REGEX);
    }
}
