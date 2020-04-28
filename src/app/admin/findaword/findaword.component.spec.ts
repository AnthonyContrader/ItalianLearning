import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FindawordComponent } from './findaword.component';

describe('FindawordComponent', () => {
  let component: FindawordComponent;
  let fixture: ComponentFixture<FindawordComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FindawordComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FindawordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
