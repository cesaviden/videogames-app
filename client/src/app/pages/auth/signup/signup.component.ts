import { Component, inject } from '@angular/core';
import { AuthService } from '../../services/auth/auth.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Subscription, timer } from 'rxjs';
import { NotificationService } from '../../services/notification/notification.service';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [ CommonModule, FormsModule ],
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignUpComponent {

  name: string;
  surname: string;
  username: string;
  password: string;
  email: string;  
  private notificationSubscription: Subscription;
  errorMessage: string;

  constructor(private authService: AuthService, private route: Router, private notificationService: NotificationService) {

    this.notificationSubscription = this.notificationService.getNotifications().subscribe(message => {
      // Mostrar el mensaje de error recibido
      setTimeout(() => {
        this.errorMessage = '';
      }, 3000); // 5 segundos de expiracion
      this.errorMessage = message;
    }
    );
   }

   ngOnDestroy() {
    // Cancelar la suscripción al servicio de notificación cuando el componente se destruye
    this.notificationSubscription.unsubscribe();
  }

  signup($event:Event) {
    this.authService.register({ username: this.username, password: this.password, email: this.email, name: this.name, surname: this.surname }).subscribe(
      (response) => {    
        this.route.navigate(['/']);
      },
      (error:string) => {
        // Manejar el error, por ejemplo, enviar una notificación
        this.notificationService.addNotification(error);
      }
    );
  }
}

