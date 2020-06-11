import { Component, OnInit } from '@angular/core';
import { GroupService } from '../services/group.service'
import { ActivatedRoute }  from '@angular/router'
import { Router } from '@angular/router';
import {Location} from '@angular/common';
import { MessageService } from '../services/message.service'

@Component({
  selector: 'app-message-stats',
  templateUrl: './message-stats.page.html',
  styleUrls: ['./message-stats.page.scss'],
})
export class MessageStatsPage implements OnInit {

  messageId: number;
  groupId: number;
  messageData: any;
  questionsData: any;
  answeredNames = [];

  answersData: any;

  constructor(private route: ActivatedRoute, 
    private groupSerivce: GroupService, 
    private router: Router,
    private location: Location,
    private messageService: MessageService) { }

  ngOnInit() {
    this.groupId = parseInt(this.route.snapshot.paramMap.get('groupId'));
    this.messageId = parseInt(this.route.snapshot.paramMap.get('messageId'));
    this.messageService.getMessageStats(this.messageId).subscribe(data => {
      this.messageData = data;
      this.answeredNames = this.getAnsweredNames(data)
    });
  }

  getAnsweredNames(data){
    let namesTable: any[] = [];
    for(let item of data.usersWhoAnwsered){
        console.log(item.user.userNick)
        if(namesTable.includes(item.user.userNick) === false){
          namesTable.push(item.user.userNick)
        }
    }
    return namesTable;
  }

  goToGroupPage(){
    this.router.navigate(["/group", this.groupId])
  }



}
