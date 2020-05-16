
import { Component , OnInit} from '@angular/core';
import { AngularFireAuth } from '@angular/fire/auth';
import { auth, User } from 'firebase/app';
import { Router } from '@angular/router';
import { ServicesService } from '../services/services.service';
import { UserService } from '../services/user.service';
import { AlertController } from '@ionic/angular';  
import { MessageService } from '../services/message.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.page.html',
  styleUrls: ['./main.page.scss'],
})
export class MainPage implements OnInit {

  item: any;
  id: string;

  username: string;
  managedGroups: any;
  memberGroups: any;
  unansweredMessages: any;


  constructor(private aut: AngularFireAuth,
    private router: Router ,
    public services: ServicesService, 
    public user: UserService,
    public alertCtrl: AlertController,
    public messageService: MessageService) {
    }

  ngOnInit() {
    this.logued();
    console.log(this.managedGroups)
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

  async signOut() {
    const res = await this.aut.auth.signOut();
    console.log(res);
    this.router.navigateByUrl('/login');
  }

  createGroup() {
    this.router.navigateByUrl('/create-group')
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
        this.getManagedGroups();
        this.getMemberGroups();
        this.user.getUnansweredMessages(this.username).subscribe(data =>
          this.unansweredMessages = data)
      }
    });
  }

  profile() {
    this.router.navigateByUrl(`profile`);
  }

  getManagedGroups(){
    console.log("getting managed groups")
    this.user.getManagedGroups(this.username).subscribe(data =>
       this.managedGroups = data)
    console.log(this.managedGroups)
  }

  getMemberGroups() {
    this.user.getGroups(this.username).subscribe(data =>
      this.memberGroups = data)
  }

  onSelect(group){
    console.log(group)
    this.router.navigate(['/group', group.groupId])
  }

  onMessage(message){
    console.log(message);
    this.showAlert(message);
  }

  async showAlert(message) {  
    const alert = await this.alertCtrl.create({  
      header: 'Message',  
      subHeader: message.authorNick,
      message: message.context,  
      buttons: [
        {
          text: 'Cancel',
          handler: (blah) => {
            console.log('Cancelled');
          }
        }, {
          text: 'OK',
          handler: () => {
            console.log('Confirm Okay');
            this.messageService.acknowledgeMessage(message.messageId, this.username).subscribe(data =>
              console.log(data))
          }
        }
      ]
    });
    await alert.present();  
    const result = await alert.onDidDismiss();  
    console.log(result);  
  }  
}
