package alexnetsci.research.librarymanagement.services;

import alexnetsci.research.librarymanagement.entities.Author;
import alexnetsci.research.librarymanagement.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    public Author createAuthor(Author authorBody) {
        return authorRepository.save(authorBody);
    }

    public Author getAuthor(Long authorId) {
        return authorRepository.findById(authorId).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "AUTHOR_NOT_FOUND"
                )
        );
    }

    public Author updateAuthor(Long authorId, Author authorBody) {
        Author currentAuthor = authorRepository.findById(authorId).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "AUTHOR_NOT_FOUND"
                )
        );
        currentAuthor.setFirstName(authorBody.getFirstName());
        currentAuthor.setLastName(authorBody.getLastName());

        return authorRepository.save(currentAuthor);
    }

    public void deleteAuthor(Long authorId) {
        Author author = authorRepository.findById(authorId).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "AUTHOR_NOT_FOUND"
                )
        );

        authorRepository.deleteById(authorId);
    }
}
