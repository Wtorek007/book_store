package pl.akademiakodu.book_store.commons;

public interface Mapper<F,T> {
    T map (F from);
}
