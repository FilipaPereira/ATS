/**
 * Scaffolding file used to store all the setups needed to run 
 * tests automatically generated by EvoSuite
 * Tue Jan 14 18:08:58 GMT 2020
 */

package main.java;

import org.evosuite.runtime.annotation.EvoSuiteClassExclude;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.After;
import org.junit.AfterClass;
import org.evosuite.runtime.sandbox.Sandbox;
import org.evosuite.runtime.sandbox.Sandbox.SandboxMode;

import static org.evosuite.shaded.org.mockito.Mockito.*;
@EvoSuiteClassExclude
public class CarroEletrico_ESTest_scaffolding {

  @org.junit.Rule 
  public org.evosuite.runtime.vnet.NonFunctionalRequirementRule nfr = new org.evosuite.runtime.vnet.NonFunctionalRequirementRule();

  private static final java.util.Properties defaultProperties = (java.util.Properties) java.lang.System.getProperties().clone(); 

  private org.evosuite.runtime.thread.ThreadStopper threadStopper =  new org.evosuite.runtime.thread.ThreadStopper (org.evosuite.runtime.thread.KillSwitchHandler.getInstance(), 3000);


  @BeforeClass 
  public static void initEvoSuiteFramework() { 
    org.evosuite.runtime.RuntimeSettings.className = "main.java.CarroEletrico"; 
    org.evosuite.runtime.GuiSupport.initialize(); 
    org.evosuite.runtime.RuntimeSettings.maxNumberOfThreads = 100; 
    org.evosuite.runtime.RuntimeSettings.maxNumberOfIterationsPerLoop = 10000; 
    org.evosuite.runtime.RuntimeSettings.mockSystemIn = true; 
    org.evosuite.runtime.RuntimeSettings.sandboxMode = org.evosuite.runtime.sandbox.Sandbox.SandboxMode.RECOMMENDED; 
    org.evosuite.runtime.sandbox.Sandbox.initializeSecurityManagerForSUT(); 
    org.evosuite.runtime.classhandling.JDKClassResetter.init();
    setSystemProperties();
    initializeClasses();
    org.evosuite.runtime.Runtime.getInstance().resetRuntime(); 
    try { initMocksToAvoidTimeoutsInTheTests(); } catch(ClassNotFoundException e) {} 
  } 

  @AfterClass 
  public static void clearEvoSuiteFramework(){ 
    Sandbox.resetDefaultSecurityManager(); 
    java.lang.System.setProperties((java.util.Properties) defaultProperties.clone()); 
  } 

  @Before 
  public void initTestCase(){ 
    threadStopper.storeCurrentThreads();
    threadStopper.startRecordingTime();
    org.evosuite.runtime.jvm.ShutdownHookHandler.getInstance().initHandler(); 
    org.evosuite.runtime.sandbox.Sandbox.goingToExecuteSUTCode(); 
    setSystemProperties(); 
    org.evosuite.runtime.GuiSupport.setHeadless(); 
    org.evosuite.runtime.Runtime.getInstance().resetRuntime(); 
    org.evosuite.runtime.agent.InstrumentingAgent.activate(); 
  } 

  @After 
  public void doneWithTestCase(){ 
    threadStopper.killAndJoinClientThreads();
    org.evosuite.runtime.jvm.ShutdownHookHandler.getInstance().safeExecuteAddedHooks(); 
    org.evosuite.runtime.classhandling.JDKClassResetter.reset(); 
    resetClasses(); 
    org.evosuite.runtime.sandbox.Sandbox.doneWithExecutingSUTCode(); 
    org.evosuite.runtime.agent.InstrumentingAgent.deactivate(); 
    org.evosuite.runtime.GuiSupport.restoreHeadlessMode(); 
  } 

  public static void setSystemProperties() {
 
    java.lang.System.setProperties((java.util.Properties) defaultProperties.clone()); 
    java.lang.System.setProperty("file.encoding", "UTF-8"); 
    java.lang.System.setProperty("java.awt.headless", "true"); 
    java.lang.System.setProperty("java.io.tmpdir", "/tmp"); 
    java.lang.System.setProperty("user.country", "US"); 
    java.lang.System.setProperty("user.dir", "/home/mercy/ATS/Projeto Refactored/demo2"); 
    java.lang.System.setProperty("user.home", "/home/mercy"); 
    java.lang.System.setProperty("user.language", "en"); 
    java.lang.System.setProperty("user.name", "mercy"); 
    java.lang.System.setProperty("user.timezone", "Europe/Lisbon"); 
  }

  private static void initializeClasses() {
    org.evosuite.runtime.classhandling.ClassStateSupport.initializeClasses(CarroEletrico_ESTest_scaffolding.class.getClassLoader() ,
      "main.java.CarroEletrico",
      "main.java.Coordinate",
      "main.java.Veiculo",
      "main.java.ParDatas",
      "main.java.Classificacao"
    );
  } 
  private static void initMocksToAvoidTimeoutsInTheTests() throws ClassNotFoundException { 
    mock(Class.forName("main.java.Coordinate", false, CarroEletrico_ESTest_scaffolding.class.getClassLoader()));
  }

