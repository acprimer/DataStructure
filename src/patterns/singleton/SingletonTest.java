package patterns.singleton;

/**
 * Created by yaodh on 2017/4/7.
 */
public class SingletonTest {
    public static void main(String[] args) {
        System.out.println("hello");
        SingletonHungry_2 singletonHungry_2 = SingletonHungry_2.getInstance();
        System.out.println(singletonHungry_2.toString());

        SingletonHungry_3 singletonHungry_3 = SingletonHungry_3.getInstance();
        System.out.println(singletonHungry_3);
    }
}
