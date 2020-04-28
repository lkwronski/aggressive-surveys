import { Injectable } from '@angular/core';
import { AngularFirestore, AngularFirestoreCollection } from '@angular/fire/firestore';
import { Route, Router } from '@angular/router';
import { map } from 'rxjs/operators';
import * as firebase from 'firebase/app';
import { HttpClient } from '@angular/common/http' 

@Injectable({
  providedIn: 'root'
})

export class GroupService {

  constructor(public http: HttpClient ) { }

  API: string = 'http://localhost:8080';
  GROUP_API: string = this.API + '/groups' 

  addGroup(nickname: string, groupName: string){
     var body: any = {
      "leaderNick": nickname,
      "groupName": groupName,
      "groupMembersNicks": []
    };
    console.log(body);
    return this.http.post<any>(this.GROUP_API, body);
  }



  
}
