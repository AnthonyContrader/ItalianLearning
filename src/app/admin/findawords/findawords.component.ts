import { Component, OnInit } from '@angular/core';
import { FindAWordDTO } from 'src/dto/findaworddto';
import { FindAWordService } from 'src/service/findaword.service';
import { CategoryDTO } from 'src/dto/categorydto';
import { CategoryService } from 'src/service/category.service';
import { LevelService } from 'src/service/level.service';
import { LevelDTO } from 'src/dto/leveldto';

//created by Gabriella Brunetto

@Component({
  selector: 'app-findawords',
  templateUrl: './findawords.component.html',
  styleUrls: ['./findawords.component.css']
})
export class FindawordsComponent implements OnInit {

  findawordsDTO: FindAWordDTO[]; // variabile ke contiene arrey di findaworddto
  findawordtoinsert: FindAWordDTO = new FindAWordDTO(); //prendiamo un singolo oggetto vuoto

  //per prendere tutti i dati del level e category
  levelsDTO: LevelDTO[]; 
  categoriesDTO: CategoryDTO[];

  //creiamo variabile service dentro il costruttore x poterla utilizzare con il nostro oggetto
  constructor(private service: FindAWordService, private serviceCategory: CategoryService, private serviceLevel:LevelService) { }

  ngOnInit() {
    //appena parte la pagina farà questo
    this.getFindAWord();
    this.getCategories();
    this.getLevels();
  }

  getFindAWord() {
    //this si riferisce all'oggetto component ke stiamo andando a creare
    this.service.getAll().subscribe(findaword => this.findawordsDTO=findaword);
    /* cio ke restituisce getall è un osserveble di arrey di findaworddto,
    col metodo subscribe passiamo il parametro di nome findaword ke conterra i dati del osservable  
    vengono salvati nella variabili findsaworddtodto*/ 
  }

  getLevels() {
    this.serviceLevel.getAll().subscribe(level => this.levelsDTO = level);
  }

  getCategories() {
    this.serviceCategory.getAll().subscribe(category => this.categoriesDTO = category);
  }

  delete(findaword: FindAWordDTO){
    this.service.delete(findaword.id).subscribe(() => this.getFindAWord());
  /*cancella i dati che passiomo atraverso l'id,
  scrivendo getfindaword vogliamo ke torni la lista aggiornata scritta nel metodo sopra 
  di nome appunto getfindaword*/ 
  }

  update(findaword: FindAWordDTO){
    this.service.update(findaword).subscribe(()=> this.getFindAWord());
  }

  insert(findaword: FindAWordDTO){
    this.service.insert(findaword).subscribe(()=> this.getFindAWord());
    this.clear(); //cancella automaticamente il contenuto della cella una volta fatto l'insert
  }

  clear(){
  //funzione per il tasto clear che pulisce i campi della caselle
    this.findawordtoinsert = new FindAWordDTO();
  }


}
