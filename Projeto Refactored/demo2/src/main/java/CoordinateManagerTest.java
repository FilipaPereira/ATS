package main.java;
import org.junit.Test;

public class CoordinateManagerTest {

    @Test
    public void isValidLatitude(){
        CoordinateManager c = new CoordinateManager();
        double latitude = Double.valueOf("45.0000");
        assertEquals(c.isValidLatitude(latitude), true);
    }

    @Test
    public void isValidLongitude(){
        CoordinateManager c = new CoordinateManager();
        double longitude = Double.valueOf("150.0000");
        assertEquals(c.isValidLongitude(longitude), true);
    }
/*
    @Test
    public void addDistanceNorth() {
    }

    @Test
    public void addDistanceSouth() {
    }

    @Test
    public void addDistanceEast() {
    }

    @Test
    public void addDistanceWest() {
    }
    */
}