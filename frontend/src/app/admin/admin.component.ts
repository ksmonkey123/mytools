import {Component, OnInit} from '@angular/core';
import {AppComponent} from "../app.component";
import {User, UserService} from "../user/user.service";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  users: User[] = [];

  showCreationForm = false

  constructor(
    private root: AppComponent,
    private userService: UserService) {
  }

  ngOnInit(): void {
    this.userService.getUserList().subscribe(users => this.users = users);
  }

  ngOnDestroy() {
    this.root.closeAlerts(this)
  }

  onUserCreated(user: User) {
    this.showCreationForm = false
    if (user) {
      this.root.addAlert({type: 'success', message: 'user created', parent: this})
      this.users.push(user);
    }
  }

  onAddUser() {
    this.showCreationForm = true
  }
}
