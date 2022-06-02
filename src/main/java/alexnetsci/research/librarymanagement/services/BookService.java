package alexnetsci.research.librarymanagement.services;

import alexnetsci.research.librarymanagement.entities.Author;
import alexnetsci.research.librarymanagement.entities.Book;
import alexnetsci.research.librarymanagement.entities.Genre;
import alexnetsci.research.librarymanagement.entities.Publisher;
import alexnetsci.research.librarymanagement.pojos.BookRequest;
import alexnetsci.research.librarymanagement.repositories.AuthorRepository;
import alexnetsci.research.librarymanagement.repositories.BookRepository;
import alexnetsci.research.librarymanagement.repositories.GenreRepository;
import alexnetsci.research.librarymanagement.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired private BookRepository bookRepository;
    @Autowired private PublisherRepository publisherRepository;
    @Autowired private AuthorRepository authorRepository;
    @Autowired private GenreRepository genreRepository;

    public Collection<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book createBook(BookRequest bookRequest) {
        Book book = new Book();

        Publisher publisher = publisherRepository.findById(bookRequest.publisherId).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "PUBLISHER_NOT_FOUND"
                )
        );

        Genre genre = genreRepository.findById(bookRequest.genreId).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "GENRE_NOT_FOUND"
                )
        );

        book.setTitle(bookRequest.title);
        book.setPublisher(publisher);
        book.setGenre(genre);

        book.setAuthors(bookRequest.authorIds.stream().map(authorId -> {
            Author authorList = new Author();
            if (authorId > 0) {
                authorList = authorRepository.findById(authorId).orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "AUTHOR_NOT_FOUND"
                        )
                );
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
        Book currentBook = bookRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "BOOK_NOT_FOUND"
                )
        );

        Publisher publisher = publisherRepository.findById(bookRequest.publisherId).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "PUBLISHER_NOT_FOUND"
                )
        );

        Genre genre = genreRepository.findById(bookRequest.genreId).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "GENRE_NOT_FOUND"
                )
        );

        currentBook.setTitle(bookRequest.title);
        currentBook.setPublisher(publisher);
        currentBook.setGenre(genre);

        currentBook.setAuthors(bookRequest.authorIds.stream().map(authorId -> {
            Author authorList = new Author();
            if (authorId > 0) {
                authorList = authorRepository.findById(authorId).orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "AUTHOR_NOT_FOUND"
                        )
                );
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
