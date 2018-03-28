import { Component, OnInit } from '@angular/core';
import {NgForm} from '@angular/forms';
import {RegisterService} from '../services/register.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  constructor(private service: RegisterService) { }

  ngOnInit() {
  }

  onSignup(form: NgForm) {
    const username = form.value.username;
    const password = form.value.password;
    this.service.register(username, password).subscribe((user) => {
      console.log("User successfully created!");
    }, (err) => {
      console.error(err);
    })
  }

}
