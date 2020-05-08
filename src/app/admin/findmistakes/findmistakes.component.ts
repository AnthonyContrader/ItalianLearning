//Created By Alessandro Alfieri
import { Component, OnInit, ViewChild } from '@angular/core';
import { FindMistakeDTO } from 'src/dto/findmistakedto';
import { FindMistakeService } from 'src/service/findmistake.service';
import { CategoriesComponent } from '../categories/categories.component';
import { LevelsComponent } from '../levels/levels.component';
import { CategoryDTO } from 'src/dto/categorydto';
import { LevelDTO } from 'src/dto/leveldto';
import { CategoryService } from 'src/service/category.service';
import { LevelService } from 'src/service/level.service';

@Component({
  selector: 'app-findmistakes',
  templateUrl: './findmistakes.component.html',
  styleUrls: ['./findmistakes.component.css']
})
export class FindmistakesComponent implements OnInit {

  findmistakesDTO: FindMistakeDTO[];
  findmistaketoinsert: FindMistakeDTO = new FindMistakeDTO();
  levelsDTO: LevelDTO[];
  levelDTO: LevelDTO;
  categoriesDTO: CategoryDTO[];
  categoryDTO: CategoryDTO;
  @ViewChild('newFindMistakeForm') findMistakeForm;
  @ViewChild('modalTitle') modalTitle;
  @ViewChild('closeModal') closeModal;

  constructor(private service: FindMistakeService, private categoryService: CategoryService, private levelService: LevelService) { }

  ngOnInit() {
    this.getFindMistakes();
  }

  getFindMistakes() {
    this.service.getAll().subscribe(findmistakes => this.findmistakesDTO = findmistakes);
    this.categoryService.getAll().subscribe(categories => this.categoriesDTO = categories);
    this.levelService.getAll().subscribe(levels => this.levelsDTO = levels);
  }

  delete(findmistake: FindMistakeDTO){
    this.service.delete(findmistake.id).subscribe(() => this.getFindMistakes());
  }
  editFindMistake(findmistake: FindMistakeDTO){
    this.findMistakeForm.reset()
    if(findmistake != null){
      this.service.read(findmistake.id).subscribe(findMistake => this.findmistaketoinsert = findmistake);
      this.modalTitle.nativeElement.textContent = 'Edit FindMistake ' + findmistake.id
    }
    else
      this.modalTitle.nativeElement.textContent = 'New FindMistake'
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
  onSubmit(findMistake: FindMistakeDTO) {
    if (findMistake.id != null)
      this.update(findMistake)
    else
      this.insert(findMistake)
    
    this.closeModal.nativeElement.click()
  }

}
