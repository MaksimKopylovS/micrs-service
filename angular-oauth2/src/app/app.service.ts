import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Injectable} from "@angular/core";

@Injectable({providedIn: 'root'})
export class AppService {

  constructor(private httpClient: HttpClient) { }

  hello(): Observable<string> {
    const headers = new HttpHeaders().set('Content-Type', 'text/plain: charset=utf-8');
    return  this.httpClient.get("http://127.0.0.1:8073/v1/organization/home", {headers, responseType: 'text'});
  }
}
