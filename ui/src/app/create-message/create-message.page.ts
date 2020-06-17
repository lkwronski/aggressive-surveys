import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ServicesService } from '../services/services.service'
import { MessageService } from '../services/message.service'
import { AngularFireAuth } from '@angular/fire/auth';
import { GroupService } from '../services/group.service';
import { ActivatedRoute }  from '@angular/router'
import { AlertController } from '@ionic/angular';  

@Component({
  selector: 'app-create-message',
  templateUrl: './create-message.page.html',
  styleUrls: ['./create-message.page.scss'],
})
export class CreateMessagePage implements OnInit {

  groupId: number;
  item: any;
  username: string;
  id: any;
  messageText: any;

  constructor(private route: ActivatedRoute,
    private router: Router, private services: ServicesService,
    private messageService: MessageService, 
    private aut: AngularFireAuth, 
    public groupService: GroupService,
    private alertCtrl: AlertController) { }

  ngOnInit() {
    this.groupId = parseInt(this.route.snapshot.paramMap.get('id'));
    this.logued();
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

  async showErrorAlert() {  
    const alert = await this.alertCtrl.create({  
      header: 'Message',  
      message: 'Something went wrong trying to send a message please try once again',  
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
      message: 'Successfully created a message',  
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

  validateAndSend(){
    this.send();
  }

  send(){
    this.groupService.addMessage(this.groupId, this.username, this.messageText, "2020-06-30T18:57:52.973")
    .subscribe(data => {console.log(data);
                        this.showSuccessAlert()},
                        (error) => {
                          this.showErrorAlert();
                        })
  }

  goToGroupPage(){
    this.router.navigate(["/group", this.groupId])
  }
}
