package alexnetsci.research.librarymanagement.services;

import alexnetsci.research.librarymanagement.entities.Author;
import alexnetsci.research.librarymanagement.entities.Book;
import alexnetsci.research.librarymanagement.entities.Publisher;
import alexnetsci.research.librarymanagement.pojos.BookRequest;
import alexnetsci.research.librarymanagement.repositories.AuthorRepository;
import alexnetsci.research.librarymanagement.repositories.BookRepository;
import alexnetsci.research.librarymanagement.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired private BookRepository bookRepository;
    @Autowired private PublisherRepository publisherRepository;
    @Autowired private AuthorRepository authorRepository;

    public Collection<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book createBook(BookRequest bookRequest) {
        Publisher publisher = publisherRepository.findById(bookRequest.publisherId).orElseThrow(EntityNotFoundException::new);

        Book book = new Book();
        book.setTitle(bookRequest.title);
        book.setPublisher(publisher);
        book.setAuthors(bookRequest.authors.stream().map(author -> {
            Author authorList = author;
            if (authorList.getId() > 0) {
                authorList = authorRepository.findById(authorList.getId()).orElseThrow(EntityNotFoundException::new);
            }
            book.getAuthors().add(authorList);
            return authorList;
        }).collect(Collectors.toSet()));

        return bookRepository.save(book);
    }

    public Book getBook(Long id) {
        return bookRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "BOOK_NOT_FOUND"
                )
        );
    }

    public Book updateBook(Long id, BookRequest bookRequest) {
        Publisher publisher = publisherRepository.findById(bookRequest.publisherId).orElseThrow(EntityNotFoundException::new);

        Book currentBook = bookRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "BOOK_NOT_FOUND"
                )
        );
        currentBook.setTitle(bookRequest.title);
        currentBook.setPublisher(publisher);
        currentBook.setAuthors(bookRequest.authors.stream().map(author -> {
            Author authorList = author;
            if (authorList.getId() > 0) {
                authorList = authorRepository.findById(authorList.getId()).orElseThrow(EntityNotFoundException::new);
            }
            currentBook.getAuthors().add(authorList);
            return authorList;
        }).collect(Collectors.toSet()));

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
