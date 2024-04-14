import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth/auth.service';
import { inject } from '@angular/core';

export const authGuard: CanActivateFn = (route, state) => {
  let authService = inject(AuthService);
  let routerService = inject(Router);
  
  if(!authService.isAuthenticated()){
    routerService.navigate(['/log-in']);
    return false;
  }
  return true;
};
