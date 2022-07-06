package model;

import exceptions.ElementsInSameBoxException;
import exceptions.MapException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TreasureMapShould {
    @Test
    @DisplayName("Should add the element to the map")
    public void addElement() throws ElementsInSameBoxException, MapException {
        // Arrange
        TreasureMap treasureMap = new TreasureMap(2,2);
        MapElement mapElement = new Treasure(1,1,1);
        // Act
        treasureMap.addElement(mapElement);
        // Assert
        Assertions.assertEquals(mapElement, treasureMap.getObjectMap().get(new Box(1,1)));
    }

    @Test
    @DisplayName("Should throw an exception when add two elements on same box")
    public void addElementOnSameBox() throws ElementsInSameBoxException, MapException {
        // Arrange
        TreasureMap treasureMap = new TreasureMap(2,2);
        MapElement mapElement1 = new Treasure(1,1,1);
        MapElement mapElement2 = new Treasure(1,1,1);
        // Act
        treasureMap.addElement(mapElement1);
        Exception exception = assertThrows(ElementsInSameBoxException.class, () -> treasureMap.addElement(mapElement2));
        String expectedMessage = "Cannot add two elements on same box";
        String actualMessage = exception.getMessage();
        // Assert
        Assertions.assertEquals(expectedMessage, actualMessage);
    }
    @Test
    @DisplayName("Should throw an exception when add an element not on the map boundaries")
    public void addElementNotOnTheMap() {
        // Arrange
        TreasureMap treasureMap = new TreasureMap(2,2);
        MapElement mapElement = new Treasure(2,2,1);
        // Act
        Exception exception = assertThrows(MapException.class, () -> treasureMap.addElement(mapElement));
        String expectedMessage = "Not in map boundaries";
        String actualMessage = exception.getMessage();
        // Assert
        Assertions.assertEquals(expectedMessage, actualMessage);
    }
    @Test
    @DisplayName("Should add an adventurer to the map")
    public void addAdventurer() throws ElementsInSameBoxException, MapException {
        // Arrange
        TreasureMap treasureMap = new TreasureMap(2,2);
        Adventurer adventurer = new Adventurer("Toto", 1,1,'S', "EN");
        // Act
        treasureMap.addAdventurer(adventurer);
        // Assert
        Assertions.assertEquals(adventurer, treasureMap.getAdventurersMap().get(new Box(1,1)));
    }

    @Test
    @DisplayName("Should throw an exception when add two adventurers on same box")
    public void addAdventurerOnSameBox() throws ElementsInSameBoxException, MapException {
        // Arrange
        TreasureMap treasureMap = new TreasureMap(2,2);
        Adventurer adventurer1 = new Adventurer("Toto", 1,1,'S', "EN");
        Adventurer adventurer2 = new Adventurer("Toto", 1,1,'S', "EN");
        // Act
        treasureMap.addAdventurer(adventurer1);
        Exception exception = assertThrows(ElementsInSameBoxException.class,
                () -> treasureMap.addAdventurer(adventurer2));
        String expectedMessage = "Cannot add two elements on same box";
        String actualMessage = exception.getMessage();
        // Assert
        Assertions.assertEquals(expectedMessage, actualMessage);
    }
    @Test
    @DisplayName("Should throw an exception when add an adventurer not on the map boundaries")
    public void addAdventurerNotOnTheMap() {
        // Arrange
        TreasureMap treasureMap = new TreasureMap(2,2);
        Adventurer adventurer = new Adventurer("Toto", 2,2,'S', "EN");
        // Act
        Exception exception = assertThrows(MapException.class, () -> treasureMap.addAdventurer(adventurer));
        String expectedMessage = "Not in map boundaries";
        String actualMessage = exception.getMessage();
        // Assert
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @ParameterizedTest
    @CsvSource({"1,1,0", "0,0,0"})
    @DisplayName("Should collect the treasures in the first case and do not do it in the second")
    public void checkAndCollectTreasures(int input, int expected1, int expected2) throws ElementsInSameBoxException,
            MapException {
        // Arrange
        TreasureMap treasureMap = new TreasureMap(2,2);
        Treasure treasure = new Treasure(1,1,input);
        Adventurer adventurer = new Adventurer("toto",1 ,1, 'S', "EN");
        // Act
        treasureMap.addElement(treasure);
        treasureMap.addAdventurer(adventurer);
        // Assert
        Assertions.assertEquals(expected1, adventurer.getNbTreasures());
        Assertions.assertEquals(expected2, treasure.getNbTreasures());
    }

    @ParameterizedTest
    @CsvSource({"1,1,1,1", "1,1,0,0"})
    @DisplayName("Should check if there is an adventurer on the box")
    public void checkBoxAdventurer(int input1,  int input2, int input3, int input4) throws ElementsInSameBoxException,
            MapException {
        // Arrange
        TreasureMap treasureMap = new TreasureMap(2,2);
        Box box = new Box(input1, input2);
        Adventurer adventurer = new Adventurer("toto", input3, input4, 'S', "NE");
        // Act
        treasureMap.addAdventurer(adventurer);
        // Assert
        Assertions.assertEquals(input1!=input3, treasureMap.checkBoxAddAdventurer(box));
    }

    @Test
    @DisplayName("Should remove the adventurer from the map")
    public void removeAdventurer() throws ElementsInSameBoxException, MapException {
        // Arrange
        TreasureMap treasureMap = new TreasureMap(2,2);
        Adventurer adventurer = new Adventurer("toto", 1,1,'S', "SE");
        Box box = new Box(1,1);
        // Act
        treasureMap.addAdventurer(adventurer);
        treasureMap.removeAdventurer(adventurer);
        // Assert
        Assertions.assertNull(treasureMap.getAdventurersMap().get(box));
    }
}
