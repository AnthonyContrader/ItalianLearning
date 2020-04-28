import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GuesspicturesComponent } from './guesspictures.component';

describe('GuesspicturesComponent', () => {
  let component: GuesspicturesComponent;
  let fixture: ComponentFixture<GuesspicturesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GuesspicturesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GuesspicturesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
