import { Component, OnInit } from '@angular/core';
import { OrganizeSentenceDTO } from 'src/dto/organizesentencedto';
import { OrganizeSentenceService } from 'src/service/organizesentence.service';
import { LevelDTO } from 'src/dto/leveldto';
import { CategoryDTO } from 'src/dto/categorydto';
import { LevelService } from 'src/service/level.service';
import { CategoryService } from 'src/service/category.service';

/*
* created by Torquato Di Maio
*/
@Component({
  selector: 'app-organizesentences',
  templateUrl: './organizesentences.component.html',
  styleUrls: ['./organizesentences.component.css']
})
export class OrganizesentencesComponent implements OnInit {


  organizesentencesDTO: OrganizeSentenceDTO[]; //array di OrganizeSentenceDTO
  organizesentencetoinsert: OrganizeSentenceDTO = new OrganizeSentenceDTO(); //definisco un singolo oggetto vuoto di tipo OrganizeSenteceDTO

  levelsDTO: LevelDTO[];
  categoriesDTO: CategoryDTO[];

  //creiamo una variabile service dentro al costruttore per poterla utilizzare con il nostro oggetto
  constructor(private service: OrganizeSentenceService, private serviceCategory: CategoryService, private serviceLevel: LevelService) { }
 //eseguito una volta che viene inizializzata la pagina
  ngOnInit() {

    this.getOrganizeSentences();//richiamo il metodo getOrganizeSentences su questo oggetto
    this.getCategories();
    this.getLevels()
  }

  getOrganizeSentences(){
    //service definito nel costruttore. get all e' observable quindi uso subscribe
    //getAll observable con i dati e con i subscribe li vedo uno per uno i dati
    this.service.getAll().subscribe(organizesentences=> this.organizesentencesDTO= organizesentences);

    
  }
  //ottengo i livelli per il gioco
  getLevels() {
    this.serviceLevel.getAll().subscribe(level => this.levelsDTO = level);
  }
  //ottengo le categorie per il gioco
  getCategories() {
    this.serviceCategory.getAll().subscribe(category => this.categoriesDTO = category);
  }

  delete(organizesentence: OrganizeSentenceDTO){
    //noi andiamo a chiamare la delete con l'id 
    //da this.service.delete(organizesentence.id) otteniamo sempre un observable. 
    //in pratica fammi la delete, mi iscrivo(subscribe) e mi richiama this.getOrganizeSentences()
    //quindi:cancella i dati e  mi restituisce la lista aggiornata da visualizzare
    this.service.delete(organizesentence.id).subscribe(()=>this.getOrganizeSentences());
  }

  update(organizesentence: OrganizeSentenceDTO){
    //aggiorna i dati
    this.service.update(organizesentence).subscribe(() => this.getOrganizeSentences());
  }

  insert(organizesentence: OrganizeSentenceDTO) {
    //inserisce i dati
    this.service.insert(organizesentence).subscribe(() => this.getOrganizeSentences());
  }

  clear(){
    //pulisce i campi
    this.organizesentencetoinsert = new OrganizeSentenceDTO();
  }

}
