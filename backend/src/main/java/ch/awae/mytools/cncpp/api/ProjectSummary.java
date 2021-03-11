package ch.awae.mytools.cncpp.api;

import ch.awae.mytools.cncpp.model.CncProject;

class ProjectSummary {

    final long id;
    final String name;

    ProjectSummary(CncProject project) {
        this.id = project.getId();
        this.name = project.getName();
    }

}
