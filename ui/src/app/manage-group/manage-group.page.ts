import { Component, OnInit } from '@angular/core';
import { GroupService } from '../services/group.service'
import { ActivatedRoute }  from '@angular/router'
import { Router } from '@angular/router';

@Component({
  selector: 'app-manage-group',
  templateUrl: './manage-group.page.html',
  styleUrls: ['./manage-group.page.scss'],
})
export class ManageGroupPage implements OnInit {

  id: number;
  group: any;
  nicknameToAdd: string = '';

  constructor(private route: ActivatedRoute, private groupService: GroupService,
    private router: Router) {
   }

  ngOnInit() {
    this.id = parseInt(this.route.snapshot.paramMap.get('id'));
    this.groupService.getGroup(this.id).subscribe(data => 
      this.group = data);
  }

  removeMember(nickname){
    this.groupService.removeGroupMember(this.id, nickname).subscribe(data =>
      console.log(data));
    window.location.reload();
  }

  addMember(){
    console.log(this.nicknameToAdd)
    this.groupService.addGroupMember(this.id, this.nicknameToAdd).subscribe(data =>
      console.log(data));
    window.location.reload();
  }

  goBack(){
    this.router.navigate(['/group', this.id])
  }

}
