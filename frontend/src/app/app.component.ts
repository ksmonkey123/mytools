import {Component, ViewChild} from '@angular/core';
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {Router} from "@angular/router";
import {Alert} from "./alert";
import {HttpErrorResponse} from "@angular/common/http";
import {User, UserService} from "./user/user.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  isCollapsed = true;

  private rawLinks: Link[] = [
    new Link("CncPP", "/cncpp", "ROLE_CNCPP", "CNC milling post-processor for PCB manufacturing."),
  ];

  links: Link[] = [];

  isAdmin = false;
  public user?: User;
  alerts: Alert[] = [];

  @ViewChild('dangerModal') private dangerModal: any;

  dangerModalData?: DangerModalData;

  constructor(
    private userService: UserService,
    private modalService: NgbModal,
    private router: Router) {
  }

  ngOnInit() {
    this.userService.getUserInfo().subscribe(
      u => this.setUser(u),
      (error: Error) => {
        console.log(error)
      });
  }

  openSettings() {
    this.router.navigate(['/user']);
  }

  openAdminPanel() {
    this.router.navigate(['/admin']);
  }

  addAlert(alert: Alert) {
    this.alerts.push(alert);
  }

  addSuccessAlert(message: string, parent: any) {
    this.addAlert({type: "success", message: message, parent: parent});
  }

  closeAlerts(parent: any) {
    this.alerts = this.alerts.filter((x: Alert) => x.parent !== parent);
  }

  closeAlert(alert: Alert) {
    const index: number = this.alerts.indexOf(alert);
    this.alerts.splice(index, 1);
    console.log("closed");
  }

  addErrorAlert(alert: HttpErrorResponse, parent: any) {
    let status = alert.status;
    let message: string = "";

    switch (status) {
      case 400: {
        message = "Could not perform operation: Invalid Input";
        break;
      }
      default: {
        message = "Operation failed: " + status + " - " + alert.error.error + " : " + alert.error.message;
      }
    }

    this.addAlert({type: 'danger', message: message, parent: parent});

  }

  openDangerModal(model: DangerModalData, onClose?: () => any) {
    this.dangerModalData = model;
    this.modalService.open(this.dangerModal).result.then(
      _ => {
        if (onClose) {
          onClose();
        }
      },
      _ => {
      }
    );
  }

  setUser(u: User) {
    this.user = u;
    this.isAdmin = u.roles.includes("ROLE_ADMIN");
    this.links = this.rawLinks.filter(link => u.roles.includes(link.role));
  }
}

export class Link {

  constructor(
    public text: string,
    public route: string,
    public role: string,
    public desc: string
  ) {
  }

}

export class DangerModalData {
  constructor(
    public action: string,
  ) {
  }
}
