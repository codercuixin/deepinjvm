package lecture33;

/**
 * javac lecture33/MyAgent.java
 * jar cvmf manifest.txt.version1 myagent.jar lecture33
 * java -javaagent:myagent.jar HelloWorld
 */
public class MyAgent {
  public static void premain(String args) {
    System.out.println("premain");
  }
}