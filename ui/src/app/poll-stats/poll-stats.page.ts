import { Component, OnInit } from '@angular/core';
import { GroupService } from '../services/group.service'
import { ActivatedRoute }  from '@angular/router'
import { Router } from '@angular/router';
import {Location} from '@angular/common';
import { PollService } from '../services/poll.service'

@Component({
  selector: 'app-poll-stats',
  templateUrl: './poll-stats.page.html',
  styleUrls: ['./poll-stats.page.scss'],
})
export class PollStatsPage implements OnInit {

  pollId: number;
  groupId: number;
  pollData: any;
  questionsData: any;
  answeredNames = [];

  answersData: any;

  constructor(private route: ActivatedRoute, 
    private groupSerivce: GroupService, 
    private router: Router,
    private location: Location,
    private pollService: PollService) { }

  ngOnInit() {
    this.groupId = parseInt(this.route.snapshot.paramMap.get('groupId'));
    this.pollId = parseInt(this.route.snapshot.paramMap.get('pollId'));
    this.pollService.getPollStats(this.pollId).subscribe(data => {
      this.pollData = data;
      this.answeredNames = this.getAnsweredNames(data)
    });
    this.pollService.getPollQuestions(this.pollId).subscribe(data =>
      this.questionsData = data);
  }

  getAnsweredNames(data){
    let namesTable: any[] = [];
    for(let item of data.respondedUser){
        console.log(item.user.userNick)
        if(namesTable.includes(item.user.userNick) === false){
          namesTable.push(item.user.userNick)
        }
    }
    return namesTable;
  }

  testButton(){
    console.log(this.pollData)
    console.log(this.questionsData)
    console.log(this.answersData)
  }

  showDetails(){
    this.answersData = this.prepareAnswers(this.pollData, this.questionsData);
    console.log(this.answersData)
  }

  prepareAnswers(pollData: any, questionsData: any): any{
    let answersData = []
    // triple loop - it should affect permance, but can be reduced 
    //to double loop by using HashMap
    for(let question of questionsData){
      let id = question.questionId;
      let questionText = question.questionDetails.questionText;
      let questionType = question.questionDetails.questionType;
      let questionAnswers = [];

      for(let user of pollData.respondedUser){
        for(let answer of user.answers){
          if(answer.questionId == id){
            questionAnswers.push(answer.answerDetails)
          }
        }
      }

      answersData.push({
        "questionId": id,
        "questionText": questionText,
        "questionType": questionType,
        "questionAnswers": questionAnswers
      })
    }
    return answersData;
  }

  goBack(){
    this.router.navigate(['group-stats', this.groupId])
  }



}
