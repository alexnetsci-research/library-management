package alexnetsci.research.librarymanagement.repositories;

import alexnetsci.research.librarymanagement.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
}
