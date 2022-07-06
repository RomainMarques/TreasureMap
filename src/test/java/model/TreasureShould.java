package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TreasureShould {
    @Test
    @DisplayName("Should decrease the amount of treasures")
    public void collect() {
        //Arrange
        Treasure treasure = new Treasure(1,1,1);
        //Act
        treasure.collect();
        //Assert
        Assertions.assertEquals(0, treasure.getNbTreasures());
    }

    @Test
    @DisplayName("Should not decrease the amount of treasures")
    public void collectWithNoTreasure() {
        //Arrange
        Treasure treasure = new Treasure(1,1,0);
        //Act
        treasure.collect();
        //Assert
        Assertions.assertEquals(0, treasure.getNbTreasures());
    }
}
