package reader;

import exceptions.MapException;
import model.TreasureMap;
import parser.TreasureMapParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
 * Class which reads the file and return the map
 */
public class FileReaderMap {
    private final String filename;

    public FileReaderMap(String filename) {
        this.filename = filename;
    }

    /**
     * Read a file and return the map created from that file
     * @return an instance of the map read
     * @throws Exception
     */
    public TreasureMap readFile() throws Exception {
        TreasureMap map = null;
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(this.filename));
            String line;
            while ((line = reader.readLine()) != null) {
                switch(line.charAt(0)) {
                    case '#' :
                        continue;
                    case 'C' :
                        if (map != null) {
                            throw new MapException("There is already a map");
                        }
                        map = TreasureMapParser.mapParser(line);
                        break;
                    case 'T' :
                        guardAgainstNoMap(map);
                        map.addElement(TreasureMapParser.treasureParser(line));
                        break;
                    case 'A' :
                        guardAgainstNoMap(map);
                        map.addAdventurer(TreasureMapParser.adventurerParser(line));
                        break;
                    case 'M' :
                        guardAgainstNoMap(map);
                        map.addElement(TreasureMapParser.mountainParser(line));
                        break;
                    default :
                        break;
                }
            }
            reader.close();
            return map;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Method which checks if the map exists, throws an exception if it is not the case
     * @param map
     * @throws Exception
     */
    private void guardAgainstNoMap(TreasureMap map) throws MapException {
        if (map == null) {
            throw new MapException("There is no map");
        }
    }
}
