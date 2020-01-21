/*
 * This file was automatically generated by EvoSuite
 * Tue Jan 14 17:52:16 GMT 2020
 */

package main.java;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.util.HashMap;
import main.java.Coordinate;
import main.java.CoordinateManager;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class CoordinateManager_ESTest extends CoordinateManager_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      HashMap<String, Coordinate> hashMap0 = CoordinateManager.getBoundingBox((-4.365326135898071), 0.0, 862);
      assertEquals(4, hashMap0.size());
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      // Undeclared exception!
      try { 
        CoordinateManager.addDistanceWest((-1.0), (-1.0), 0);
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // All parameters are required and must be valid
         //
         verifyException("main.java.CoordinateManager", e);
      }
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      // Undeclared exception!
      try { 
        CoordinateManager.addDistanceEast(0.0, 0.0, 0);
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // All parameters are required and must be valid
         //
         verifyException("main.java.CoordinateManager", e);
      }
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      // Undeclared exception!
      try { 
        CoordinateManager.addDistanceSouth(2.0, 2.0, 0);
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // All parameters are required and must be valid
         //
         verifyException("main.java.CoordinateManager", e);
      }
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      // Undeclared exception!
      try { 
        CoordinateManager.addDistanceNorth(1.0, 2.0, 0);
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // All parameters are required and must be valid
         //
         verifyException("main.java.CoordinateManager", e);
      }
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      boolean boolean0 = CoordinateManager.isValidLongitude(180.0);
      assertTrue(boolean0);
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      boolean boolean0 = CoordinateManager.isValidLongitude((-180.0));
      assertTrue(boolean0);
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      boolean boolean0 = CoordinateManager.isValidLatitude(90.0);
      assertTrue(boolean0);
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      // Undeclared exception!
      try { 
        CoordinateManager.getBoundingBox((-90.0), (-90.0), (-1));
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // All parameters are required and must be valid
         //
         verifyException("main.java.CoordinateManager", e);
      }
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      Coordinate coordinate0 = CoordinateManager.addDistanceWest(3.141592653589793, 0.0, 2784);
      assertEquals(3.141592653589793, coordinate0.getLatitude(), 0.01);
      assertEquals((-0.025009097509887473), coordinate0.getLongitude(), 0.01);
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      Coordinate coordinate0 = CoordinateManager.addDistanceWest((-1.0), 1.0, 617);
      assertEquals((-1.0), coordinate0.getLatitude(), 0.01);
      assertEquals(0.9897416589882737, coordinate0.getLongitude(), 0.01);
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      Coordinate coordinate0 = CoordinateManager.addDistanceSouth((-64.68), 2.0, 2132086301);
      assertEquals((-19217.537112501548), coordinate0.getLatitude(), 0.01);
      assertEquals(2.0, coordinate0.getLongitude(), 0.01);
  }

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      Coordinate coordinate0 = CoordinateManager.addDistanceSouth(2.0, 0.0, 1);
      assertEquals(1.9999910168471589, coordinate0.getLatitude(), 0.01);
      assertEquals(0.0, coordinate0.getLongitude(), 0.01);
  }

  @Test(timeout = 4000)
  public void test13()  throws Throwable  {
      Coordinate coordinate0 = CoordinateManager.addDistanceNorth((-1.0), 1.0, 617);
      assertEquals(1.0, coordinate0.getLongitude(), 0.01);
      assertEquals((-0.9944573946969826), coordinate0.getLatitude(), 0.01);
  }

  @Test(timeout = 4000)
  public void test14()  throws Throwable  {
      Coordinate coordinate0 = CoordinateManager.addDistanceNorth(0.0, 0.0, 1232);
      assertEquals(0.0, coordinate0.getLongitude(), 0.01);
      assertEquals(0.011067244300352505, coordinate0.getLatitude(), 0.01);
  }

  @Test(timeout = 4000)
  public void test15()  throws Throwable  {
      Coordinate coordinate0 = CoordinateManager.addDistanceEast(0.0, (-1.0), 3329);
      assertEquals(0.0, coordinate0.getLatitude(), 0.01);
      assertEquals((-0.9700950841916611), coordinate0.getLongitude(), 0.01);
  }

  @Test(timeout = 4000)
  public void test16()  throws Throwable  {
      Coordinate coordinate0 = CoordinateManager.addDistanceEast(48.0897885, 1.0, 64);
      assertEquals(48.0897885, coordinate0.getLatitude(), 0.01);
      assertEquals(1.001010979691407, coordinate0.getLongitude(), 0.01);
  }

  @Test(timeout = 4000)
  public void test17()  throws Throwable  {
      Coordinate coordinate0 = CoordinateManager.addDistanceEast((-1.0), 111.31949079327356, 339);
      assertEquals((-1.0), coordinate0.getLatitude(), 0.01);
      assertEquals(111.32512706167384, coordinate0.getLongitude(), 0.01);
  }

  @Test(timeout = 4000)
  public void test18()  throws Throwable  {
      Coordinate coordinate0 = CoordinateManager.addDistanceWest(0.0, 3.141592653589793, 5);
      assertEquals(0.0, coordinate0.getLatitude(), 0.01);
      assertEquals(3.141547737825587, coordinate0.getLongitude(), 0.01);
  }

  @Test(timeout = 4000)
  public void test19()  throws Throwable  {
      Coordinate coordinate0 = CoordinateManager.addDistanceSouth(0.0, (-1.0), 862);
      assertEquals((-1.0), coordinate0.getLongitude(), 0.01);
      assertEquals((-0.007743477749110276), coordinate0.getLatitude(), 0.01);
  }

  @Test(timeout = 4000)
  public void test20()  throws Throwable  {
      Coordinate coordinate0 = CoordinateManager.addDistanceNorth((-1.0), (-1.0), 1);
      assertEquals((-0.9999910168471589), coordinate0.getLatitude(), 0.01);
      assertEquals((-1.0), coordinate0.getLongitude(), 0.01);
  }

  @Test(timeout = 4000)
  public void test21()  throws Throwable  {
      boolean boolean0 = CoordinateManager.isValidLongitude((-2001.93867969));
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test22()  throws Throwable  {
      boolean boolean0 = CoordinateManager.isValidLatitude(604.0);
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test23()  throws Throwable  {
      boolean boolean0 = CoordinateManager.isValidLatitude((-613.2102751351256));
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test24()  throws Throwable  {
      double double0 = CoordinateManager.longitudeConstant(0.0);
      assertEquals(111.31949079327357, double0, 0.01);
  }

  @Test(timeout = 4000)
  public void test25()  throws Throwable  {
      // Undeclared exception!
      try { 
        CoordinateManager.getBoundingBox(0.0, 1.0, 0);
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // All parameters are required and must be valid
         //
         verifyException("main.java.CoordinateManager", e);
      }
  }

  @Test(timeout = 4000)
  public void test26()  throws Throwable  {
      // Undeclared exception!
      try { 
        CoordinateManager.getBoundingBox((-12), 1971.9636, (-12));
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // All parameters are required and must be valid
         //
         verifyException("main.java.CoordinateManager", e);
      }
  }

  @Test(timeout = 4000)
  public void test27()  throws Throwable  {
      // Undeclared exception!
      try { 
        CoordinateManager.addDistanceWest(2.0, 111.31949079327356, (-405));
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // All parameters are required and must be valid
         //
         verifyException("main.java.CoordinateManager", e);
      }
  }

  @Test(timeout = 4000)
  public void test28()  throws Throwable  {
      // Undeclared exception!
      try { 
        CoordinateManager.addDistanceWest(1.0, 2339.40418, 3030);
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // All parameters are required and must be valid
         //
         verifyException("main.java.CoordinateManager", e);
      }
  }

  @Test(timeout = 4000)
  public void test29()  throws Throwable  {
      // Undeclared exception!
      try { 
        CoordinateManager.addDistanceWest((-2788.0944292892873), 0.0, 244);
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // All parameters are required and must be valid
         //
         verifyException("main.java.CoordinateManager", e);
      }
  }

  @Test(timeout = 4000)
  public void test30()  throws Throwable  {
      // Undeclared exception!
      try { 
        CoordinateManager.addDistanceEast((-64), (-64), (-64));
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // All parameters are required and must be valid
         //
         verifyException("main.java.CoordinateManager", e);
      }
  }

  @Test(timeout = 4000)
  public void test31()  throws Throwable  {
      // Undeclared exception!
      try { 
        CoordinateManager.addDistanceSouth((-1.0), 111.31949079327356, (-1));
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // All parameters are required and must be valid
         //
         verifyException("main.java.CoordinateManager", e);
      }
  }

  @Test(timeout = 4000)
  public void test32()  throws Throwable  {
      // Undeclared exception!
      try { 
        CoordinateManager.addDistanceSouth((-2.1229333462112234), (-1388.9364677959718), 1);
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // All parameters are required and must be valid
         //
         verifyException("main.java.CoordinateManager", e);
      }
  }

  @Test(timeout = 4000)
  public void test33()  throws Throwable  {
      // Undeclared exception!
      try { 
        CoordinateManager.addDistanceSouth(96.5, (-90.0), (-1498));
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // All parameters are required and must be valid
         //
         verifyException("main.java.CoordinateManager", e);
      }
  }

  @Test(timeout = 4000)
  public void test34()  throws Throwable  {
      // Undeclared exception!
      try { 
        CoordinateManager.addDistanceNorth((-1.0), (-4.365326135898071), (-349));
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // All parameters are required and must be valid
         //
         verifyException("main.java.CoordinateManager", e);
      }
  }

  @Test(timeout = 4000)
  public void test35()  throws Throwable  {
      // Undeclared exception!
      try { 
        CoordinateManager.addDistanceNorth((-21.0), 2218.451616320067, 3483);
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // All parameters are required and must be valid
         //
         verifyException("main.java.CoordinateManager", e);
      }
  }

  @Test(timeout = 4000)
  public void test36()  throws Throwable  {
      // Undeclared exception!
      try { 
        CoordinateManager.addDistanceNorth(111.31949079327356, (-2360), (-2360));
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // All parameters are required and must be valid
         //
         verifyException("main.java.CoordinateManager", e);
      }
  }

  @Test(timeout = 4000)
  public void test37()  throws Throwable  {
      boolean boolean0 = CoordinateManager.isValidLongitude(1000.0);
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test38()  throws Throwable  {
      // Undeclared exception!
      try { 
        CoordinateManager.addDistanceEast(22.88419807027181, (-2186.65993092841), 1617);
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // All parameters are required and must be valid
         //
         verifyException("main.java.CoordinateManager", e);
      }
  }

  @Test(timeout = 4000)
  public void test39()  throws Throwable  {
      // Undeclared exception!
      try { 
        CoordinateManager.getBoundingBox(1000.0, (-1695.2707964176666), 505);
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // All parameters are required and must be valid
         //
         verifyException("main.java.CoordinateManager", e);
      }
  }

  @Test(timeout = 4000)
  public void test40()  throws Throwable  {
      // Undeclared exception!
      try { 
        CoordinateManager.addDistanceEast((-2351.31), (-2351.31), 0);
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // All parameters are required and must be valid
         //
         verifyException("main.java.CoordinateManager", e);
      }
  }

  @Test(timeout = 4000)
  public void test41()  throws Throwable  {
      double double0 = CoordinateManager.latitudeConstant();
      assertEquals(111.31949079327356, double0, 0.01);
  }
}