package alexnetsci.research.librarymanagement.controllers;

import alexnetsci.research.librarymanagement.entities.Genre;
import alexnetsci.research.librarymanagement.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping(path = "genres")
public class GenreController {

    @Autowired private GenreService genreService;

    @GetMapping
    public Collection<Genre> index() {
        return genreService.getGenres();
    }

    @PostMapping
    public ResponseEntity<Genre> create(@Valid @RequestBody Genre genreBody) {
        Genre genre = genreService.createGenre(genreBody);

        return new ResponseEntity<>(genre, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public Genre details(@PathVariable("id") Long id) {
        return genreService.getGenre(id);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Genre update(@PathVariable("id") Long id, @Valid @RequestBody Genre genreBody) {
        return genreService.updateGenre(id, genreBody);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        genreService.deleteGenre(id);
    }
}
