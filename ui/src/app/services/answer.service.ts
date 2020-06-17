import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http' 

@Injectable({
  providedIn: 'root'
})
export class AnswerService {

  constructor(public http: HttpClient) { }

  API: string = 'http://backend:8080';
  ANSWER_API: string = this.API + '/answer/' 

  findAnswer(id: any){
    return this.http.get(this.ANSWER_API + id)
  }

  deleteAnswer(id: any){
    return this.http.delete(this.ANSWER_API + id)
  }

  getAnswersForQuestion(id: any){
    return this.http.get(this.API + '/questions/' + id + '/answers')
  }

  postAnswer(userId: any, questionId: any, answer: any){
    return this.http.post(this.API + '/user/' + userId + '/questions/' + questionId + '/answers',
      answer)
  }
}
