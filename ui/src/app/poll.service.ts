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
        "questionDetails": { 
          "questionText": "Test Question",
          "questionType": "text"} 
      },
      { 
      "questionId": 2,
      "pollId": 1,
      "questionType": "text", 
      "questionDetails": { 
        "questionText": "Drugie pytanie",
        "questionType": "text"} 
    },
    { 
      "questionId": 3,
      "pollId": 1,
      "questionDetails": { 
        "questionText": "Trzecie pytanie",
        "questionType": "checkbox",
        "options": ["opcja 1", "opcja 2", "opcja 3"]} 
    },
    { 
      "questionId": 4,
      "pollId": 1,
      "questionDetails": { 
        "questionText": "Jak oceniasz pracę?",
        "questionType": "checkbox",
        "options": ["1", "2", "3", "4", "5"]} 
    }
    ];

      let questionList: Question[] = [];

      for (let item of mock){

          var question: any = {
          };
          question.pollId = item.pollId;
          question.questionId = item.questionId;
          question.questionType = item.questionDetails.questionType;
          question.questionText = item.questionDetails.questionText;
          
          if (question.questionType == "checkbox") {
            question.options = item.questionDetails.options;
          }

          questionList.push(question);
      }

      return questionList;


  }
}