  private static void resetClasses() {
    org.evosuite.runtime.classhandling.ClassResetter.getInstance().setClassLoader(CarroEletrico_ESTest_scaffolding.class.getClassLoader()); 

    org.evosuite.runtime.classhandling.ClassStateSupport.resetClasses(
      "main.java.Veiculo",
      "main.java.CarroEletrico",
      "main.java.Coordinate",
      "main.java.ParDatas",
      "main.java.Weather",
      "com.google.gson.Gson",
      "com.google.gson.internal.Excluder",
      "com.google.gson.FieldNamingPolicy",
      "com.google.gson.LongSerializationPolicy",
      "com.google.gson.Gson$1",
      "com.google.gson.Gson$2",
      "com.google.gson.internal.ConstructorConstructor",
      "com.google.gson.TypeAdapter",
      "com.google.gson.internal.bind.TypeAdapters$1",
      "com.google.gson.internal.bind.TypeAdapters$32",
      "com.google.gson.internal.bind.TypeAdapters$2",
      "com.google.gson.internal.bind.TypeAdapters$3",
      "com.google.gson.internal.bind.TypeAdapters$4",
      "com.google.gson.internal.bind.TypeAdapters$33",
      "com.google.gson.internal.bind.TypeAdapters$5",
      "com.google.gson.internal.bind.TypeAdapters$6",
      "com.google.gson.internal.bind.TypeAdapters$7",
      "com.google.gson.internal.bind.TypeAdapters$8",
      "com.google.gson.TypeAdapter$1",
      "com.google.gson.internal.bind.TypeAdapters$9",
      "com.google.gson.internal.bind.TypeAdapters$10",
      "com.google.gson.internal.bind.TypeAdapters$11",
      "com.google.gson.internal.bind.TypeAdapters$12",
      "com.google.gson.internal.bind.TypeAdapters$13",
      "com.google.gson.internal.bind.TypeAdapters$14",
      "com.google.gson.internal.bind.TypeAdapters$15",
      "com.google.gson.internal.bind.TypeAdapters$16",
      "com.google.gson.internal.bind.TypeAdapters$17",
      "com.google.gson.internal.bind.TypeAdapters$18",
      "com.google.gson.internal.bind.TypeAdapters$19",
      "com.google.gson.internal.bind.TypeAdapters$20",
      "com.google.gson.internal.bind.TypeAdapters$21",
      "com.google.gson.internal.bind.TypeAdapters$22",
      "com.google.gson.internal.bind.TypeAdapters$23",
      "com.google.gson.internal.bind.TypeAdapters$35",
      "com.google.gson.internal.bind.TypeAdapters$24",
      "com.google.gson.internal.bind.TypeAdapters$25",
      "com.google.gson.internal.bind.TypeAdapters$26",
      "com.google.gson.internal.bind.TypeAdapters$27",
      "com.google.gson.internal.bind.TypeAdapters$34",
      "com.google.gson.internal.bind.TypeAdapters$28",
      "com.google.gson.internal.bind.TypeAdapters$29",
      "com.google.gson.internal.bind.TypeAdapters$30",
      "com.google.gson.internal.bind.TypeAdapters",
      "com.google.gson.internal.bind.ObjectTypeAdapter$1",
      "com.google.gson.internal.bind.ObjectTypeAdapter",
      "com.google.gson.Gson$3",
      "com.google.gson.Gson$4",
      "com.google.gson.Gson$6",
      "com.google.gson.Gson$7",
      "com.google.gson.internal.bind.DateTypeAdapter$1",
      "com.google.gson.internal.bind.DateTypeAdapter",
      "com.google.gson.internal.bind.TimeTypeAdapter$1",
      "com.google.gson.internal.bind.TimeTypeAdapter",
      "com.google.gson.internal.bind.SqlDateTypeAdapter$1",
      "com.google.gson.internal.bind.SqlDateTypeAdapter",
      "com.google.gson.internal.bind.ArrayTypeAdapter$1",
      "com.google.gson.internal.bind.ArrayTypeAdapter",
      "com.google.gson.internal.bind.CollectionTypeAdapterFactory",
      "com.google.gson.internal.bind.MapTypeAdapterFactory",
      "com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory",
      "com.google.gson.internal.bind.ReflectiveTypeAdapterFactory",
      "com.google.gson.reflect.TypeToken",
      "main.java.Weather$1",
      "com.google.gson.internal.$Gson$Types",
      "com.google.gson.internal.$Gson$Types$ParameterizedTypeImpl",
      "com.google.gson.internal.$Gson$Preconditions",
      "com.google.gson.internal.JsonReaderInternalAccess",
      "com.google.gson.stream.JsonReader$1",
      "com.google.gson.stream.JsonReader",
      "com.google.gson.stream.JsonToken",
      "com.google.gson.Gson$FutureTypeAdapter",
      "com.google.gson.internal.ConstructorConstructor$3",
      "com.google.gson.internal.bind.MapTypeAdapterFactory$Adapter",
      "com.google.gson.internal.bind.TypeAdapterRuntimeTypeWrapper",
      "com.google.gson.JsonParseException",
      "com.google.gson.JsonSyntaxException"
    );
  }
}
