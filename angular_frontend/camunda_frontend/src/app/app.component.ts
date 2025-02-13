import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ApiService } from './api.service';  // Import the service

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  message = 'Please deploy a process!';
  processName: string = '';
  data: any;

  constructor(private ApiService: ApiService) { 
    console.log("app component called");
  }

  deployCamundaProcess(processName:string) {

    console.log("deployCamundaProcess called");

    this.message = "";

    this.ApiService.postDeployCamundaProcess(processName);
    
    this.message = 'Prozess is deployed: ' + processName;
  }

}
