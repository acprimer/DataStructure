package shengsiyuan;

public class ObjectFinalField {
    private final int a = 1;
    private final int b;
    {
        b = 2;
    }
    private final int c;
    public ObjectFinalField() {
        this(3);
    }
    public ObjectFinalField(int c) {
        this.c = c;
    }

    private final Object d = new Object();
    private final Object e;
    {
        e = new Object();
    }

    private final String f = "hello";
    private final String g;
    {
        g = "world";
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public int getC() {
        return c;
    }

    public Object getD() {
        return d;
    }

    public Object getE() {
        return e;
    }

    public String getF() {
        return f;
    }

    public String getG() {
        return g;
    }

    @Override
    public String toString() {
        return "ObjectFinalField{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", d=" + d +
                ", e=" + e +
                ", f='" + f + '\'' +
                ", g='" + g + '\'' +
                '}';
    }
}
