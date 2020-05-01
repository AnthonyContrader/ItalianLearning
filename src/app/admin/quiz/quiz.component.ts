import { Component, OnInit } from '@angular/core';
import { QuizDTO } from 'src/dto/quizdto';
import {QuizService} from 'src/service/quiz.service';
import { LevelDTO } from 'src/dto/leveldto';
import { CategoryDTO } from 'src/dto/categorydto';
import { CategoryService } from 'src/service/category.service';
import { LevelService } from 'src/service/level.service';

/* created by Anna Cecere*/

@Component({
  selector: 'app-quiz',
  templateUrl: './quiz.component.html',
  styleUrls: ['./quiz.component.css']
})
export class QuizComponent implements OnInit {
  quizDTO: QuizDTO[];
  levelsDTO: LevelDTO[];
  categoriesDTO: CategoryDTO[];
  quiztoinsert : QuizDTO = new QuizDTO();

  constructor(private service: QuizService, private serviceCategory: CategoryService, private serviceLevel:LevelService) { }

  ngOnInit() {
    this.getQuiz();
    this.getCategories();
    this.getLevels();
    }
  getQuiz() {
    this.service.getAll().subscribe(quiz => this.quizDTO = quiz); 
    //l'oggetto observable tramite subscribe ci passa i dati con parametro da lista game che verrà salvato nella varibile dto
    }
  getLevels() {
    this.serviceLevel.getAll().subscribe(level => this.levelsDTO = level);
  }
  getCategories() {
    this.serviceCategory.getAll().subscribe(category => this.categoriesDTO = category);
  }
  delete(quiz: QuizDTO) {
    this.service.delete(quiz.id).subscribe(() => this.getQuiz());
    //cancella i dati e restituisce la lista aggiornata
  }
  update(quiz: QuizDTO) {
    this.service.update(quiz).subscribe(() => this.getQuiz());
    
    //aggiorna i dati e restituisce la lista aggiornata
  }
  insert(quiz: QuizDTO){
    console.log(quiz);
    this.service.insert(quiz).subscribe(() => this.getQuiz());
    this.clear();
  }
  clear(){
    this.quiztoinsert = new QuizDTO();
      }
}
