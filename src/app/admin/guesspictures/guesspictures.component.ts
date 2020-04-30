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
  }

  clear(){
    this.guesspicturetoinsert = new GuessPictureDTO();
  }

}
