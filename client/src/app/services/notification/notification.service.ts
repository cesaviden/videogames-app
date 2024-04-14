import { Injectable } from '@angular/core';
import { Subject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {
  private notificationSubject = new Subject<string>();

  constructor() {}

  // Método para agregar una nueva notificación
  addNotification(message: string) {
    this.notificationSubject.next(message);
  }

  // Método para obtener las notificaciones como un observable
  getNotifications(): Observable<string> {
    return this.notificationSubject.asObservable();
  }
}
