package ch.awae.mytools.cncpp.model;

import ch.awae.mytools.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cncpp_project")
public class CncProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private User user;

    @Column(nullable = false, length = 40)
    private String name;

    private boolean archived;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id")
    @OrderColumn(name = "projectOrder")
    private List<CncRawGcodeFile> rawGcode = new ArrayList<>();

    public CncProject() {}

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CncRawGcodeFile> getRawGcode() {
        return rawGcode;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }
}
