import { Injectable } from '@angular/core';
import { AbstractService } from './abstractservice';
import { CategoryDTO } from 'src/dto/categorydto';
import { HttpClient } from '@angular/common/http';

/*
 * @author Enzo Tasca
 */

@Injectable({
    providedIn: 'root'
  })
export class CategoryService extends AbstractService<CategoryDTO>{

  constructor(http: HttpClient) {
    super(http);
    this.type = 'categories';
    this.micro = 'games';
  }

}
  