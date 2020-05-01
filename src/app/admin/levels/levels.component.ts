//Created By Alessandro Alfieri
import { Component, OnInit } from '@angular/core';
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

}
