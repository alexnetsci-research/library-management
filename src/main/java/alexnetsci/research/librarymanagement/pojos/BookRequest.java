package alexnetsci.research.librarymanagement.pojos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

public class BookRequest {

    @NotNull
    @NotEmpty
    @Size(max = 50)
    public String title;
    
    @NotNull
    public long publisherId;

    @NotEmpty
    public Set<Long> authorIds;

    @NotNull
    public long genreId;
}
