import {Component, OnInit} from '@angular/core';
import {AppComponent} from "../../../app.component";
import {User, UserService} from "../../../user/user.service";
import { allIcons } from 'ngx-bootstrap-icons';

@Component({
  selector: 'app-change-password-form',
  inputs: ['userId', 'admin', 'isCollapsed'],
  templateUrl: './change-password-form.component.html',
  styleUrls: ['./change-password-form.component.css']
})
export class ChangePasswordFormComponent implements OnInit {

  iconNames = allIcons;

  isCollapsed: boolean = true;

  userId: number | null = null
  admin: boolean = false
  username: string = ""

  pwmodel: { oldpassword?: string, newpassword?: string } = {}

  constructor(
    private root: AppComponent,
    private userService: UserService) {
  }

  ngOnInit() {
    if (this.userId != null) {
      this.userService.getUserInfoById(this.userId).subscribe(u => this.username = u.name)
    }
  }

  clearInput() {
    this.pwmodel = {}
  }

  ngOnDestroy() {
    this.root.closeAlerts(this)
  }

  changePassword() {
    if (!this.admin) {
      if (this.pwmodel.oldpassword && this.pwmodel.newpassword) {
        this.userService.changePassword(this.pwmodel.oldpassword, this.pwmodel.newpassword).subscribe(
          _ => {
            this.root.addAlert({type: 'success', message: 'password changed', parent: this})
            this.clearInput()
          },
          error =>
            this.root.addErrorAlert(error, this)
        )
      }
    } else {
      if (this.userId && this.pwmodel.newpassword) {
        this.userService.setPassword(this.userId, this.pwmodel.newpassword).subscribe(
          _ => {
            this.root.addAlert({type: 'success', message: 'password changed', parent: this})
            this.clearInput()
          },
          error =>
            this.root.addErrorAlert(error, this)
        )
      }
    }
  }

}
