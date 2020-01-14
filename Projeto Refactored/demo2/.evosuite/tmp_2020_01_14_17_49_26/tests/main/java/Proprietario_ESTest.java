/*
 * This file was automatically generated by EvoSuite
 * Tue Jan 14 18:08:33 GMT 2020
 */

package main.java;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.util.GregorianCalendar;
import main.java.Proprietario;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class Proprietario_ESTest extends Proprietario_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      GregorianCalendar gregorianCalendar0 = mock(GregorianCalendar.class, new ViolatedAssumptionAnswer());
      Proprietario proprietario0 = new Proprietario((String) null, "CemQiZ*/T^_bBGW/u8", (String) null, "", "", gregorianCalendar0);
      proprietario0.setDataNasc((GregorianCalendar) null);
      // Undeclared exception!
      try { 
        proprietario0.toString();
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("main.java.Utilizador", e);
      }
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      Proprietario proprietario0 = null;
      try {
        proprietario0 = new Proprietario((Proprietario) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("main.java.Utilizador", e);
      }
  }

  @Test(timeout = 4000)
  public void test2()  throws Throwable  {
      GregorianCalendar gregorianCalendar0 = mock(GregorianCalendar.class, new ViolatedAssumptionAnswer());
      Proprietario proprietario0 = new Proprietario("m4h;U", "bJed?+z QH04JD", "m4h;U", "bJed?+z QH04JD", ";", gregorianCalendar0);
      Proprietario proprietario1 = new Proprietario(proprietario0);
      boolean boolean0 = proprietario0.equals(proprietario1);
      assertTrue(boolean0);
  }

  @Test(timeout = 4000)
  public void test3()  throws Throwable  {
      GregorianCalendar gregorianCalendar0 = mock(GregorianCalendar.class, new ViolatedAssumptionAnswer());
      Proprietario proprietario0 = new Proprietario("", "", (String) null, (String) null, "", gregorianCalendar0);
      boolean boolean0 = proprietario0.equals((Object) null);
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test4()  throws Throwable  {
      GregorianCalendar gregorianCalendar0 = mock(GregorianCalendar.class, new ViolatedAssumptionAnswer());
      Proprietario proprietario0 = new Proprietario("m4h;U", "bJed?+z QH04JD", "m4h;U", "bJed?+z QH04JD", ";", gregorianCalendar0);
      boolean boolean0 = proprietario0.equals(proprietario0);
      assertTrue(boolean0);
  }

  @Test(timeout = 4000)
  public void test5()  throws Throwable  {
      GregorianCalendar gregorianCalendar0 = mock(GregorianCalendar.class, new ViolatedAssumptionAnswer());
      Proprietario proprietario0 = new Proprietario("m4h;U", "bJed?+z QH04JD", "m4h;U", "bJed?+z QH04JD", ";", gregorianCalendar0);
      boolean boolean0 = proprietario0.equals("m4h;U");
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test6()  throws Throwable  {
      GregorianCalendar gregorianCalendar0 = mock(GregorianCalendar.class, new ViolatedAssumptionAnswer());
      Proprietario proprietario0 = new Proprietario("m4h;U", "bJed?+z QH04JD", "m4h;U", "bJed?+z QH04JD", ";", gregorianCalendar0);
      proprietario0.hashCode();
  }

  @Test(timeout = 4000)
  public void test7()  throws Throwable  {
      GregorianCalendar gregorianCalendar0 = mock(GregorianCalendar.class, new ViolatedAssumptionAnswer());
      doReturn(1, 1, 1).when(gregorianCalendar0).get(anyInt());
      Proprietario proprietario0 = new Proprietario("m4h;U", "bJed?+z QH04JD", "m4h;U", "bJed?+z QH04JD", ";", gregorianCalendar0);
      Proprietario proprietario1 = proprietario0.clone();
      assertEquals("bJed?+z QH04JD", proprietario1.getPassword());
  }

  @Test(timeout = 4000)
  public void test8()  throws Throwable  {
      Proprietario proprietario0 = new Proprietario();
      Proprietario proprietario1 = proprietario0.clone();
      proprietario1.setDataNasc((GregorianCalendar) null);
      // Undeclared exception!
      try { 
        proprietario1.equals(proprietario0);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("main.java.Utilizador", e);
      }
  }

  @Test(timeout = 4000)
  public void test9()  throws Throwable  {
      GregorianCalendar gregorianCalendar0 = mock(GregorianCalendar.class, new ViolatedAssumptionAnswer());
      doReturn(1, 1, 1).when(gregorianCalendar0).get(anyInt());
      Proprietario proprietario0 = new Proprietario("m4h;U", "bJed?+z QH04JD", "m4h;U", "bJed?+z QH04JD", ";", gregorianCalendar0);
      String string0 = proprietario0.toString();
      assertEquals("*****    UTILIZADOR    *****\nNome: m4h;U\nNIF: bJed?+z QH04JD\nEmail: m4h;U\nPassword: bJed?+z QH04JD\nMorada: ;\nData de Nascimento: 1/2/1\n", string0);
  }
}
