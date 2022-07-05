import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CustomerComponent } from './components/customer/customer.component';
import { ExecutiveComponent } from './components/executive/executive.component';
import { LoginComponent } from './components/login/login.component';
import { ManagerComponent } from './components/manager/manager.component';
import { PropertyComponent } from './components/property/property.component';
import { RegisterComponent } from './components/register/register.component';
import { AuthGuard } from './shared/auth.guard';
import { LogoutGuard } from './shared/logout.guard';

const routes: Routes = [
  {
    path:'login',
    component:LoginComponent
  },
  {
    path:'register',
    component:RegisterComponent
  },
  {
    path:'logout',
    component:LoginComponent,
    canActivate:[LogoutGuard]
  },
  {
    path:'property',
    component:PropertyComponent,
    canActivate:[AuthGuard]
  },
  {
    path:'customer',
    component:CustomerComponent,
    canActivate:[AuthGuard]
  },
  {
    path:'executive',
    component:ExecutiveComponent,
    canActivate:[AuthGuard]
  },
  {
    path:'manager',
    component:ManagerComponent,
    canActivate:[AuthGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
