package patterns.proxy;

public class JavaDeveloper implements Developer, Researcher {
    private String name;

    public JavaDeveloper(String name) {
        this.name = name;
    }

    @Override
    public void code() {
        System.out.println(name + " is coding java");
    }

    @Override
    public void debug() {
        System.out.println(name + " is debugging java");
    }

    @Override
    public void research() {
        System.out.println("research");
    }

    @Override
    public void paper() {
        System.out.println("paper");
    }
}
