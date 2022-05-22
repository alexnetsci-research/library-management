package alexnetsci.research.librarymanagement.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "publishers")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long publisherId;

    @NotNull
    @Size(max = 50)
    private String name;

    @CreationTimestamp
    private LocalDateTime creationDate;

    @UpdateTimestamp
    private LocalDateTime updatedDate;

    public Publisher() {
    }

    public Publisher(Long publisherId, String name, LocalDateTime creationDate, LocalDateTime updatedDate) {
        this.publisherId = publisherId;
        this.name = name;
        this.creationDate = creationDate;
        this.updatedDate = updatedDate;
    }

    public Publisher(String name, LocalDateTime creationDate, LocalDateTime updatedDate) {
        this.name = name;
        this.creationDate = creationDate;
        this.updatedDate = updatedDate;
    }

    public Long getPublisherId() {
        return publisherId;
    }

    public void PublisherId(Long publisherId) {
        this.publisherId = publisherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "publisherId=" + publisherId +
                ", name='" + name + '\'' +
                ", creationDate=" + creationDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
