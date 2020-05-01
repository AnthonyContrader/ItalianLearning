//Created By Alessandro Alfieri
import { Component, OnInit } from '@angular/core';
import { HangmanDTO } from 'src/dto/hangmandto';
import { HangmanService } from 'src/service/hangman.service';
import { CategoryService } from 'src/service/category.service';
import { LevelService } from 'src/service/level.service';
import { LevelDTO } from 'src/dto/leveldto';
import { CategoryDTO } from 'src/dto/categorydto';

@Component({
  selector: 'app-hangmen',
  templateUrl: './hangmen.component.html',
  styleUrls: ['./hangmen.component.css']
})
export class HangmenComponent implements OnInit {

  hangmenDTO: HangmanDTO[];
  levelsDTO: LevelDTO[];
  categoriesDTO: CategoryDTO[];
  hangmantoinsert: HangmanDTO = new HangmanDTO();

  constructor(private service: HangmanService, private categoryService: CategoryService, private levelService: LevelService) { }

  ngOnInit() {
    this.getHangmen();
  }

  getHangmen() {
    this.service.getAll().subscribe(hangmen => this.hangmenDTO = hangmen);
    this.categoryService.getAll().subscribe(categories => this.categoriesDTO = categories);
    this.levelService.getAll().subscribe(levels => this.levelsDTO = levels);
  }

  delete(hangman: HangmanDTO){
    this.service.delete(hangman.id).subscribe(() => this.getHangmen());
  }

  update(hangman: HangmanDTO){
    this.service.update(hangman).subscribe(() => this.getHangmen());
  }

  insert(hangman: HangmanDTO){
    this.service.insert(hangman).subscribe(() => this.getHangmen());
  }

  clear(){
    this.hangmantoinsert = new HangmanDTO();
  }

}
