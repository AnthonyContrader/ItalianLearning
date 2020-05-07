import { Component, OnInit, ViewChild } from '@angular/core';
import { GuessPictureDTO } from 'src/dto/guesspicturedto';
import { LevelDTO } from 'src/dto/leveldto';
import { CategoryDTO } from 'src/dto/categorydto';
import { GuessPictureService } from 'src/service/guesspicture.service';
import { CategoryService } from 'src/service/category.service';
import { LevelService } from 'src/service/level.service';

/*
 * @author Enzo Tasca
 */

@Component({
  selector: 'app-guesspictures',
  templateUrl: './guesspictures.component.html'
})
export class GuesspicturesComponent implements OnInit {

  guesspicturesDTO: GuessPictureDTO[];
  guesspicturetoinsert: GuessPictureDTO = new GuessPictureDTO();

  levelsDTO: LevelDTO[];
  categoriesDTO: CategoryDTO[];

  b64: string;
  b64toinsert: string;
  b64toupdate: string;
  idImageChange: number;
  newImage: boolean = false;
  @ViewChild('newGuessPictureForm') guessPictureForm;
  @ViewChild('modalTitle') modalTitle;

  constructor(private service: GuessPictureService, private serviceCategory: CategoryService, private serviceLevel:LevelService) { }

  ngOnInit() {
    this.getGuessPictures();
    this.getCategories();
    this.getLevels();
  }

  getGuessPictures() {
    this.service.getAll().subscribe(guesspicture => this.guesspicturesDTO = guesspicture);
  }

  getLevels() {
    this.serviceLevel.getAll().subscribe(level => this.levelsDTO = level);
  }

  getCategories() {
    this.serviceCategory.getAll().subscribe(category => this.categoriesDTO = category);
  }

  delete(guesspicture: GuessPictureDTO) {
    this.service.delete(guesspicture.id).subscribe(() => this.getGuessPictures());
  }

  update(guesspicture: GuessPictureDTO) {
    if (this.b64toupdate != '' && this.idImageChange == guesspicture.id){
      guesspicture.image = this.b64toupdate;
    }
    this.service.update(guesspicture).subscribe(() => this.getGuessPictures());
    this.b64toupdate = "";

  }

  insert(guesspicture: GuessPictureDTO) {
    guesspicture.image = this.b64toinsert;
    console.log(this.b64toinsert);
    this.service.insert(guesspicture).subscribe(() => this.getGuessPictures());
    this.clear();
    this.b64toinsert = "";
  }

  clear(){
    this.guesspicturetoinsert = new GuessPictureDTO();
  }

  imageModal(image: string){
    this.b64=image;
  }

  handleInputChange(e,newImage: boolean) {
    var file = e.dataTransfer ? e.dataTransfer.files[0] : e.target.files[0];
    var pattern = /image-*/;
    var reader = new FileReader();
    reader.onload = function(e){
      let reader = e.target;
      if (newImage === true)
        this.b64toinsert = reader.result ;
      else
        this.b64toupdate = reader.result;
    }.bind(this);
    reader.readAsDataURL(file);
  }

  editGuessPicture(guessPicture: GuessPictureDTO){
    this.guessPictureForm.reset()
    if(guessPicture != null){
      this.service.read(guessPicture.id).subscribe(hangman => this.guesspicturetoinsert = hangman);
      this.modalTitle.nativeElement.textContent = 'Edit Guess Picture ' + guessPicture.id
    }
    else
      this.modalTitle.nativeElement.textContent = 'New Guess Picture'
  }

}
