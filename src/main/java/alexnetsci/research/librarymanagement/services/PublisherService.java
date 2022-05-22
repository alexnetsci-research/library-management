package alexnetsci.research.librarymanagement.services;

import alexnetsci.research.librarymanagement.entities.Publisher;
import alexnetsci.research.librarymanagement.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PublisherService {

    private final PublisherRepository publisherRepository;

    @Autowired
    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public List<Publisher> getPublishers() {
        return publisherRepository.findAll();
    }

    public Publisher createPublisher(Publisher publisherBody) {
        return publisherRepository.save(publisherBody);
    }

    public Publisher getPublisher(Long publisherId) {
        return publisherRepository.findById(publisherId).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "PUBLISHER_NOT_FOUND"
                )
        );
    }

    public Publisher updatePublisher(Long publisherId, Publisher publisherBody) {
        Publisher currentPublisher = publisherRepository.findById(publisherId).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "PUBLISHER_NOT_FOUND"
                )
        );
        currentPublisher.setName(publisherBody.getName());

        return publisherRepository.save(currentPublisher);
    }

    public void deletePublisher(Long publisherId) {
        Publisher publisher = publisherRepository.findById(publisherId).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "PUBLISHER_NOT_FOUND"
                )
        );

        publisherRepository.deleteById(publisherId);
    }
}
