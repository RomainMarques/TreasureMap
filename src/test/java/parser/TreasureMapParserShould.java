package parser;

import model.Adventurer;
import model.Mountain;
import model.Treasure;
import model.TreasureMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TreasureMapParserShould {
    @Test
    @DisplayName("Should create a map")
    public void mapParser() {
        // Arrange
        String line = "C - 3 - 4";
        // Act
        TreasureMap treasureMap = TreasureMapParser.mapParser(line);
        // Assert
        Assertions.assertEquals(3, treasureMap.getLength());
        Assertions.assertEquals(4, treasureMap.getWidth());
    }

    @ParameterizedTest
    @CsvSource({"1,0","2,1"})
    @DisplayName("Should create a mountain")
    public void mountainParser(int input1, int input2) {
        // Arrange
        StringBuilder sb = new StringBuilder("M - ");
        sb.append(input1);
        sb.append(" - ");
        sb.append(input2);
        // Act
        Mountain mountain = TreasureMapParser.mountainParser(sb.toString());
        // Assert
        Assertions.assertEquals(input1, mountain.x());
        Assertions.assertEquals(input2, mountain.y());
    }

    @ParameterizedTest
    @CsvSource({"0,3,2", "1,3,3"})
    @DisplayName("Should create a treasure")
    public void treasureParser(int input1, int input2, int input3) {
        // Arrange
        StringBuilder sb = new StringBuilder("T - ");
        sb.append(input1);
        sb.append(" - ");
        sb.append(input2);
        sb.append(" - ");
        sb.append(input3);
        // Act
        Treasure treasure = TreasureMapParser.treasureParser(sb.toString());
        // Assert
        Assertions.assertEquals(input1, treasure.x());
        Assertions.assertEquals(input2, treasure.y());
        Assertions.assertEquals(input3, treasure.getNbTreasures());
    }

    @Test
    @DisplayName("Should create an adventurer")
    public void adventurerParser() {
        // Arrange
        String line = "A - Lara - 1 - 1 - S - AADADAGGA";
        // Act
        Adventurer adventurer = TreasureMapParser.adventurerParser(line);
        // Assert
        Assertions.assertEquals("Lara", adventurer.getName());
        Assertions.assertEquals(1, adventurer.x());
        Assertions.assertEquals(1, adventurer.y());
        Assertions.assertEquals('S', adventurer.getDirection());
        Assertions.assertEquals("AADADAGGA", adventurer.getMovements());
    }
}
