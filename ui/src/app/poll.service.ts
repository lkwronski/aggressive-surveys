import { Injectable } from '@angular/core';
import { Question } from './question'

@Injectable({
  providedIn: 'root'
})
export class PollService {

  constructor() { }

  getPoll(): Question[] {
      /* Here will be request to API */
      var mock = [{ 
        "questionId": 1,
        "pollId": 1,
        "questionType": "text",
        "quesionDetails": { "questionText": "Test Question"} 
      },
      { 
      "questionId": 2,
      "pollId": 1,
      "questionType": "text", 
      "quesionDetails": { "questionText": "Second Question"} 
    },
    { 
      "questionId": 3,
      "pollId": 1,
      "questionType": "text", 
      "quesionDetails": { "questionText": "Trzecie pytanie"} 
    }
    ];

      let questionList: Question[] = [];

      for (let item of mock){
          var question: Question = {
          };
          question.pollId = item.pollId;
          question.questionId = item.questionId;
          question.questionType = item.questionType;
          question.questionText = item.quesionDetails.questionText;

          questionList.push(question);
      }

      return questionList;


  }
}
