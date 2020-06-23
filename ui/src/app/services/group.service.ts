import { Injectable } from '@angular/core';
import { AngularFirestore, AngularFirestoreCollection } from '@angular/fire/firestore';
import { Route, Router } from '@angular/router';
import { map } from 'rxjs/operators';
import * as firebase from 'firebase/app';
import { HttpParams, HttpClient } from '@angular/common/http' 

@Injectable({
  providedIn: 'root'
})

export class GroupService {

  constructor(public http: HttpClient ) { }

  API: string = 'http://18.224.140.96:8080';
  GROUP_API: string = this.API + '/groups' 

  addPoll(groupId: any, nickname: string, pollTitle: string, questionDetails: any, deadline: any){
    var body: any = {
      "authorNick": nickname,
      "polDeadline": deadline,
      "pollName": pollTitle,
      "questionDetails": questionDetails
    };
    console.log(body)
    return this.http.post<any>(this.GROUP_API + "/" + groupId + "/polls", body);
  }

  addGroup(nickname: string, groupName: string){
     var body: any = {
      "leaderNick": nickname,
      "groupName": groupName,
      "groupMembersNicks": []
    };
    console.log(body);
    return this.http.post<any>(this.GROUP_API, body);
  }

  addGroupMember(id: any, nick: string){
    //temperary solution
    var request = this.GROUP_API + "/" + id + "/members" + "?userNick=" + nick;
    return this.http.post(request, {});
  }

  removeGroupMember(id: any, nick: string){
    return this.http.delete(this.GROUP_API + "/" + id + "/members/" + nick)
  }

  getGroup(id: any){
    return this.http.get(this.GROUP_API + "/" + id);
  }

  getPolls(id: any){
    return this.http.get(this.GROUP_API + "/" + id + "/polls")
  }  

  addMessage(id: any, username: any, context: any, deadline: any)
  {
    return this.http.post(this.GROUP_API + "/" + id + "/messages", 
      {"authorNick": username,
       "context": context,
       "deadline": deadline
      })
  }

  getMessages(id: any){
    return this.http.get(this.GROUP_API + "/" + id + "/messages")
  }
}
