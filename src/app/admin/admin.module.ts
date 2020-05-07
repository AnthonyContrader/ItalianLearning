import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AdminRoutingModule } from './admin-routing.module';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { UsersComponent } from './users/users.component';
import { WorkInProgressComponent } from './work-in-progress/work-in-progress.component';
import { QuizComponent } from './quiz/quiz.component';
import { PlaylistComponent } from './playlist/playlist.component';
import { FindawordsComponent } from './findawords/findawords.component';
import { FindmistakesComponent } from './findmistakes/findmistakes.component';
import { CategoriesComponent } from './categories/categories.component';
import { OrganizesentencesComponent } from './organizesentences/organizesentences.component';
import { HangmenComponent } from './hangmen/hangmen.component';
import { GuesspicturesComponent } from './guesspictures/guesspictures.component';
import { LevelsComponent } from './levels/levels.component';
import { ReactiveFormsModule } from '@angular/forms';

/**
 * Modulo dell'admin, qui vengono dichiarate le component che utilizza 
 * l'admin. Questo modulo importa AdminRoutingModule.
 * 
 * @author Vittorio Valent
 * 
 * @see AdminRoutingModule
 */
@NgModule({
  declarations: [AdminDashboardComponent, UsersComponent, WorkInProgressComponent, QuizComponent, PlaylistComponent, FindawordsComponent, FindmistakesComponent, CategoriesComponent, OrganizesentencesComponent, HangmenComponent, GuesspicturesComponent, LevelsComponent],
  imports: [
    CommonModule,
    AdminRoutingModule,
    ReactiveFormsModule,
    FormsModule
  ]
})
export class AdminModule { }
