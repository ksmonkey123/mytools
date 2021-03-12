import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ProjectSummary} from "./cncpp.model";

@Injectable()
export class CncProjectService {

  urls = {
    list: '/rest/cncpp/project',
    create: '/rest/cncpp/project',
    delete: (id: number) => '/rest/cncpp/project/' + id
  };

  constructor(private http: HttpClient) {
  }

  getProjectList() {
    return this.http.get<ProjectSummary[]>(this.urls.list)
  }

  createProject(newProjectName: string) {
    return this.http.post<ProjectSummary>(this.urls.create, {name: newProjectName})
  }

  archiveProject(id: number) {
    return this.http.delete<any>(this.urls.delete(id))
  }

}
