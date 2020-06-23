import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AngularFireAuth } from '@angular/fire/auth';
import { Observable } from 'rxjs';
import { LoadingController } from '@ionic/angular';
import { finalize } from 'rxjs/operators';
import { AngularFireStorage } from '@angular/fire/storage';
import { ServicesService } from '../services/services.service';
import { UserService } from '../services/user.service';
import { FCM } from '@ionic-native/fcm/ngx';
import { stringify } from 'querystring';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.page.html',
  styleUrls: ['./edit-profile.page.scss'],
})
export class EditProfilePage implements OnInit {
  @ViewChild('imageProd') inputimageProd: ElementRef;
  id: any;
  uid: string;
  name: any;
  surname: string;

  mail: string;
  uploadPercent: Observable<number>;

  item: any;
  username: string;
  
  cp: Boolean;

  constructor(private rout: Router,
    private route: ActivatedRoute,
    private services: ServicesService,
    private afs: AngularFireStorage,
    private loadingController: LoadingController,
    private userConfig: UserService,
    private aut: AngularFireAuth,
    private fcm: FCM) {
  }
  usr: string;
  ngOnInit() {
    this.logueado();
    
  }




  logueado() {
    this.aut.authState
      .subscribe(
        user => {
          if (user) {
            this.mail = user.email;
            this.uid = user.uid;
            console.log(this.mail);
            this.getProfile(this.uid);
          }
        });
  }

  async getProfile(id) {
    await this.services.getProfile(id).subscribe((data: any) => {
      console.log(data);
      if (data.length !== 0) {
        this.cp = true;
        this.id = data[0].payload.doc.id;
        this.name = data[0].payload.doc.data().name;
        this.surname = data[0].payload.doc.data().surname;

        this.username =  data[0].payload.doc.data().username;
        console.log('profil full');
      } else {
        this.cp = false;
        console.log('profile empty');
      }

    });
  }


  onUpload(e) {
    console.log(e.target.files[0]);

    const id = Math.random().toString(36).substring(2);
    const file = e.target.files[0];

    this.presentLoading();
  }


  save(name, surname, username) {
    console.log(this.cp);

    const data = {
      name: name,
      surname: surname,
      mail: this.mail,

      uid: this.uid,
      username: username || 'null'
    };
    console.log(data);
    if (this.cp === false) {
      this.userConfig.addUser(name, surname,username, this.mail).subscribe( res => console.log(res) );
      this.services.createUser(data).then(
        res => {
          console.log('Upload' + res);
          this.rout.navigateByUrl(`/main`);
        });

    } else {
      this.userConfig.addUser(name, surname,username, this.mail).subscribe( res => console.log(res) );
      this.services.updateUser(data, this.id).then(
        res => {
          console.log('Upload' + res);
          this.rout.navigateByUrl(`/main`);
        });
      
    }
    

    this.usr = username.toString();
    this.fcm.subscribeToTopic(this.usr);

  }


  async presentLoading() {
    const loading = await this.loadingController.create({
      message: 'Loading image',
      duration: 2000
    });
    await loading.present();

    const { role, data } = await loading.onDidDismiss();

    console.log('Loading dismissed!');
  }
  moveFocus(nextElement) {
    nextElement.setFocus();
  }

}
