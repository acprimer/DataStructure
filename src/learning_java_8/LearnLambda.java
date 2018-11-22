package learning_java_8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by yaodh on 2017/12/8.
 */
public class LearnLambda {
    public static void main(String[] args) {
//        List<String> str = Arrays.asList("a", "b", "A", "B");
//        str.sort(String::compareToIgnoreCase);
//        System.out.println(Arrays.toString(str.toArray()));
//
//        Function<Integer, Apple> c1 = Apple::new;
//        Apple a1 = c1.apply(110);
//        Apple a2 = c1.apply(120);
//        Apple a3 = c1.apply(90);
//        List<Apple> inventory = new ArrayList<>();
//        inventory.add(a1);
//        inventory.add(a2);
//        inventory.add(a3);
//        inventory.sort(Comparator.comparing(Apple::getWeight).reversed());
//        System.out.println(Arrays.toString(inventory.toArray()));
//
//        List<Dish> menu = Arrays.asList(
//                new Dish("pork", false, 800, Dish.Type.MEAT),
//                new Dish("beef", false, 700, Dish.Type.MEAT),
//                new Dish("chicken", false, 400, Dish.Type.MEAT),
//
//                new Dish("french fries", true, 530, Dish.Type.OTHER),
//                new Dish("rice", true, 350, Dish.Type.OTHER),
//                new Dish("season fruit", true, 120, Dish.Type.OTHER),
//                new Dish("pizza", true, 550, Dish.Type.OTHER),
//                new Dish("prawns", false, 300, Dish.Type.FISH),
//                new Dish("salmon", false, 450, Dish.Type.FISH) );
//        List<String> threeHighCaloricDishNames =
//                menu.stream()
//                .filter(d -> d.getCalories() > 300)
//                .map(Dish::getName)
//                .limit(3)
//                .collect(Collectors.toList());
//        System.out.println(threeHighCaloricDishNames);
    }
}
