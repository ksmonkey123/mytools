import {Component, OnInit, Output, EventEmitter} from '@angular/core';
import {AppComponent} from "../../app.component";
import {User, UserService} from "../../user/user.service";

@Component({
  selector: 'app-create-user-form',
  templateUrl: './create-user-form.component.html',
  styleUrls: ['./create-user-form.component.css']
})
export class CreateUserFormComponent implements OnInit {

  @Output() userCreatedEvent = new EventEmitter<User>();

  userModel: { username?: string, password?: string } = {}

  constructor(private root: AppComponent,
              private userService: UserService) {
  }

  clearInput() {
    this.userModel = {}
  }

  ngOnInit(): void {
  }

  ngOnDestroy() {
    this.root.closeAlerts(this)
  }

  onSaveUser() {
    if (this.userModel.username && this.userModel.password) {
      this.userService.createUser(this.userModel.username, this.userModel.password).subscribe(
        user => {
          this.clearInput()
          //this.root.addAlert({type: 'success', message: 'user created', parent: this})
          this.userCreatedEvent.emit(user)
        },
        error => this.root.addErrorAlert(error, this)
      )
    }
  }

  onAbort() {
    this.userCreatedEvent.emit(undefined)
  }
}
