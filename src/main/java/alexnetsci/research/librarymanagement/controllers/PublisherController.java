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

    @GetMapping(path = "/{publisherId}")
    public Publisher details(@PathVariable("publisherId") Long publisherId) {
        return publisherService.getPublisher(publisherId);
    }

    @PutMapping(path = "/{publisherId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Publisher update(@PathVariable("publisherId") Long publisherId, @Valid @RequestBody Publisher publisherBody) {
        return publisherService.updatePublisher(publisherId, publisherBody);
    }

    @DeleteMapping(path = "/{publisherId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("publisherId") Long publisherId) {
        publisherService.deletePublisher(publisherId);
    }
}
