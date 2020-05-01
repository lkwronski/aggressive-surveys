import { Component, OnInit } from '@angular/core';
import { GroupService } from '../services/group.service'
import { ActivatedRoute }  from '@angular/router'

@Component({
  selector: 'app-group',
  templateUrl: './group.page.html',
  styleUrls: ['./group.page.scss'],
})
export class GroupPage implements OnInit {

  group: any;
  id: number;
  polls: any;



  constructor(private route: ActivatedRoute, 
    private groupSerivce: GroupService) { }

  ngOnInit() {
    this.id = parseInt(this.route.snapshot.paramMap.get('id'));
    this.groupSerivce.getGroup(this.id).subscribe(data => 
      this.group = data);
    this.groupSerivce.getPolls(this.id).subscribe(data =>
      this.polls = data)
  }

  manageGroup(){
    console.log("Redirect to manage group")
  }

  createPoll(){
    console.log("Redirect to create poll")
  }

}
