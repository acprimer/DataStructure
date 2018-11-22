package thinking_in_java.chap15_generics;

public class P190_Parcel {
    class Contents {
        private int i = 11;
        public int value() { return i; }
    }

    class Destination {
        private String label;
        Destination(String whereTo) {
            label = whereTo;
        }

        String readLabel() {
            return label;
        }
    }

    public Destination to(String s) {
        return new Destination(s);
    }

    public Contents contents() {
        return new Contents();
    }

    public void ship(String dest) {
        Contents c = contents();
        Destination d = to(dest);
        System.out.println(d.readLabel());
    }

    public static void main(String[] args) {
        P190_Parcel parcel = new P190_Parcel();
        parcel.ship("Tasmania");

        P190_Parcel p2 = new P190_Parcel();
        Contents contents = p2.contents();
        Destination destination = p2.to("Borneo");

        Contents s = new P190_Parcel().new Contents();
    }
}
