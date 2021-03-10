package ch.awae.mytools.cncpp.model;

import ch.awae.mytools.user.User;

import javax.persistence.*;

@Entity
@Table(name = "cncpp_project")
public class CncProject extends CncRawGcodeFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private User user;

    @Column(nullable = false, length = 40)
    private String name;

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
}
