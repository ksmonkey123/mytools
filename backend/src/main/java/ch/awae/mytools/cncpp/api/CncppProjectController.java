package ch.awae.mytools.cncpp.api;

import ch.awae.mytools.cncpp.service.ProjectService;
import ch.awae.mytools.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@Transactional
@RestController
@RequestMapping("/rest/cncpp/project")
public class CncppProjectController {

    private final ProjectService projectService;

    @Autowired
    public CncppProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<ProjectSummary> getAllUserProjects() {
        return projectService.getProjectList()
                .map(ProjectSummary::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{projectId}")
    public ProjectDetails getProjectDetails(@PathVariable long projectId) {
        return projectService.getProject(projectId)
                .map(ProjectDetails::new)
                .orElseThrow(ResourceNotFoundException.byId("project", projectId));
    }

    @PostMapping
    public ProjectSummary createProject(@Valid @RequestBody CreateProjectRequest request) {
        return new ProjectSummary(projectService.createProject(request.name));
    }

    @DeleteMapping("/{projectId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void archiveProject(@PathVariable long projectId) {
        projectService
                .getProject(projectId)
                .orElseThrow(ResourceNotFoundException.byId("project", projectId))
                .setArchived(true);
    }

    static class CreateProjectRequest {
        @NotNull
        @Size(min = 4)
        public String name;
    }

}
