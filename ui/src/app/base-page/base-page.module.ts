import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { BasePagePageRoutingModule } from './base-page-routing.module';

import { BasePagePage } from './base-page.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    BasePagePageRoutingModule
  ],
  declarations: [BasePagePage]
})
export class BasePagePageModule {}
