import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminLayoutComponent } from '../layout/admin-layout/admin-layout.component';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { UsersComponent } from './users/users.component';
import { WorkInProgressComponent } from './work-in-progress/work-in-progress.component';
import { HangmenComponent } from './hangmen/hangmen.component';
import { FindawordsComponent } from './findawords/findawords.component';
import { FindmistakesComponent } from './findmistakes/findmistakes.component';
import { GuesspicturesComponent } from './guesspictures/guesspictures.component';
import { OrganizesentencesComponent } from './organizesentences/organizesentences.component';
import { QuizComponent } from './quiz/quiz.component';

/**
 * Modulo di routing dell'admin. Qui ci sono i percorsi che un admin può seguire:
 * appena fa il login viene caricato nel <router-outlet> di app-component il layout e nel 
 * <router-outlet> del layout (come percorsi "children") vengono visualizzati gli altri 
 * (qui sotto sono indentati).
 * 
 * @author Vittorio Valent
 * 
 * @see AdminLayoutComponent
 * 
 * @see layout
 */
const routes: Routes = [
  { path: 'admin-dashboard', component: AdminLayoutComponent, children:[
    { path: '', component: AdminDashboardComponent},
    { path: 'users', component: UsersComponent},
    { path: 'work-in-progress', component: WorkInProgressComponent},
    { path: 'games', children: [
      { path: 'findawords', component: FindawordsComponent},
      { path: 'findmistakes', component: FindmistakesComponent},
      { path: 'guesspictures', component: GuesspicturesComponent},
      { path: 'hangmen', component: HangmenComponent},
      { path: 'organizesentences', component: OrganizesentencesComponent},
      { path: 'quiz', component: QuizComponent}
    ]}
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }