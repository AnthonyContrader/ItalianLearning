import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FindawordsComponent } from './findawords.component';

describe('FindawordsComponent', () => {
  let component: FindawordsComponent;
  let fixture: ComponentFixture<FindawordsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FindawordsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FindawordsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
