import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HttpClientModule} from "@angular/common/http";
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HomeComponent } from './home/home.component';
import {UserService} from "./user/user.service";
import { UserComponent } from './user/user.component';
import {allIcons, NgxBootstrapIconsModule} from "ngx-bootstrap-icons";
import {FormsModule} from "@angular/forms";
import { AdminComponent } from './admin/admin.component';
import { UserSettingsComponent } from './admin/user-settings/user-settings.component';
import { ChangePasswordFormComponent } from './admin/user-settings/change-password-form/change-password-form.component';
import { UserRoleManagementComponent } from './admin/user-settings/user-role-management/user-role-management.component';
import { CreateUserFormComponent } from './admin/create-user-form/create-user-form.component';
import { CncppRootComponent } from './cncpp/cncpp-root/cncpp-root.component';
import { CncppProjectComponent } from './cncpp/cncpp-project/cncpp-project.component';
import {CncProjectService} from "./cncpp/project.service";

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    UserComponent,
    ChangePasswordFormComponent,
    AdminComponent,
    UserSettingsComponent,
    UserRoleManagementComponent,
    CreateUserFormComponent,
    CncppRootComponent,
    CncppProjectComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    NgbModule,
    NgxBootstrapIconsModule.pick(allIcons),
    FormsModule
  ],
  providers: [
    UserService,
    CncProjectService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
