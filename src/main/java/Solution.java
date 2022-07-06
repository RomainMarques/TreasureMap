import game.TreasureGame;
import reader.FileReaderMap;
import writter.FileWritterMap;

public class Solution {

    public static final String ENTRY_TREASURE_MAP_PATH = "./src/main/resources/TresorMap1";
    public static final String RESULT_TREASURE_MAP_PATH = "./src/main/resources/TestMap";

    public static void main(String[] args) {
        FileReaderMap read = new FileReaderMap(ENTRY_TREASURE_MAP_PATH);

        try {
            final var map = read.readFile();
            TreasureGame game = new TreasureGame(map);
            game.execute();
            FileWritterMap writer = new FileWritterMap(map);
            writer.writeFile(RESULT_TREASURE_MAP_PATH);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
