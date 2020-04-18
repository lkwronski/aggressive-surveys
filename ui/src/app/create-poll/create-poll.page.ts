import { Component, OnInit } from '@angular/core';
import { QuestionDetails } from '../questionDetails';

@Component({
  selector: 'app-create-poll',
  templateUrl: './create-poll.page.html',
  styleUrls: ['./create-poll.page.scss'],
})
export class CreatePollPage implements OnInit {

  questionList: QuestionDetails[];
  title: String;

  constructor() { }

  ngOnInit() {
    this.title = "";
    this.questionList = [];
  }

  addQuestion(){
    let q: QuestionDetails = {
      "questionText": ""
    }
    this.questionList.push(q)
  }


}
