package lecture27;

/**
 * javac  -processor lecture27.CheckGetterProcessor lecture27/Foo.java
 */
@CheckGetter
class Foo {      // TypeElement
  int a;           // VariableElement
  static int b;    // VariableElement
  Foo () {}        // ExecutableElement
  void setA (      // ExecutableElement
    int newA         // VariableElement
  ) {}
}