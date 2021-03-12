package ch.awae.mytools.cncpp.api;

import ch.awae.mytools.cncpp.model.CncProject;

class ProjectSummary {

    private final long id;
    private final String name;
    private final int fileCount;

    ProjectSummary(CncProject project) {
        this.id = project.getId();
        this.name = project.getName();
        this.fileCount = project.getRawGcode().size();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getFileCount() {
        return fileCount;
    }

}
