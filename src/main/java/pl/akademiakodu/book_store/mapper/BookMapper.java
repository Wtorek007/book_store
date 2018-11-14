package pl.akademiakodu.book_store.mapper;


import org.springframework.stereotype.Component;
import pl.akademiakodu.book_store.commons.Mapper;
import pl.akademiakodu.book_store.dtos.BookDto;
import pl.akademiakodu.book_store.model.Book;

@Component
public class BookMapper implements Mapper<Book, BookDto> {

    @Override
    public BookDto map(Book from) {
        return new BookDto (
                from.getTitle(),
                from.getIsbn(),
                from.getAuthor(),
                from.getCategory().getTitle()
        );
    }
}


