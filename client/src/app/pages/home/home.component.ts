import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { AuthService } from '../../services/auth/auth.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {
  isAuthenticated$: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  isAuthenticated: boolean = false;
  username: string = sessionStorage.getItem('username') || '';

  constructor(private authService: AuthService) { // Inject AuthService here
  }

  ngOnInit(): void {
    // Subscribe to AuthService's isAuthenticatedSubject for real-time updates
    this.authService.isAuthenticatedSubject.subscribe(isAuthenticated => {
      this.isAuthenticated = isAuthenticated;
    });

    // Optional: Check initial login state from session storage
    this.isAuthenticated = sessionStorage.getItem('JWT_TOKEN') !== null;
    
  }
}
