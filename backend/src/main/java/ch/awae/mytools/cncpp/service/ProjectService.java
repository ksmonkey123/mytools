package ch.awae.mytools.cncpp.service;

import ch.awae.mytools.cncpp.model.CncProject;
import ch.awae.mytools.cncpp.model.repo.CncProjectRepository;
import ch.awae.mytools.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class ProjectService {

    private final UserService userService;
    private final CncProjectRepository projectRepository;

    @Autowired
    public ProjectService(UserService userService, CncProjectRepository projectRepository) {
        this.userService = userService;
        this.projectRepository = projectRepository;
    }

    public Stream<CncProject> getProjectList() {
        return projectRepository.findByUserAndArchivedOrderByIdDesc(userService.getCurrentUser(), false);
    }

    public Optional<CncProject> getProject(long projectId) {
        return projectRepository.findByIdAndUser(projectId, userService.getCurrentUser());
    }

    @Nonnull
    public CncProject createProject(@Nonnull String name) {
        CncProject project = new CncProject();
        project.setUser(userService.getCurrentUser());
        project.setName(name);
        return projectRepository.saveAndFlush(project);
    }
}
