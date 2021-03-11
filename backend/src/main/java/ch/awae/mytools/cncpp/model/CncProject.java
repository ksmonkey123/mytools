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

    @OneToMany
    @JoinColumn(name = "project_id")
    @OrderColumn(name = "projectOrder")
    private List<CncRawGcodeFile> rawGcode = new ArrayList<>();

    public CncProject() {}

    public CncProject(User user, String name) {
        this.user = user;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CncRawGcodeFile> getRawGcode() {
        return rawGcode;
    }
}
