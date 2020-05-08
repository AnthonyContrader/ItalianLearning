import { Component, OnInit, ViewChild } from '@angular/core';
import { OrganizeSentenceDTO } from 'src/dto/organizesentencedto';
import { OrganizeSentenceService } from 'src/service/organizesentence.service';
import { LevelDTO } from 'src/dto/leveldto';
import { CategoryDTO } from 'src/dto/categorydto';
import { LevelService } from 'src/service/level.service';
import { CategoryService } from 'src/service/category.service';
import { FormControl, FormGroup, FormBuilder, Validators } from '@angular/forms';

/*
* created by Torquato Di Maio
*/
@Component({
  selector: 'app-organizesentences',
  templateUrl: './organizesentences.component.html',
  styleUrls: ['./organizesentences.component.css']
})
//un oggetto di OrganizesentencesComponent e fatto dalle variabili(campi) definiti prima del costruttore + quelli definiti come parametri nel costruttore
export class OrganizesentencesComponent implements OnInit {

  //Oggetti hanno le variabili definiti qui e quelle definite ne costruttore
  organizesentencesDTO: OrganizeSentenceDTO[]; //array di OrganizeSentenceDTO
  organizesentencetoinsert: OrganizeSentenceDTO = new OrganizeSentenceDTO(); //definisco un singolo oggetto vuoto di tipo OrganizeSenteceDTO

  levelsDTO: LevelDTO[];
  levelDTO: LevelDTO;
  categoriesDTO: CategoryDTO[];
  categoryDTO: CategoryDTO;

  @ViewChild('newOrganizeSentenceForm') organizeSentenceForm;
  @ViewChild('modalTitle') modalTitle;
  @ViewChild('closeModal') closeModal;

  //creiamo le variabili service, serviceCategory e serviceLevel dentro al costruttore per poterle utilizzare con il nostro oggetto   
  constructor(private service: OrganizeSentenceService, private serviceCategory: CategoryService, private serviceLevel: LevelService) { }
 //eseguito una volta che viene inizializzata la pagina e ogni volta che viene caricata questa pagina
  ngOnInit() {

    this.getOrganizeSentences();//richiamo il metodo getOrganizeSentences su questo oggetto
    this.getCategories();
    this.getLevels()
  }

  getOrganizeSentences(){
    //service e' definito nel costruttore. this.service.getAll mi restituisce un tipo Observable di array di OrganizeSentenceDTO 
    //Poiche getAll mi restituisce dati di tipo observable uso  subscribe per vederli tutti 
    //con subscribe passiamo il parametro organizesentences che conterra' i dati dell'observable i quali
    //vengono salvati nella variabile organizesentencesDTO
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

  editOrganizeSentence(organizesentence: OrganizeSentenceDTO){
    this.organizeSentenceForm.reset()
    if(organizesentence != null){
      this.service.read(organizesentence.id).subscribe(organizesentence => this.organizesentencetoinsert = organizesentence);
      this.modalTitle.nativeElement.textContent = 'Edit OrganizeSentence ' + organizesentence.id
    }
    else
      this.modalTitle.nativeElement.textContent = 'New OrganizeSentence'
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
    //svuoto i campi
    this.clear();
  }

  clear(){
    //pulisce i campi
    this.organizesentencetoinsert = new OrganizeSentenceDTO();
  }

  submitted = false;
  
  onSubmit(organizesentence: OrganizeSentenceDTO) {
    if (organizesentence.id != null)
      this.update(organizesentence)
    else
      this.insert(organizesentence)

    this.closeModal.nativeElement.click()
  }

}
