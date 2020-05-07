//Created By Alessandro Alfieri
import { Component, OnInit } from '@angular/core';
import { HangmanDTO } from 'src/dto/hangmandto';
import { HangmanService } from 'src/service/hangman.service';
import { CategoryService } from 'src/service/category.service';
import { LevelService } from 'src/service/level.service';
import { LevelDTO } from 'src/dto/leveldto';
import { CategoryDTO } from 'src/dto/categorydto';
import { FormControl, FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-hangmen',
  templateUrl: './hangmen.component.html',
  styleUrls: ['./hangmen.component.css']
})
export class HangmenComponent implements OnInit {

  hangmenDTO: HangmanDTO[];
  levelsDTO: LevelDTO[];
  levelDTO: LevelDTO;
  categoriesDTO: CategoryDTO[];
  categoryDTO: CategoryDTO;
  hangmantoinsert: HangmanDTO = new HangmanDTO();
  newHangman: FormGroup;
  editHangman: FormGroup;
  constructor(private service: HangmanService, private categoryService: CategoryService, private levelService: LevelService, private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.getHangmen();
    this.newHangman = new FormGroup({
      solution: new FormControl('', Validators.required),
      definition: new FormControl(''),
      sentence: new FormControl('', Validators.required),
      categoryId: new FormControl('', Validators.required),
      levelId: new FormControl('', Validators.required)
    });

    this.editHangman = new FormGroup({
      solution: new FormControl('', Validators.required),
      definition: new FormControl(''),
      sentence: new FormControl('', Validators.required),
      categoryId: new FormControl('', Validators.required),
      levelId: new FormControl('', Validators.required)
    });
  }

  getHangmen() {
    this.service.getAll().subscribe(hangmen => this.hangmenDTO = hangmen);
    this.categoryService.getAll().subscribe(categories => this.categoriesDTO = categories);
    this.levelService.getAll().subscribe(levels => this.levelsDTO = levels);
  }

  delete(hangman: HangmanDTO){
    this.service.delete(hangman.id).subscribe(() => this.getHangmen());
  }

  read(hangman: HangmanDTO){
    this.service.read(hangman.id).subscribe(() => this.getHangmen());
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

  newSubmit(){
    console.log(this.newHangman.value);
  }

  editSubmit(){
    console.log('edit');
  }

}
