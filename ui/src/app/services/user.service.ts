import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http' 

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(public http: HttpClient) { }

  API: string = 'http://localhost:8080';
  USER_API: string = this.API + '/users' 

  getGroups(nick: string){
      var request = this.USER_API + '/' + nick + 'groups'
      return this.http.get(request)
  }

  getManagedGroups(nick: string){
    var request = this.USER_API + '/' + nick + 'managedGroups'
    return this.http.get(request)
  }

  getUser(nick: string){
    var request = this.USER_API + '/' + nick
    return this.http.get(request)
  }

}
