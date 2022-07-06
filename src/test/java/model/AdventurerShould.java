package model;

import exceptions.AdventurerMovementException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class AdventurerShould {
    @Test
    @DisplayName("Should increase number of collected treasure")
    public void increaseNumberOfTreasure() {
        // Arrange
        final var adventurer = new Adventurer("toto", 1, 1, 'S', "AB");
        // Act
        adventurer.increaseNbTreasures(1);
        // Assert
        Assertions.assertEquals(1, adventurer.getNbTreasures());
    }

    @ParameterizedTest
    @CsvSource({"S,O", "E, S", "N, E", "O, N"})
    @DisplayName("Should move left")
    public void moveLeft(char input, char expected) throws AdventurerMovementException {
        // Arrange
        final var adventurer = new Adventurer("toto", 1, 1, input, "AB");
        // Act
        adventurer.moveLeft();
        // Assert
        Assertions.assertEquals(expected, adventurer.getDirection());
    }

    @ParameterizedTest
    @CsvSource({"S,E", "E, N", "N, O", "O, S"})
    @DisplayName("Should move right")
    public void moveRight(char input, char expected) throws AdventurerMovementException {
        // Arrange
        final var adventurer = new Adventurer("toto", 1, 1, input, "AB");
        // Act
        adventurer.moveRight();
        // Assert
        Assertions.assertEquals(expected, adventurer.getDirection());
    }

    @ParameterizedTest
    @CsvSource({"S,1,2", "E,0,1", "N,1,0", "O,2,1"})
    @DisplayName("Should move adventurer")
    public void moveAdventurer(char input, int expected1, int expected2) throws AdventurerMovementException {
        // Arrange
        final var adventurer = new Adventurer("toto", 1, 1, input, "AB");
        // Act
        adventurer.moveAdventurer();
        // Assert
        Assertions.assertEquals(expected1, adventurer.x());
        Assertions.assertEquals(expected2, adventurer.y());
    }
    @Test
    @DisplayName("Should send an exception")
    public void moveAdventurer() throws AdventurerMovementException {
        // Arrange
        final var adventurer = new Adventurer("toto", 1, 1, 'A', "AB");
        // Act
        Exception exception = assertThrows(AdventurerMovementException.class, adventurer::moveAdventurer);
        String expectedMessage = "Unclear direction";
        String actualMessage = exception.getMessage();
        // Assert
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @ParameterizedTest
    @CsvSource({"S,1,2", "E,0,1", "N,1,0", "O,2,1"})
    @DisplayName("Should move")
    public void move(char input, int expected1, int expected2) throws AdventurerMovementException {
        // Arrange
        final var adventurer = new Adventurer("toto", 1, 1, input, "AB");
        // Act
        Box box = adventurer.move(new Box(adventurer.x(), adventurer.y()));
        // Assert
        Assertions.assertEquals(expected1, box.x());
        Assertions.assertEquals(expected2, box.y());
    }

    @Test
    @DisplayName("Should send an exception")
    public void move() {
        // Arrange
        final var adventurer = new Adventurer("toto", 1, 1, 'A', "AB");
        // Act
        Exception exception = assertThrows(AdventurerMovementException.class, () -> {
            adventurer.move(new Box(adventurer.x(), adventurer.y()));
        });
        String expectedMessage = "Unclear direction";
        String actualMessage = exception.getMessage();
        // Assert
        Assertions.assertEquals(expectedMessage, actualMessage);
    }
}
