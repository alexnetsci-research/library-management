package alexnetsci.research.librarymanagement.services;

import alexnetsci.research.librarymanagement.entities.Book;
import alexnetsci.research.librarymanagement.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book createBook(Book bookBody) {
        return bookRepository.save(bookBody);
    }

    public Book getBook(Long id) {
        return bookRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "BOOK_NOT_FOUND"
                )
        );
    }

    public Book updateBook(Long id, Book bookBody) {
        Book currentBook = bookRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "BOOK_NOT_FOUND"
                )
        );
        currentBook.setTitle(bookBody.getTitle());

        return bookRepository.save(currentBook);
    }

    public void deleteBook(Long id) {
        bookRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "BOOK_NOT_FOUND"
                )
        );

        bookRepository.deleteById(id);
    }
}
