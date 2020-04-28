import { Component, OnInit } from '@angular/core';
import { FindMistakeDTO } from 'src/dto/findmistakedto';
import { FindMistakeService } from 'src/service/findmistake.service';
import { CategoriesComponent } from '../categories/categories.component';
import { LevelsComponent } from '../levels/levels.component';

@Component({
  selector: 'app-findmistakes',
  templateUrl: './findmistakes.component.html',
  styleUrls: ['./findmistakes.component.css']
})
export class FindmistakesComponent implements OnInit {

  findmistakesDTO: FindMistakeDTO[];
  findmistaketoinsert: FindMistakeDTO = new FindMistakeDTO();

  constructor(private service: FindMistakeService, private category: CategoriesComponent, private level: LevelsComponent) { }

  ngOnInit() {
    this.getFindMistakes();
  }

  getFindMistakes() {
    this.service.getAll().subscribe(findmistakes => this.findmistakesDTO = findmistakes);
    this.level.getLevels();
    this.category.getCategories();
  }

  delete(findmistake: FindMistakeDTO){
    this.service.delete(findmistake.id).subscribe(() => this.getFindMistakes());
  }

  update(findmistake: FindMistakeDTO){
    this.service.update(findmistake).subscribe(() => this.getFindMistakes());
  }

  insert(findmistake: FindMistakeDTO){
    this.service.insert(findmistake).subscribe(() => this.getFindMistakes());
  }

  clear(){
    this.findmistaketoinsert = new FindMistakeDTO();
  }

}
