package thinking_in_java.chap15_generics;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yaodh on 2018/9/12.
 */
public class P353_Holder2 {
    private Object a;
    public P353_Holder2(Object a) {
        this.a = a;
    }

    public Object get() {
        return a;
    }

    public void set(Object a) {
        this.a = a;
    }

    public static void main(String[] args) {
        P353_Holder2 h2 = new P353_Holder2(new Automobile());
        Automobile a = (Automobile) h2.get();
        h2.set(1);
        int x = (int) h2.get();
        System.out.println(x);

        List<String> str = new ArrayList<>();
        List<Integer> integers = new ArrayList<>();

        P190_Parcel.Contents c = new P190_Parcel().new Contents();
    }
}
