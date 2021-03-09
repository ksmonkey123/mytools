import {Component, OnInit} from '@angular/core';
import {User, UserService} from "./user.service";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  user ?: User;
  isAdmin: boolean = false;

  constructor(private userService: UserService) {
  }

  ngOnInit() {
    this.userService.getUserInfo().subscribe(user => {
      this.user = user;
      this.isAdmin = user.roles.includes('ROLE_ADMIN');
    })
  }

}
