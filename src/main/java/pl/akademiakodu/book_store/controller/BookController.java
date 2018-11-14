package pl.akademiakodu.book_store.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pl.akademiakodu.book_store.model.Book;
import pl.akademiakodu.book_store.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/")
public class BookController {


    private BookRepository bookRepository;

    // @Autowired /*nie jest wymagane*/
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("books")
    public ResponseEntity<List<Book>> getBooks() {
        return new ResponseEntity<>(bookRepository.findAll(), HttpStatus.OK);
    }


    @GetMapping("books{isbn}")
    public ResponseEntity<Book> getBook(@PathVariable(value = "isbn") String isbn) {

        Optional<Book> bookOptional = bookRepository.findByIsbn(isbn);

        if(bookOptional.isPresent()) {
            return new ResponseEntity<>(bookOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Book(), HttpStatus.NOT_FOUND);
    }


//    @PostMapping("v1/books")
//    public ResponseEntity<Book> addBookV1(
//            @RequestParam(value = "title") String title,
//            @RequestParam(value = "author", required = true) String author,
//            @RequestParam(value = "isbn") String isbn
//    ) {
//        Book book = new Book();
//        book.setTitle(title);
//        book.setAuthor(author);
//        book.setIsbn(isbn);
//
//        return new ResponseEntity<>(bookRepository.save(book), HttpStatus.OK); //status 200
//    }

    @PostMapping("v2/books")
    public ResponseEntity<Book> addBookV2(@RequestBody Book book) {
        return new ResponseEntity<>(bookRepository.save(book), HttpStatus.OK); //status 200
    }

    // @RequestMapping(value = "books", method = RequestMethod.POST)
    @PostMapping("v3/books")
    public ResponseEntity<Book> addBookV3(@RequestBody Book book) {

        Optional<Book> bookOptional = bookRepository.findByIsbn(book.getIsbn());

        if (!bookOptional.isPresent()) {
            return new ResponseEntity<>(bookRepository.save(book), HttpStatus.OK); //status 200
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT); //status 409
    }

    @DeleteMapping("books/{isbn}")
    public ResponseEntity<Book> deleteBook(@PathVariable String isbn) {
        Optional<Book> bookOptional = bookRepository.findByIsbn(isbn);

        if (bookOptional.isPresent()) {
            bookRepository.delete(bookOptional.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("books")
    public ResponseEntity<Book> updateBook(@RequestParam String isbn, @RequestBody Book book) {
        Optional<Book> bookOptional = bookRepository.findByIsbn(isbn);

        if (bookOptional.isPresent()) {

            bookOptional.get().setTitle(book.getTitle());
            bookOptional.get().setAuthor(book.getAuthor());
            bookOptional.get().setIsbn(book.getIsbn());
          //  bookOptional.get().setId(book.getId()); /*nie poprawnie*/

            return new ResponseEntity<>(bookRepository.save(bookOptional.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
