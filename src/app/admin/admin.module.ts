import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AdminRoutingModule } from './admin-routing.module';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { UsersComponent } from './users/users.component';
import { WorkInProgressComponent } from './work-in-progress/work-in-progress.component';
import { FindawordComponent } from './findaword/findaword.component';
import { QuizComponent } from './quiz/quiz.component';

import { LevelComponent } from './level/level.component';
import { OrganizesentenceComponent } from './organizesentence/organizesentence.component';
import { GuesspictureComponent } from './guesspicture/guesspicture.component';

/**
 * Modulo dell'admin, qui vengono dichiarate le component che utilizza 
 * l'admin. Questo modulo importa AdminRoutingModule.
 * 
 * @author Vittorio Valent
 * 
 * @see AdminRoutingModule
 */
@NgModule({
  declarations: [AdminDashboardComponent, UsersComponent, WorkInProgressComponent, GuesspictureComponent, OrganizesentenceComponent, LevelComponent, QuizComponent,FindawordComponent],
  imports: [
    CommonModule,
    AdminRoutingModule,
    FormsModule
  ]
})
export class AdminModule { }
