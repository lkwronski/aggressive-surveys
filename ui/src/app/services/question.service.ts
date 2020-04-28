import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http' 

@Injectable({
  providedIn: 'root'
})
export class QuestionService {

  constructor(public http: HttpClient ) { }

  API: string = 'http://localhost:8080';
  QUESTION_API: string = this.API + '/questions/' 

  getQuestion(id: any){
    return this.http.get(this.QUESTION_API + id)
  }

  deleteQuestion(id: any){
    return this.http.delete(this.QUESTION_API + id)
  }
}
