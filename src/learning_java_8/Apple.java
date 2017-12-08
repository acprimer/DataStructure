package learning_java_8;

/**
 * Created by yaodh on 2017/12/8.
 */
public class Apple {
    int weight;

    public Apple() {
    }

    public Apple(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Apple " + weight;
    }
}
