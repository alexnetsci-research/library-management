package alexnetsci.research.librarymanagement.services;

import alexnetsci.research.librarymanagement.entities.Genre;
import alexnetsci.research.librarymanagement.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@Service
public class GenreService {

    @Autowired private GenreRepository genreRepository;

    public Collection<Genre> getGenres() {
        return genreRepository.findAll();
    }

    public Genre createGenre(Genre genreBody) {
        return genreRepository.save(genreBody);
    }

    public Genre getGenre(Long id) {
        return genreRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "GENRE_NOT_FOUND"
                )
        );
    }

    public Genre updateGenre(Long id, Genre genreBody) {
        Genre currentGenre = genreRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "GENRE_NOT_FOUND"
                )
        );
        currentGenre.setName(genreBody.getName());

        return genreRepository.save(currentGenre);
    }

    public void deleteGenre(Long id) {
        genreRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "GENRE_NOT_FOUND"
                )
        );

        genreRepository.deleteById(id);
    }
}
