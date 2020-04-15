import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  
  {
    path: 'show-poll',
    loadChildren: () => import('./show-poll/show-poll.module').then( m => m.ShowPollPageModule)
  },
  {
    path: '',
    loadChildren: () => import('./base-page/base-page.module').then( m => m.BasePagePageModule)
  }
];
@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {}
