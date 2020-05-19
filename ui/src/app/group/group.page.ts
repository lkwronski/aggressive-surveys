import { Component, OnInit } from '@angular/core';
import { GroupService } from '../services/group.service'
import { ActivatedRoute }  from '@angular/router'
import { Router } from '@angular/router';
import {Location} from '@angular/common';

@Component({
  selector: 'app-group',
  templateUrl: './group.page.html',
  styleUrls: ['./group.page.scss'],
})
export class GroupPage implements OnInit {

  group: any;
  id: number;
  polls: any;
  messages: any;

  constructor(private route: ActivatedRoute, 
    private groupSerivce: GroupService, 
    private router: Router,
    private location: Location) { }

  ngOnInit() {
    this.id = parseInt(this.route.snapshot.paramMap.get('id'));
    this.groupSerivce.getGroup(this.id).subscribe(data => 
      this.group = data);
    this.groupSerivce.getPolls(this.id).subscribe(data =>
      this.polls = data)
    this.groupSerivce.getMessages(this.id).subscribe(data =>
      this.messages = data)
  }

  manageGroup(){
    console.log("Redirect to manage group")
    this.router.navigate(['/manage-group', this.id])
  }

  checkStats(){
    console.log("Redirect to stats")
    this.router.navigate(['/group-stats', this.id])
  }

  createPoll(){
    console.log("Redirect to create poll")
    this.router.navigate(['/group', this.id, 'create-poll'])
  }

  createMessage(){
    console.log("Redirect to create message")
    this.router.navigate(['/group', this.id, 'create-message'])
  }

  onSelect(poll){
    console.log(poll)
    this.router.navigate(['/group', this.id, 'answer-poll', poll.pollId])
  }

  onSelectMessage(message){
    console.log(message)
  }

  goToMainPage(){
    this.router.navigateByUrl('');
  }

}
