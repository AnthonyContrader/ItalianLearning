import { Component, OnInit } from '@angular/core';
import { GuessPictureDTO } from 'src/dto/guesspicturedto';
import { GuessPictureService } from 'src/service/guesspicture.service';

/*
 * @author Enzo Tasca
 */

@Component({
  selector: 'app-guesspictures',
  templateUrl: './guesspicturs.component.html',
  styleUrls: ['./guesspicturs.component.css']
})
export class GuesspicturesComponent implements OnInit {

  guesspictures: GuessPictureDTO[];
  guesspicturetoinsert: GuessPictureDTO = new GuessPictureDTO();

  constructor(private service: GuessPictureService) { }

  ngOnInit() {
    this.getGuessPictures();
  }

  getGuessPictures() {
    this.service.getAll().subscribe(guesspicture => this.guesspictures = guesspicture);
  }

  delete(guesspicture: GuessPictureDTO) {
    this.service.delete(guesspicture.id).subscribe(() => this.getGuessPictures());
  }

  update(guesspicture: GuessPictureDTO) {
    this.service.update(guesspicture).subscribe(() => this.getGuessPictures());
  }

  insert(guesspicture: GuessPictureDTO) {
    this.service.insert(guesspicture).subscribe(() => this.getGuessPictures());
  }

  clear(){
    this.guesspicturetoinsert = new GuessPictureDTO();
  }

}
