import { Component, OnInit } from '@angular/core';
import { PollService } from '../poll.service'
import { Question } from '../question';
import { Answer } from './answer';

@Component({
  selector: 'app-show-poll',
  templateUrl: './show-poll.page.html',
  styleUrls: ['./show-poll.page.scss'],
})
export class ShowPollPage implements OnInit {

  questionList: Question[];
  answerList: Answer[];

  constructor(private pollService: PollService) { }

  ngOnInit() {
    this.questionList = this.pollService.getPoll();
    this.answerList = this.initAnswers(this.questionList);
  }

  initAnswers(questions: Question[]): Answer[]{
    let answers = [];
    for (var i = 0; i < questions.length; i++) {
      let answer: Answer = {
        "pollId": questions[i].pollId,
        "questionId": questions[i].questionId,
        "answerText": "",
        "answerAuthorId": 123456
      };
      answers.push(answer)
    }

    return answers;
  }

  helloWorld(){
    console.log(this.questionList)
  }

}
