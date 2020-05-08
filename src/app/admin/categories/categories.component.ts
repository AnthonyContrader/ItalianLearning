import { Component, OnInit, ViewChild } from '@angular/core';
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

  categoriesDTO: CategoryDTO[];
  categorytoinsert: CategoryDTO = new CategoryDTO();
  @ViewChild('newCategoryForm') categoryForm;
  @ViewChild('modalTitle') modalTitle;
  @ViewChild('closeModal') closeModal;

  constructor(private service: CategoryService) { }

  ngOnInit() {
    this.getCategories();
  }

  getCategories() {
    this.service.getAll().subscribe(categories => this.categoriesDTO = categories);
  }

  delete(category: CategoryDTO) {
    this.service.delete(category.id).subscribe(() => this.getCategories());
  }

  update(category: CategoryDTO) {
    this.service.update(category).subscribe(() => this.getCategories());
  }

  insert(category: CategoryDTO) {
    this.service.insert(category).subscribe(() => this.getCategories());
    this.clear();
  }

  clear(){
    this.categorytoinsert = new CategoryDTO();
  }
  submitted = false;

  editCategory(category: CategoryDTO){
    this.categoryForm.reset()
    if(category != null){
      this.service.read(category.id).subscribe(category => this.categorytoinsert = category);
      this.modalTitle.nativeElement.textContent = 'Edit Category ' + category.id
    }
    else
      this.modalTitle.nativeElement.textContent = 'New Category'
  }

  onSubmit(category: CategoryDTO) {
    if (category.id != null)
      this.update(category)
    else
      this.insert(category)
      
      this.closeModal.nativeElement.click()
  }


}
