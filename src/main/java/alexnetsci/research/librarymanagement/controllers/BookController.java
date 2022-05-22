package alexnetsci.research.librarymanagement.controllers;

import alexnetsci.research.librarymanagement.entities.Book;
import alexnetsci.research.librarymanagement.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> index() {
        return bookService.getBooks();
    }

    @PostMapping
    public ResponseEntity<Book> create(@Valid @RequestBody Book bookBody) {
        Book book = bookService.createBook(bookBody);

        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public Book details(@PathVariable("id") Long id) {
        return bookService.getBook(id);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Book update(@PathVariable("id") Long id, @Valid @RequestBody Book bookBody) {
        return bookService.updateBook(id, bookBody);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
    }
}
