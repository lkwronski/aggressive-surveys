import { Component, OnInit } from '@angular/core';
import { AngularFireAuth } from '@angular/fire/auth';
import { auth } from 'firebase/app';
import { Router, ActivatedRoute } from '@angular/router';
import { ServicesService } from '../services/services.service';
import { GroupService } from '../services/group.service'
import { PollService } from '../services/poll.service'
import { AnswerService } from '../services/answer.service';
import { AnswerBody } from './answerBody';
import { AlertController } from '@ionic/angular';  

@Component({
  selector: 'app-answer-poll',
  templateUrl: './answer-poll.page.html',
  styleUrls: ['./answer-poll.page.scss'],
})
export class AnswerPollPage implements OnInit {

  item: any;
  username: string;
  id: any;
  pollId: number;
  groupId: any;
  questions: any;
  data: any;

  constructor(private aut: AngularFireAuth,
    private route: ActivatedRoute,
    private router: Router,
    private services: ServicesService,
    private pollService: PollService,
    private answerService: AnswerService,
    private alertCtrl: AlertController) { }

  ngOnInit() {
    this.groupId = parseInt(this.route.snapshot.paramMap.get('groupId'));
    this.pollId = parseInt(this.route.snapshot.paramMap.get('pollId'));
    this.logued();
    this.pollService.findPoll(this.pollId).subscribe(data =>
      this.data = data)
  }



  logued() {
    this.aut.authState
      .subscribe(
        user => {
          if (user) {
            console.log('loged');
            this.id = user.uid;
            console.log(this.id);
            this.getProfile(this.id);
          } else {
            this.router.navigateByUrl('/login');
          }
        },
        () => {
          // this.router.navigateByUrl('/login');
        }
      );
  }

  async getProfile(id) {
    await this.services.getProfile(id).subscribe((data: any) => {
      if (data.length === 0) {
        console.log('profile empty');
        this.router.navigateByUrl(`edit-profile`);
      } else {
        console.log('Profile not empty');
        console.log(data);
        this.item = data;
        this.username = this.item[0].payload.doc.data().username;
        console.log(this.username)
      }
    });
  }

  sendAnswers(){
    console.log(this.data.questions);
    let answers: AnswerBody[] = this.getAnswers();
    console.log(answers)
    let pollAnswer = {
      "answerAuthor": this.username,
      "answers": answers
    }
    console.log(pollAnswer);
    this.pollService.postPollAnswer(this.pollId, pollAnswer).subscribe(data =>
      {console.log(data);
        this.showSuccessAlert();
      },
      error => {
        this.showErrorAlert();
      });
  }

  async showErrorAlert() {  
    const alert = await this.alertCtrl.create({  
      header: 'Message',  
      message: 'Something went wrong trying to send an answer please try once again',  
      buttons: [{
          text: 'OK',
          handler: () => {
            console.log('Confirm Okay');
          }
        }
      ]
    });
    alert.present();  
    let result = await alert.onDidDismiss();
    console.log(result);
  }  

  async showSuccessAlert() {  
    const alert = await this.alertCtrl.create({  
      header: 'Message',  
      message: 'Successfully answered a poll',  
      buttons: [{
          text: 'OK',
          handler: () => {
            console.log('Confirm Okay');
          }
        }
      ]
    });
    alert.present();  
    let result = await alert.onDidDismiss();
    console.log(result);
    this.goToGroupPage();
  }  

  showEntry(entry){
    console.log(entry);
  }

  showData(){
    console.log(this.data)
  }

  getAnswers(): AnswerBody[]{
    let answers: AnswerBody[] = []
    console.log(this.data.questions)
    for(var question of this.data.questions){
      var answer: any = {
        "details": {
          "answerType": question.questionDetails.questionType,
        },
        "questionId": question.questionId
      }
      if(question.questionDetails.questionType === 'TEXT'){
        answer.details.answerText = question.answer
      }
      if(question.questionDetails.questionType === 'CHECKBOX'){
        answer.details.selectedOptions = [question.answer]
      }
      if(question.questionDetails.questionType === 'TIME'){
        var answerTimeSlots: any = []
        for(var timeSlot of question.questionDetails.timeSlots){
          let slotAns = {
            "slotTimeId": timeSlot.timeSlotId,
            "slotDay": timeSlot.slotDay,
            "startHour": timeSlot.startHourAns,
            "endHour": timeSlot.endHourAns
          }
          answerTimeSlots.push(slotAns)
        }
        answer.details.timeSlots = answerTimeSlots
      }

      answers.push(answer)
    }
    return answers
  }

  goToGroupPage(){
    this.router.navigate(["group", this.groupId])
  }

}
