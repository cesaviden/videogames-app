import { CommonModule } from '@angular/common';
import { Component, OnInit, inject } from '@angular/core';
import { Router, NavigationEnd, RouterLink } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AuthService } from '../../services/auth/auth.service';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [RouterLink, CommonModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent implements OnInit {

  isAuthenticated$: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  isAuthenticated: boolean = false;

  constructor(private authService: AuthService, private route: Router) { // Inject AuthService here
  }

  ngOnInit(): void {
    // Subscribe to AuthService's isAuthenticatedSubject for real-time updates
    this.authService.isAuthenticatedSubject.subscribe(isAuthenticated => {
      this.isAuthenticated = isAuthenticated;
      this.isAuthenticated$.next(isAuthenticated);
    });

    // Check initial login state from session storage (optional)
    this.isAuthenticated = sessionStorage.getItem('JWT_TOKEN') !== null;
    this.isAuthenticated$.next(this.isAuthenticated);
    
  }

  logout() {
    this.authService.logout(); // Delegate logout logic to AuthService
  }
}
