package alexnetsci.research.librarymanagement.repositories;

import alexnetsci.research.librarymanagement.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
