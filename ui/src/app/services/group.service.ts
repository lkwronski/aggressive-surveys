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

  constructor(private http: HttpClient ) { }

  API = 'localhost:8080/groups/'

  addGroup(nickname: string, groupName: string){
     var body: any = {
      "leaderNick": nickname,
      "groupName": groupName,
      "groupMembersNicks": []
    };

    return this.http.post(this.API, body);
  }



  
}
