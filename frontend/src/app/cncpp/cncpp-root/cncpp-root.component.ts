import {Component, OnInit} from '@angular/core';
import {CncProjectService} from "../project.service";
import {ProjectSummary} from "../cncpp.model";
import {AppComponent} from "../../app.component";
import {Router} from "@angular/router";

@Component({
  selector: 'app-cncpp-root',
  templateUrl: './cncpp-root.component.html',
  styleUrls: ['./cncpp-root.component.css']
})
export class CncppRootComponent implements OnInit {

  projects: ProjectSummary[] = [];

  newProjectName = "";

  constructor(private root: AppComponent,
              private router: Router,
              private projectService: CncProjectService) {
  }

  ngOnInit(): void {
    this.projectService.getProjectList().subscribe(projects => this.projects = projects)
  }

  onCreateProject() {
    this.projectService.createProject(this.newProjectName).subscribe(
      project => {
        this.projects = [project, ...this.projects];
        this.newProjectName = ""
        this.router.navigate(['/cncpp/', project.id]);
      },
      error => this.root.addErrorAlert(error, this)
    )
  }

  onArchiveProject(id: number) {
    this.projectService.archiveProject(id).subscribe(
      _ => { this.ngOnInit() },
      error => this.root.addErrorAlert(error, this)
    )
  }

  onProjectClicked(id: number) {
    console.log(id)
  }
}
