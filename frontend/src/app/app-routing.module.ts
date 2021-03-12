import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {UserComponent} from "./user/user.component";
import {AdminComponent} from "./admin/admin.component";
import {UserSettingsComponent} from "./admin/user-settings/user-settings.component";
import {CncppRootComponent} from "./cncpp/cncpp-root/cncpp-root.component";
import {CncppProjectComponent} from "./cncpp/cncpp-project/cncpp-project.component";

const routes: Routes = [
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {path: 'home', component: HomeComponent},
  {path: 'user', component: UserComponent},
  {path: 'admin', component: AdminComponent},
  {path: 'admin/user/:id', component: UserSettingsComponent},
  {path: 'cncpp', component: CncppRootComponent},
  {path: 'cncpp/:id', component: CncppProjectComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
