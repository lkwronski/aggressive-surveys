import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ServicesService } from '../services/services.service'
import { PollService } from '../services/poll.service'
import { AngularFireAuth } from '@angular/fire/auth';
import { GroupService } from '../services/group.service';
import { ActivatedRoute }  from '@angular/router'

@Component({
  selector: 'app-create-poll',
  templateUrl: './create-poll.page.html',
  styleUrls: ['./create-poll.page.scss'],
})
export class CreatePollPage implements OnInit {

  groupId: number;
  item: any;
  username: string;
  id: any;
  questionList: any[];
  title: string;
  deadline: string;

  TEXT = "TEXT"
  CHECKBOX = "CHECKBOX"

  constructor(private route: ActivatedRoute,
    private router: Router, private services: ServicesService,
    private pollService: PollService, private aut: AngularFireAuth, public groupService: GroupService) { }

  ngOnInit() {
    this.groupId = parseInt(this.route.snapshot.paramMap.get('id'));
    this.logued();
    this.title = "";
    this.questionList = []
    this.deadline = "2020-05-30T20:00:00.000";
    
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

  addTextQuestion(){
    let q: any = {
      "questionText": "",
      "options": [],
      "questionType": this.TEXT
    }
    this.questionList.push(q)
  }

  addCheckboxQuestion(){
    let q: any = {
      "questionText": "",
      "options": [],
      "questionType": this.CHECKBOX
    }
    this.questionList.push(q)
  }

  addAnswer(q: any, o: string){
    let option: any = {
      "answerText": o
    }
    q.options.push(option);
  }

  convertOption(q: any){
    let result = [];
    for(let ans of q.options){
      result.push(ans.answerText)
    }
    q.options = result;
  }

  removeAnswer(options: any[], removedOption: any){
    var index = options.indexOf(removedOption, 0);
    if (index > -1){
      options.splice(index, 1);
    }
  }

  removeQuestion(removedQuestion: any){
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

  send(){
    //console.log(this.groupService)
    for(let question of this.questionList){
      this.convertOption(question)
    }
    console.log(this.questionList)
    this.groupService.addPoll(this.groupId, this.username, this.title, this.questionList, this.deadline).subscribe(data =>
      console.log(data))
  }

  goToGroupPage(){
    this.router.navigate(["/group", this.groupId])
  }

}
