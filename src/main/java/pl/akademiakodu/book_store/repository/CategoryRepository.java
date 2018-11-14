package pl.akademiakodu.book_store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.akademiakodu.book_store.model.Category;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByTitle(String title);

}
