import {Component, OnInit} from '@angular/core';
import {AppComponent} from "../../app.component";
import {User, UserService} from "../../user/user.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-user-settings',
  templateUrl: './user-settings.component.html',
  styleUrls: ['./user-settings.component.css']
})
export class UserSettingsComponent implements OnInit {
  user?: User;
  isOwnUser: boolean = true;

  constructor(
    private root: AppComponent,
    private userService: UserService,
    private activeRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.activeRoute.params.subscribe(params => {
      this.userService.getUserInfoById(params['id']).subscribe(u => {
        this.user = u;
        if (this.root.user) {
          this.isOwnUser = this.root.user.id === this.user.id
        }
      });
    });
  }

  ngOnDestroy() {
    this.root.closeAlerts(this)
  }

  onEnableUser(enable: boolean) {
    if (this.user) {
      this.userService.enableUser(this.user.id, enable).subscribe(
        user => {
          this.root.addAlert({type: 'success', message: 'user ' + (enable ? 'enabled' : 'disabled'), parent: this})
          this.user = user
        },
        error =>
          this.root.addErrorAlert(error, this)
      )
    }
  }

}
