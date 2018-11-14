package pl.akademiakodu.book_store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.akademiakodu.book_store.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {


    String BY_AUTHOR = "SELECT * FROM books WHERE author like (?1%)";
    //  String BY_CATEGORY = "SELECT * FROM categories WHERE title like ?1%";


    Optional<Book> findByIsbn(String isbn);

    List<Book> findBooksByCategoryId(Long fk_category);

    // @Async
    @Query(value = BY_AUTHOR, nativeQuery = true)
    Optional<Book> findByAuthor(String author);

    //  @Async
    //  @Query(value = BY_CATEGORY, nativeQuery = true)
    //  Optional<Book> findByCategory(String category);
}
