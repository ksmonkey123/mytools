import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {map} from 'rxjs/operators';

@Injectable()
export class UserService {

  urls = {
    info: '/rest/user/me',
    getById: '/rest/user/',
    password: '/rest/user/password',
    patchUser: '/rest/user/',
    userList: '/rest/user/list',
    allRoles: '/rest/user/roles',
  };

  constructor(private http: HttpClient) {
  }

  getUserInfo(): Observable<User> {
    return this.http.get<User>(this.urls.info);
  }

  getUserInfoById(id: number): Observable<User> {
    return this.http.get<User>(this.urls.getById + id);
  }

  changePassword(oldPW: string, newPW: string): Observable<boolean> {
    return this.http.post(this.urls.password, {password: oldPW, newPassword: newPW})
      .pipe(map(x => true));
  }

  setPassword(userId: number, password: string): Observable<User> {
    return this.http.patch<User>(this.urls.patchUser + userId, {password: password})
  }

  enableUser(userId: number, enable: boolean): Observable<User> {
    return this.http.patch<User>(this.urls.patchUser + userId, {active: enable})
  }

  getAvailableRoles(): Observable<string[]> {
    return this.http.get<string[]>(this.urls.allRoles);
  }

  getUserList() {
    return this.http.get<User[]>(this.urls.userList);
  }
}

export interface User {
  id: number;
  name: string;
  roles: string[];
  active: boolean;
}
