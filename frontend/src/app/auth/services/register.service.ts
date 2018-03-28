import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class RegisterService {

  constructor(private http: HttpClient) { }

  register(username: string, password: string) {
    return this.http.post('http://localhost:8080/v01/signup', {
      username: username,
      password: password
    });
  }

}
