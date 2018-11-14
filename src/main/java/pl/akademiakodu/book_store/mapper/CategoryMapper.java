package pl.akademiakodu.book_store.mapper;

import org.springframework.stereotype.Component;
import pl.akademiakodu.book_store.commons.Mapper;
import pl.akademiakodu.book_store.dtos.CategoryDto;
import pl.akademiakodu.book_store.model.Book;
import pl.akademiakodu.book_store.model.Category;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class CategoryMapper implements Mapper<Category, CategoryDto> {


    @Override
    public CategoryDto map(Category from) {

        List<String> books = from.getBooks()
                .stream()
                .map(BooksToString.INSTANCE)
                .collect(Collectors.toList());

        return new CategoryDto(
                from.getTitle(),
                books
        );
    }

    private enum BooksToString implements Function<Book, String> {
        INSTANCE;

        @Override
        public String apply(Book book) {
            return book.getTitle();
        }
    }
}
