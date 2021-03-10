package ch.awae.mytools.cncpp.model;

import ch.awae.mytools.datastore.model.DatastoreEntry;

import javax.persistence.*;

@Entity
@Table(name = "cncpp_raw_gcode")
public class CncRawGcodeFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private CncProject project;

    @Column(nullable = false)
    private String name;

    @OneToOne(optional = false)
    private DatastoreEntry datastoreEntry;

    public Long getId() {
        return id;
    }

    public CncProject getProject() {
        return project;
    }

    public void setProject(CncProject project) {
        this.project = project;
    }

    public DatastoreEntry getDatastoreEntry() {
        return datastoreEntry;
    }

    public void setDatastoreEntry(DatastoreEntry datastoreEntry) {
        this.datastoreEntry = datastoreEntry;
    }
}
