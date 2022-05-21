package alexnetsci.research.librarymanagement.controllers;

import alexnetsci.research.librarymanagement.entities.Author;
import alexnetsci.research.librarymanagement.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "authors")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> index() {
        return authorService.getAuthors();
    }

    @PostMapping
    public ResponseEntity<Author> create(@Valid @RequestBody Author authorBody) {
        Author author = authorService.createAuthor(authorBody);

        return new ResponseEntity<>(author, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{authorId}")
    public Author details(@PathVariable("authorId") Long authorId) {
        return authorService.getAuthor(authorId);
    }

    @PutMapping(path = "/{authorId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Author update(@PathVariable("authorId") Long authorId, @Valid @RequestBody Author authorBody) {
        return authorService.updateAuthor(authorId, authorBody);
    }

    @DeleteMapping(path = "/{authorId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("authorId") Long authorId) {
        authorService.deleteAuthor(authorId);
    }
}
