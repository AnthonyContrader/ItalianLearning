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
  b64toinsert: string = null;
  @ViewChild('newGuessPictureForm') guessPictureForm;
  @ViewChild('modalTitle') modalTitle;
  @ViewChild('closeModal') closeModal;

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
    this.service.update(guesspicture).subscribe(() => this.getGuessPictures());
  }

  insert(guesspicture: GuessPictureDTO) {
    console.log(guesspicture);

    this.service.insert(guesspicture).subscribe(() => this.getGuessPictures());
    this.clear();
  }

  clear(){
    this.guesspicturetoinsert = new GuessPictureDTO();
  }

  imageModal(image: string){
    this.b64= "data:image/jpeg;base64," +image;
  }

  handleInputImage(e) {
    var file = e.dataTransfer ? e.dataTransfer.files[0] : e.target.files[0];
    var reader = new FileReader();
    reader.onload = function(e){
      let reader = e.target;
      this.b64toinsert = reader.result ;
      this.b64toinsert = this.b64toinsert.split(",",2)
      console.log(this.b64toinsert)
    }.bind(this);
    reader.readAsDataURL(file);
  }

  editGuessPicture(guessPicture: GuessPictureDTO){
    this.guessPictureForm.reset()
    if(guessPicture != null){
      this.service.read(guessPicture.id).subscribe(guessPicture => this.guesspicturetoinsert = guessPicture);
      this.modalTitle.nativeElement.textContent = 'Edit Guess Picture ' + guessPicture.id
    }
    else
      this.modalTitle.nativeElement.textContent = 'New Guess Picture'
  }

  onSubmit(g: GuessPictureDTO) {
    if(this.b64toinsert != null){
      g.image = this.b64toinsert[1];
      g.imageContentType = this.b64toinsert[0];
    }

    if (g.id != null)
      this.update(g)
    else
      this.insert(g)

    this.closeModal.nativeElement.click()
  }

  validation(g: GuessPictureDTO){

    if(g.id == null && this.guessPictureForm.form.valid && this.b64toinsert != null)
      return true;

    if(this.guessPictureForm.form.valid && g.id != null)
      return true;
    
    return false;
  }

}
