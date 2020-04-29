import { Component, OnInit } from '@angular/core';
import { HangmanDTO } from 'src/dto/hangmandto';
import { HangmanService } from 'src/service/hangman.service';
import { CategoriesComponent } from 'src/app/admin/categories/categories.component'
import { LevelsComponent } from 'src/app/admin/levels/levels.component'

@Component({
  selector: 'app-hangmen',
  templateUrl: './hangmen.component.html',
  styleUrls: ['./hangmen.component.css']
})
export class HangmenComponent implements OnInit {

  hangmenDTO: HangmanDTO[];
  hangmantoinsert: HangmanDTO = new HangmanDTO();

  constructor(private service: HangmanService) { }

  ngOnInit() {
    this.getHangmen();
  }

  getHangmen() {
    this.service.getAll().subscribe(hangmen => this.hangmenDTO = hangmen);
    // this.level.getLevels();
    // this.category.getCategories();
  }

  delete(hangman: HangmanDTO){
    this.service.delete(hangman.id).subscribe(() => this.getHangmen());
  }

  update(hangman: HangmanDTO){
    this.service.update(hangman).subscribe(() => this.getHangmen());
  }

  insert(hangman: HangmanDTO){
    this.service.insert(hangman).subscribe( () => this.getHangmen());
  }

  clear(){
    this.hangmantoinsert = new HangmanDTO();
  }

}
