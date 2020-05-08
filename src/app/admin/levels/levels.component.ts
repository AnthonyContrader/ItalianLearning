//Created By Alessandro Alfieri
import { Component, OnInit, ViewChild } from '@angular/core';
import { LevelDTO } from 'src/dto/leveldto';
import { LevelService } from 'src/service/level.service';

@Component({
  selector: 'app-levels',
  templateUrl: './levels.component.html',
  styleUrls: ['./levels.component.css']
})
export class LevelsComponent implements OnInit {

  levelsDTO: LevelDTO[];
  leveltoinsert: LevelDTO = new LevelDTO();
  @ViewChild('newLevelForm') levelForm;
  @ViewChild('modalTitle') modalTitle;
  @ViewChild('closeModal') closeModal;
  
  constructor(private service: LevelService) { }

  ngOnInit() {
    this.getLevels();
  }

  getLevels() {
    this.service.getAll().subscribe(levels => this.levelsDTO = levels);
  }

  delete(level: LevelDTO){
    this.service.delete(level.id).subscribe(() => this.getLevels());
  }

  update(level: LevelDTO){
    this.service.update(level).subscribe(() => this.getLevels());
  }

  insert(level: LevelDTO){
    this.service.insert(level).subscribe(() => this.getLevels());
  }

  clear(){
    this.leveltoinsert = new LevelDTO;
  }
  submitted = false;

  editLevel(level: LevelDTO){
    this.levelForm.reset()
    if(level != null){
      this.service.read(level.id).subscribe(level => this.leveltoinsert = level);
      this.modalTitle.nativeElement.textContent = 'Edit Level ' + level.id
    }
    else
      this.modalTitle.nativeElement.textContent = 'New Level'
  }

  onSubmit(level: LevelDTO) {
    if (level.id != null)
      this.update(level)
    else
      this.insert(level)
      
      this.closeModal.nativeElement.click()
  }

  validation(level: LevelDTO){
    if (level.score > 0 && this.levelForm.form.valid){
      return true;
    }
    return false;
  }


}
