import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  constructor(private http: HttpClient) { }

  postData(endpoint: string, data: any){
    return this.http.post(endpoint, data);
  }

  getData(endpoint: string){
    return this.http.get(endpoint);
  }
}
