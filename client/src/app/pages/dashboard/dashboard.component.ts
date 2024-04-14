import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { Videogame } from '../../services/videogame/videogames';
import { VideogameService } from '../../services/videogame/videogame.service';
import { FormsModule } from '@angular/forms';
import { Subscription } from 'rxjs';
import { NotificationService } from '../../services/notification/notification.service';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [ CommonModule, FormsModule ],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {

  videogames:Videogame[];
  name: string;
  description: string;
  price : number;
  year : number;
  company: string;
  private notificationSubscription: Subscription;
  errorMessage: string;

  constructor(private videogameService: VideogameService, private notificationService: NotificationService) {
    this.notificationSubscription = this.notificationService.getNotifications().subscribe(message => {
      setTimeout(() => {
        this.errorMessage = '';
      }, 3000); 
      this.errorMessage = message;
    });
  }

  ngOnInit(): void {
    this.videogameService.getAllVideogames().subscribe((data: Videogame[]) => {
      this.videogames = data;
    });
  }

  addVideogame($event: Event) {
    $event.preventDefault();

    const videogame: Videogame = {
      id: 0,
      name: this.name,
      description: this.description,
      price: this.price,
      year: this.year,
      company: this.company
    };

    this.videogameService.addVideogame(videogame).subscribe(
      (data) => {
        this.videogames.push(data.videogame);
        this.name = '';
        this.description = '';
        this.price = 0;
        this.year = 0;
        this.company = '';
      },
      (error) => {
        this.notificationService.addNotification(error.error);
      }
    );
    }
}
