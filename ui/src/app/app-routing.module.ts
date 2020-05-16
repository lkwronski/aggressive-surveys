import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  { path: '', loadChildren: './main/main.module#MainPageModule' },
  { path: 'login', loadChildren: './login/login.module#LoginPageModule' },
  { path: 'register', loadChildren: './register/register.module#RegisterPageModule' },
  { path: 'profile', loadChildren: './profile/profile.module#ProfilePageModule' },
  { path: 'main', loadChildren: './main/main.module#MainPageModule' },
  { path: 'create-group', loadChildren: './create-group/create-group.module#CreateGroupPageModule' },
  { path: 'edit-profile', loadChildren: './edit-profile/edit-profile.module#EditProfilePageModule' },
  { path: 'group/:id', loadChildren: './group/group.module#GroupPageModule' },
  { path: 'manage-group/:id', loadChildren: './manage-group/manage-group.module#ManageGroupPageModule' },
  { path: 'group/:id/create-poll', loadChildren: './create-poll/create-poll.module#CreatePollPageModule' },
  { path: 'group/:groupId/answer-poll/:pollId', loadChildren: './answer-poll/answer-poll.module#AnswerPollPageModule' },
  { path: 'group/:id/create-message', loadChildren: './create-message/create-message.module#CreateMessagePageModule' },
  { path: 'group-stats/:id', loadChildren: './group-stats/group-stats.module#GroupStatsPageModule' },
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
