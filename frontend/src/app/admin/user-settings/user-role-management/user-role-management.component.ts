import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {AppComponent} from "../../../app.component";
import {User, UserService} from "../../../user/user.service";

@Component({
  selector: 'app-user-role-management',
  inputs: ['userId', 'selfManagement'],
  templateUrl: './user-role-management.component.html',
  styleUrls: ['./user-role-management.component.css']
})
export class UserRoleManagementComponent implements OnInit {

  @Output() userUpdatedEvent = new EventEmitter<User>();

  userId: number | null = null
  isCollapsed: boolean = true
  selfManagement: boolean = true

  roles: string[] = []
  user?: User

  constructor(private root: AppComponent,
              private userService: UserService) {
  }

  ngOnInit(): void {
    if (this.userId) {
      this.userService.getAvailableRoles().subscribe(roles => this.roles = roles);
      this.userService.getUserInfoById(this.userId).subscribe(user => this.user = user);
    }
  }

  ngOnDestroy() {
    this.root.closeAlerts(this)
  }

  onToggleRole(userId: number, role: string, enable: boolean) {
    this.userService.toggleUserRole(userId, role, enable).subscribe(
      user => {
        this.user = user;
        this.root.setUser(user);
        this.userUpdatedEvent.emit(user)
      },
      error => this.root.addErrorAlert(error, this)
    )
  }
}
