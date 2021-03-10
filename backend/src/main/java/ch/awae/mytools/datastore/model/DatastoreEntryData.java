package ch.awae.mytools.datastore.model;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Table(name = "datastore_entry_data")
public class DatastoreEntryData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private Blob data;

    public DatastoreEntryData() {

    }

    public DatastoreEntryData(Blob data) {
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public Blob getData() {
        return data;
    }

    public void setData(Blob data) {
        this.data = data;
    }
}
