import { Component, OnInit } from '@angular/core';
import { CategoryDTO } from 'src/dto/categorydto';
import { CategoryService } from 'src/service/category.service';

/*
 * @author Enzo Tasca
 */

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css']
})
export class CategoriesComponent implements OnInit {

  categories: CategoryDTO[];
  categorytoinsert: CategoryDTO = new CategoryDTO();

  constructor(private service: CategoryService) { }

  ngOnInit() {
    this.getCategories();
  }

  getCategories() {
    this.service.getAll().subscribe(categories => this.categories = categories);
  }

  delete(category: CategoryDTO) {
    this.service.delete(category.id).subscribe(() => this.getCategories());
  }

  update(category: CategoryDTO) {
    this.service.update(category).subscribe(() => this.getCategories());
  }

  insert(category: CategoryDTO) {
    this.service.insert(category).subscribe(() => this.getCategories());
  }

  clear(){
    this.categorytoinsert = new CategoryDTO();
  }

}
