import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http' 

@Injectable({
  providedIn: 'root'
})
export class PollService {

  constructor(public http: HttpClient ) { }

  API: string = 'http://localhost:8080';
  POLL_API: string = this.API + '/polls' 

  getAllPolls(){
    return this.http.get(this.POLL_API);
  }

  findPoll(id: any){
    return this.http.get(this.POLL_API + "/" + id)
  }

  getPollQuestions(id: any){
    return this.http.get(this.POLL_API + "/" + id + "/questions")
  }

  addQuestion(id: any, questionDetails: any){
    return this.http.post(this.POLL_API + "/" + id + "/questions", questionDetails)
  }

  getPollStats(id: any){
    //tmp, API should be get 
    return this.http.post(this.POLL_API + "/" + id + "/statistics", {})
  }

  postPollAnswer(id: any, answer: any){
    
    return this.http.post(this.POLL_API + "/" + id + "/answers", answer);
  }
}
