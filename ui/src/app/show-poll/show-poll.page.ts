import { Component, OnInit } from '@angular/core';
import { PollService } from '../poll.service'
import { Question } from '../question';

@Component({
  selector: 'app-show-poll',
  templateUrl: './show-poll.page.html',
  styleUrls: ['./show-poll.page.scss'],
})
export class ShowPollPage implements OnInit {

  questionList: Question[]

  constructor(private pollService: PollService) { }

  ngOnInit() {
    this.questionList = this.pollService.getPoll();
  }

}
