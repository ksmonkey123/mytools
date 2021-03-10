package ch.awae.mytools.datastore.model;

import ch.awae.mytools.user.User;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Table(name = "datastore_entry")
public class DatastoreEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String category;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private DatastoreEntryData content;

    public DatastoreEntry() {
    }

    public DatastoreEntry(User user, String category, String title, Blob content) {
        this.user = user;
        this.category = category;
        this.title = title;
        this.content = new DatastoreEntryData(content);
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public DatastoreEntryData getContent() {
        return content;
    }

}
