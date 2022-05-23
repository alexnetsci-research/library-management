package alexnetsci.research.librarymanagement.controllers;

import alexnetsci.research.librarymanagement.entities.Book;
import alexnetsci.research.librarymanagement.pojos.BookRequest;
import alexnetsci.research.librarymanagement.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping(path = "books")
public class BookController {

    @Autowired private BookService bookService;

    @GetMapping
    public Collection<Book> index() {
        return bookService.getBooks();
    }

    @PostMapping
    public ResponseEntity<Book> create(@Valid @RequestBody BookRequest bookRequest) {
        Book book = bookService.createBook(bookRequest);

        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public Book details(@PathVariable("id") Long id) {
        return bookService.getBook(id);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Book update(@PathVariable("id") Long id, @Valid @RequestBody BookRequest bookRequest) {
        return bookService.updateBook(id, bookRequest);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
    }
}
