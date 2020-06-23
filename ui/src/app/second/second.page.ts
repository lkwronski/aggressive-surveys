import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-second',
  templateUrl: './second.page.html',
  styleUrls: ['./second.page.scss'],
})
export class SecondPage implements OnInit {

  price: any = '3';

  constructor(private route: ActivatedRoute, private router: Router) {
    
  }

  ngOnInit() {
  }

  goBack(){
    this.router.navigate(['/main'])
  }

}
