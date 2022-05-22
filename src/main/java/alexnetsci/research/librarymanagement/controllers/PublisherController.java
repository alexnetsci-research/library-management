package alexnetsci.research.librarymanagement.controllers;

import alexnetsci.research.librarymanagement.entities.Publisher;
import alexnetsci.research.librarymanagement.services.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "publishers")
public class PublisherController {

    private final PublisherService publisherService;

    @Autowired
    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping
    public List<Publisher> index() {
        return publisherService.getPublishers();
    }

    @PostMapping
    public ResponseEntity<Publisher> create(@Valid @RequestBody Publisher publisherBody) {
        Publisher publisher = publisherService.createPublisher(publisherBody);

        return new ResponseEntity<>(publisher, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public Publisher details(@PathVariable("id") Long id) {
        return publisherService.getPublisher(id);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Publisher update(@PathVariable("id") Long id, @Valid @RequestBody Publisher publisherBody) {
        return publisherService.updatePublisher(id, publisherBody);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        publisherService.deletePublisher(id);
    }
}
