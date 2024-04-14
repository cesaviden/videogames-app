import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './auth/login/login.component';
import { SignUpComponent } from './auth/signup/signup.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { authGuard } from './guards/auth.guard';

export const routes: Routes = [
    {
        path : '',
        component : HomeComponent
    },
    {
        path : 'dashboard',
        component : DashboardComponent,
        canActivate: [
            authGuard
        ]
    },
    {
        path : 'log-in',
        component : LoginComponent
    },
    {
        path : 'log-out',
        component : LoginComponent,
        canActivate: [
            authGuard
        ]
    },
    {
        path : 'sign-up',
        component : SignUpComponent,
        
    }
];