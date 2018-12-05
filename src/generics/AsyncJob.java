package generics;

public abstract class AsyncJob<T> {
    public abstract <R> AsyncJob<R> map();
}
