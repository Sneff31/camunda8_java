import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private apiUrl = 'http://localhost:8081/api/deploy/process'; // Your Spring Boot API URL

  constructor(private http: HttpClient) { 
    console.log("api service called");
  }

  // https://angular.dev/guide/http/making-requests

  // Method to get data from backend
  async postDeployCamundaProcess(processName: string): Promise<any> {
    console.log("post call for new process")
    this.http.post(this.apiUrl,{"name":processName}).subscribe();
    //const data = await fetch(this.apiUrl);
  }
}
