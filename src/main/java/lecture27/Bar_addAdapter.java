package lecture27;
import java.util.function.IntBinaryOperator;

public class Bar_addAdapter implements IntBinaryOperator {
  @Override
  public int applyAsInt(int arg0, int arg1) {
    return Bar.add(arg0, arg1);
  }
}
