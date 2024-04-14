import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { environment } from '../../../enviroments/enviroment';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly JWT_TOKEN = 'JWT_TOKEN';
  private readonly username = 'username';
  private loggedUser: string;
  isAuthenticatedSubject = new BehaviorSubject<boolean>(false);

  constructor(private http: HttpClient) {}

  login(user: { username: string; password: string }): Observable<any> {
    return this.http.post(environment.urlHost + '/auth/log-in', user)
      .pipe(
        tap((response: any) => this.doLoginUser(user.username, response.token)),
        catchError(this.handleError)
      );
  }

  register(user: { username: string; password: string, email: string, name: string, surname: string }): Observable<any> {
    return this.http.post(environment.urlHost + '/auth/sign-up', user)
      .pipe(
        tap((response: any) => this.doLoginUser(user.username, response.token)),
        catchError(this.handleError)
      );
  }

  private doLoginUser(username: string, token: any) {
    this.loggedUser = username;
    sessionStorage.setItem(this.username, username);
    this.storeJwtToken(token);
    this.isAuthenticatedSubject.next(true);
  }

  private storeJwtToken(jwt: string) {
    sessionStorage.setItem(this.JWT_TOKEN, jwt);
  }

  logout() {
    sessionStorage.removeItem(this.JWT_TOKEN);
    this.isAuthenticatedSubject.next(false);
  }

  isAuthenticated() {
    return !!sessionStorage.getItem(this.JWT_TOKEN);
  }

  private handleError(error: HttpErrorResponse) {
    let errorMessage = 'An unknown error occurred';
    if (error.error instanceof ErrorEvent) {
      // Error del lado del cliente
      errorMessage = `Error: ${error.error.message}`;
    } else {
      // Error del lado del servidor
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    // Retornar un observable con el mensaje de error
    return throwError(errorMessage);
  }
}
