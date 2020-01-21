package main.java.UnitTests;
import main.java.*;
import org.junit.Test;
import static org.junit.Assert.*;


public class CoordinateManagerTest {

    @Test
    public void isValidLatitude(){
        double latitude = 45.9875;
        assertTrue(CoordinateManager.isValidLatitude(latitude));
    }

    @Test
    public void isValidLongitude(){
        double longitude = 132.42;
        assertTrue(CoordinateManager.isValidLongitude(longitude));
    }

    @Test
    public void addDistanceNorth() {
        int dist = 2350;
        Coordinate c = CoordinateManager.addDistanceNorth(45,45,dist);
        double res = 45.021110409176806;
        assertEquals(c.getLatitude(), res, 0.1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addDistanceSouth() {
        int dist = -2;
        CoordinateManager.addDistanceSouth(34,54.9,dist);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addDistanceEast() {
        int dist = 6000;
        CoordinateManager.addDistanceEast(93,188,dist);
    }

    @Test
    public void addDistanceWest() {
        int dist = 6000;
        Coordinate c = CoordinateManager.addDistanceEast(22,-120,dist);
        double res = -119.94609897145521;
        assertEquals(c.getLongitude(), res, 0.1);
    }
}