import { Component, OnInit } from '@angular/core';
import { GroupService } from '../services/group.service'
import { ActivatedRoute }  from '@angular/router'
import { Router } from '@angular/router';
import { connectableObservableDescriptor } from 'rxjs/internal/observable/ConnectableObservable';

@Component({
  selector: 'app-group-stats',
  templateUrl: './group-stats.page.html',
  styleUrls: ['./group-stats.page.scss'],
})
export class GroupStatsPage implements OnInit {

  group: any;
  id: number;
  polls: any;
  messages: any;
  constructor(private route: ActivatedRoute, 
    private groupSerivce: GroupService, private router: Router) { }

  ngOnInit() {
    this.id = parseInt(this.route.snapshot.paramMap.get('id'));
    this.groupSerivce.getGroup(this.id).subscribe(data => 
      this.group = data);
    this.groupSerivce.getPolls(this.id).subscribe(data =>
      this.polls = data)
    this.groupSerivce.getMessages(this.id).subscribe(data =>
      this.messages = data)
    console.log(this.polls)
  }

  onPollSelect(pollId: number){
    console.log(pollId);
    this.router.navigate(['/group-stats', this.id, 'poll-stats', pollId])
  }

}
