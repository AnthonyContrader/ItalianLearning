import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FindmistakesComponent } from './findmistakes.component';

describe('FindmistakesComponent', () => {
  let component: FindmistakesComponent;
  let fixture: ComponentFixture<FindmistakesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FindmistakesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FindmistakesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
