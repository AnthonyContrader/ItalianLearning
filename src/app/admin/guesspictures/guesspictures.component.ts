import { Component, OnInit } from '@angular/core';
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
  templateUrl: './guesspictures.component.html',
  styleUrls: ['./guesspictures.component.css']
})
export class GuesspicturesComponent implements OnInit {

  guesspicturesDTO: GuessPictureDTO[];
  guesspicturetoinsert: GuessPictureDTO = new GuessPictureDTO();

  levelsDTO: LevelDTO[];
  categoriesDTO: CategoryDTO[];

  b64: string;
  b64toinsert: string;
  b64toupdate: string;

  html: string;


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
    if (this.b64toupdate != ''){
      guesspicture.image = this.b64toupdate;
      console.log(this.b64toupdate);
    }
      
    //console.log(guesspicture);
    this.service.update(guesspicture).subscribe(() => this.getGuessPictures());
  }

  insert(guesspicture: GuessPictureDTO) {

    
    /*if(guesspicture.solution == null){
      this.html = '<div class="alert alert-warning alert-dismissible fade show" role="alert">'+
      '<strong>Holy guacamole!</strong> You should check in on some of those fields below.'+
      '<button type="button" class="close" data-dismiss="alert" aria-label="Close">'+
        '<span aria-hidden="true">&times;</span>'+
      '</button>'+
    '</div>';
    }*/

    guesspicture.image = this.b64toinsert;
    this.service.insert(guesspicture).subscribe(() => this.getGuessPictures());
    this.clear();
  }

  clear(){
    this.guesspicturetoinsert = new GuessPictureDTO();
  }

  imageModal(image: string){
    this.b64=image;
  }

  handleInputChange(e) {
    var file = e.dataTransfer ? e.dataTransfer.files[0] : e.target.files[0];
    var pattern = /image-*/;
    var reader = new FileReader();
    reader.onload = this._handleReaderLoaded.bind(this);
    reader.readAsDataURL(file);
  }
  _handleReaderLoaded(e) {
    let reader = e.target;
    this.b64toinsert = reader.result;
  }

  handleUpdateChange(e) {
    var file = e.dataTransfer ? e.dataTransfer.files[0] : e.target.files[0];
    var pattern = /image-*/;
    var reader = new FileReader();
    reader.onload = this._handleUpdateLoaded.bind(this);
    reader.readAsDataURL(file);
  }
  _handleUpdateLoaded(e) {
    let reader = e.target;
    this.b64toupdate = reader.result;
  }

}
