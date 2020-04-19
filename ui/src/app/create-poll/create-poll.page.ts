import { Component, OnInit } from '@angular/core';
import { QuestionDetails } from '../questionDetails';
import { setIndex } from '@ionic-native/core/decorators/common';
import { Option } from '../option'

@Component({
  selector: 'app-create-poll',
  templateUrl: './create-poll.page.html',
  styleUrls: ['./create-poll.page.scss'],
})
export class CreatePollPage implements OnInit {

  questionList: QuestionDetails[];
  title: String;

  TEXT = "text"
  CHECKBOX = "checkbox"

  constructor() { }

  ngOnInit() {
    this.title = "";
    this.questionList = [];
  }

  addTextQuestion(){
    let q: QuestionDetails = {
      "questionText": "",
      "options": [],
      "questionType": "text"
    }
    this.questionList.push(q)
  }

  addCheckboxQuestion(){
    let q: QuestionDetails = {
      "questionText": "",
      "options": [],
      "questionType": "checkbox"
    }
    this.questionList.push(q)
  }

  addAnswer(q: QuestionDetails, o: string){
    let option: Option = {
      "answerText": o
    }
    q.options.push(option);
  }

  removeAnswer(options: Option[], removedOption: Option){
    var index = options.indexOf(removedOption, 0);
    if (index > -1){
      options.splice(index, 1);
    }
  }

  removeQuestion(removedQuestion: QuestionDetails){
    var index = this.questionList.indexOf(removedQuestion, 0);
    if (index > -1){
      this.questionList.splice(index, 1);
    }
  }

  validateAndSend(){
    let canSend: boolean = this.validate()
    if (canSend){
      this.send()
    }
    
  }

  validate(): boolean{
    return true;
  }

  send() {
    console.log(this.questionList)
  }
}
