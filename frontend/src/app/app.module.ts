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

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    UserComponent,
    ChangePasswordFormComponent,
    AdminComponent,
    UserSettingsComponent,
    UserRoleManagementComponent
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
    UserService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
