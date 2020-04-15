import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { BasePagePage } from './base-page.page';

const routes: Routes = [
  {
    path: '',
    component: BasePagePage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class BasePagePageRoutingModule {}
