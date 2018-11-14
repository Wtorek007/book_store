package pl.akademiakodu.book_store.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private String title;
    private String isbn;
    private String author;
    private String category;
}
