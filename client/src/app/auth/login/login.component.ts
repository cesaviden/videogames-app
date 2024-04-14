import { Component, inject } from '@angular/core';
import { AuthService } from '../../services/auth/auth.service';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { NotificationService } from '../../services/notification/notification.service';
import { Subscription } from 'rxjs';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  imports: [CommonModule, FormsModule],
})
export class LoginComponent {
  username: string;
  password: string;
  private notificationSubscription: Subscription;
  errorMessage: string;

  constructor(private authService: AuthService, private router: Router, private notificationService: NotificationService) {
    // Suscribirse al servicio de notificación para recibir mensajes de error
    this.notificationSubscription = this.notificationService.getNotifications().subscribe(message => {
      console.log(message);
      
      setTimeout(() => {
        this.errorMessage = '';
      }, 3000); // 5 segundos de expiracion
      this.errorMessage = message;
    });
  }

  ngOnDestroy() {
    // Cancelar la suscripción al servicio de notificación cuando el componente se destruye
    this.notificationSubscription.unsubscribe();
  }

  login(event: Event) {
    this.authService.login({
      username: this.username,
      password: this.password
    }).subscribe(
      (response) => {
        // Manejar la respuesta exitosa, por ejemplo, redirigir al usuario a la página principal
        this.router.navigate(['/']);
      },
      (error) => {
        // Manejar el error, por ejemplo, enviar una notificación
        this.notificationService.addNotification(error);
      }
    );
  }
}
