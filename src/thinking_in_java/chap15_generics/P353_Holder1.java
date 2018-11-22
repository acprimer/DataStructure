package thinking_in_java.chap15_generics;

/**
 * Created by yaodh on 2018/9/12.
 */
class Automobile {}
public class P353_Holder1 {
    private Automobile a;
    public P353_Holder1(Automobile a) {
        this.a = a;
    }
    Automobile get() {
        return a;
    }
}
