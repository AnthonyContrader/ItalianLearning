import { Component, OnInit } from '@angular/core';
import { QuizDTO } from 'src/dto/quizdto';
import {QuizService} from 'src/service/quiz.service';

/* created by Anna Cecere*/

@Component({
  selector: 'app-quiz',
  templateUrl: './quiz.component.html',
  styleUrls: ['./quiz.component.css']
})
export class QuizComponent implements OnInit {
  quizDTO: QuizDTO[];
  quiztoinsert : QuizDTO = new QuizDTO();

  constructor(private service: QuizService) { }

  ngOnInit() {
    this.getQuiz();
   
    }
  getQuiz() {
    this.service.getAll().subscribe(quiz => this.quizDTO = quiz); 
    //l'oggetto observable tramite subscribe ci passa i dati con parametro da lista game che verrà salvato nella varibile dto
  }
  delete(quiz: QuizDTO) {
    this.service.delete(quiz.id).subscribe(() => this.getQuiz());
    //cancella i dati e restituisce la lista aggiornata
  }
  update(quiz: QuizDTO) {
    this.service.update(quiz).subscribe(() => this.getQuiz());
    //aggiorna i dati e restituisce la lista aggiornata
  }
  clear(){
    this.quiztoinsert = new QuizDTO();
      }
}
