import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import {AuthGuard} from './auth.guard';
import {LayoutComponent} from "./layout/layout.component";

const routes: Routes = [

  { path: 'login', component: HomeComponent},
  { path: '', component:LayoutComponent,children:[
      { path: 'home', component: HomeComponent, canActivate : [AuthGuard] },
      { path: '', redirectTo: '/home', pathMatch: 'full' }
    ] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
