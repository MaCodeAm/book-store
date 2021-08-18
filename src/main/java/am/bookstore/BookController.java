package am.bookstore;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    private final JdbcTemplate jdbcTemplate;

    public BookController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/books")
    public List<Book> getBooks() {
        return jdbcTemplate.query(
                "select id, title, image_url, price, category from book",
                (resultSet, i) -> {
                    Book book = new Book();
                    book.setId(resultSet.getInt("id"));
                    book.setImageUrl(resultSet.getString("image_url"));
                    book.setTitle(resultSet.getString("title"));
                    book.setPrice(resultSet.getInt("price"));
                    book.setCategory(resultSet.getString("category"));

                    return book;
                }
        );
    }

}
