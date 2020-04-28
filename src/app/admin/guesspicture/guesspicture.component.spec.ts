import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GuesspictureComponent } from './guesspicture.component';

describe('GuesspictureComponent', () => {
  let component: GuesspictureComponent;
  let fixture: ComponentFixture<GuesspictureComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GuesspictureComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GuesspictureComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
