import { Component, OnInit } from '@angular/core';
import { AngularFireAuth } from '@angular/fire/auth';
import { auth } from 'firebase/app';
import { Router, ActivatedRoute } from '@angular/router';
import { ServicesService } from '../services/services.service';
import { GroupService } from '../services/group.service'
import { PollService } from '../services/poll.service'

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


  constructor(private aut: AngularFireAuth,
    private route: ActivatedRoute,
    private router: Router,
    private services: ServicesService,
    private pollService: PollService) { }

  ngOnInit() {
    this.groupId = parseInt(this.route.snapshot.paramMap.get('groupId'));
    this.pollId = parseInt(this.route.snapshot.paramMap.get('pollId'));
    this.logued();
    this.pollService.findPoll(this.pollId).subscribe(data =>
      this.questions = data.questions)
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

  printQuestions(){
    console.log(this.questions)
  }

}
