package ch.awae.mytools.cncpp.api;

import ch.awae.mytools.cncpp.service.ProjectService;
import ch.awae.mytools.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@Transactional
@RestController
@RequestMapping("/rest/cncpp")
public class CncPostProcessorController {

    private final ProjectService projectService;

    @Autowired
    public CncPostProcessorController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<ProjectSummary> getAllUserProjects() {
        return projectService.getProjectsOfCurrentUser()
                .map(ProjectSummary::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{projectId}")
    public ProjectDetails getProjectDetails(@PathVariable long projectId) {
        return projectService.getProjectOfCurrentUser(projectId)
                .map(ProjectDetails::new)
                .orElseThrow(ResourceNotFoundException.byId("project", projectId));
    }

}
