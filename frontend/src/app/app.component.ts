import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontend';

  greeting: Greeting | null = null;

  constructor(private http: HttpClient) {
    http.get<Greeting>('rest/hi').subscribe(data => this.greeting = data);
  }
}

interface Greeting {
  message: string;
  id: string;
}
