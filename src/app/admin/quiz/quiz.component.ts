import { Component, OnInit, ViewChild } from '@angular/core';
import { QuizDTO } from 'src/dto/quizdto';
import { QuizService } from 'src/service/quiz.service';
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
  quizzesDTO: QuizDTO[];
  levelsDTO: LevelDTO[];
  categoriesDTO: CategoryDTO[];
  quiztoinsert: QuizDTO = new QuizDTO();
  @ViewChild('newQuizForm') quizForm;
  @ViewChild('modalTitle') modalTitle;
  @ViewChild('closeModal') closeModal;

  constructor(private service: QuizService, private serviceCategory: CategoryService, private serviceLevel: LevelService) { }

  ngOnInit() {
    this.getQuizzes();
    this.getCategories();
    this.getLevels();
  }
  getQuizzes() {
    this.service.getAll().subscribe(quiz => this.quizzesDTO = quiz);
    //l'oggetto observable tramite subscribe ci passa i dati con parametro da lista game che verrà salvato nella varibile dto
  }
  getLevels() {
    this.serviceLevel.getAll().subscribe(level => this.levelsDTO = level);
  }
  getCategories() {
    this.serviceCategory.getAll().subscribe(category => this.categoriesDTO = category);
  }
  delete(quiz: QuizDTO) {
    this.service.delete(quiz.id).subscribe(() => this.getQuizzes());
    //cancella i dati e restituisce la lista aggiornata
  }
  editQuiz(quiz: QuizDTO) {
    this.quizForm.reset()
    if (quiz != null) {
      this.service.read(quiz.id).subscribe(quiz => this.quiztoinsert = quiz);
      this.modalTitle.nativeElement.textContent = 'Edit Quiz ' + quiz.id
    }
    else
      this.modalTitle.nativeElement.textContent = 'New Quiz'
  }
  update(quiz: QuizDTO) {
    this.service.update(quiz).subscribe(() => this.getQuizzes());

    //aggiorna i dati e restituisce la lista aggiornata
  }
  insert(quiz: QuizDTO){
    this.service.insert(quiz).subscribe(() => this.getQuizzes());
  }
  
  clear(){
    this.quiztoinsert = new QuizDTO();
  }
  onSubmit(quiz: QuizDTO) {
    if (quiz.id != null)
      this.update(quiz)
    else
      this.insert(quiz)
  
    this.closeModal.nativeElement.click()
  }

}
