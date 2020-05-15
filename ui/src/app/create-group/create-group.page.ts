import { Component, OnInit } from '@angular/core';
import { AngularFireAuth } from '@angular/fire/auth';
import { auth } from 'firebase/app';
import { Router } from '@angular/router';
import { ServicesService } from '../services/services.service';
import { GroupService } from '../services/group.service'

@Component({
  selector: 'app-create-group',
  templateUrl: './create-group.page.html',
  styleUrls: ['./create-group.page.scss'],
})
export class CreateGroupPage implements OnInit {

  item: any;
  id: string;
  groupName: string = "";

  username: string;

  constructor(private aut: AngularFireAuth,
    private router: Router , public services: ServicesService,
    public groupService: GroupService ) {

    }

  ngOnInit() {
    this.logued();
  }

  addGroup(){
    //console.log(this.groupService)
    this.groupService.addGroup(this.username, this.groupName).subscribe();
    //console.log("Hello")
    this.router.navigateByUrl('/')
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
        console.log(this.username);
      }
    });
  }
}
