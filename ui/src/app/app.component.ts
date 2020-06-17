import { Component } from '@angular/core';

import { Platform } from '@ionic/angular';
import { SplashScreen } from '@ionic-native/splash-screen/ngx';
import { StatusBar } from '@ionic-native/status-bar/ngx';

import { AngularFireAuth } from '@angular/fire/auth';
import { auth } from 'firebase/app';
import { Router } from '@angular/router';
import { ThemeService } from './services/theme.service';
import { GroupService } from './services/group.service';
import { FCM } from '@ionic-native/fcm/ngx';


@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html'
})
export class AppComponent {

  constructor(
    private platform: Platform,
    private splashScreen: SplashScreen,
    private statusBar: StatusBar,
    public aut: AngularFireAuth,
    private rout: Router,
    private theme: ThemeService,
    private fcm: FCM,
  ) {
    this.initializeApp();
    if ( localStorage.getItem('theme') === 'dark') {
      this.theme.enableDark();
    }
  }

  initializeApp() {
    this.platform.ready().then(() => {
      this.statusBar.styleDefault();
      this.splashScreen.hide();
      this.fcm.getToken().then(token => {
        console.log(token);
      });
    });
    this.fcm.onTokenRefresh().subscribe(token => {
      console.log(token);
    });

    this.fcm.onNotification().subscribe(data => {
      console.log(data);
      if (data.wasTapped) {
        console.log('Received in background');
        this.rout.navigate([data.landing_page, data.price]);
      } else {
        console.log('Received in foreground');
        this.rout.navigate([data.landing_page, data.price]);
      }
    });

    


    this.aut.authState
      .subscribe(
        user => {
          if (user) {
            // this.rout.navigateByUrl('');
          } else {
            this.rout.navigateByUrl('/login');
          }
        },
        () => {
          // this.rout.navigateByUrl('/login');
        }
      );

    
  }
}