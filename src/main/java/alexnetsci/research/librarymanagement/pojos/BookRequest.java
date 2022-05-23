package alexnetsci.research.librarymanagement.pojos;

import alexnetsci.research.librarymanagement.entities.Author;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

public class BookRequest {

    public long id;

    @NotNull
    @NotEmpty
    @Size(max = 50)
    public String title;
    
    @NotNull
    public long publisherId;

    @NotNull
    public Set<Author> authors;
}
