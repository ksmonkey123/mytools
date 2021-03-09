import { Component, OnInit } from '@angular/core';
import {AppComponent} from "../app.component";
import {User, UserService} from "../user/user.service";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  users: User[] = [];

  constructor(
    private root: AppComponent,
    private userService: UserService) { }

  ngOnInit(): void {
    this.userService.getUserList().subscribe(users => this.users = users);
  }

}
