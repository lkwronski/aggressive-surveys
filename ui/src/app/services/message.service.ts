import { Injectable } from '@angular/core';
import { HttpParams, HttpClient } from '@angular/common/http' 

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  constructor(private http: HttpClient) { }

  API: string = 'http://localhost:8080';
  MESSAGE_API: string = this.API + '/messages/' 

  getMessageById(id: number){
    return this.http.get(this.MESSAGE_API + id);
  }

  deleteMessageById(id: number){
    return this.http.delete(this.MESSAGE_API + id);
  }

  acknowledgeMessage(id: number, nickname: any){
    return this.http.post(this.MESSAGE_API + id + "/acknowledgments/" + nickname, {});
  }

  getMessageStats(id: any){
    return this.http.post(this.MESSAGE_API + "/" + id + "/acknowledgments", {})
  }
}
