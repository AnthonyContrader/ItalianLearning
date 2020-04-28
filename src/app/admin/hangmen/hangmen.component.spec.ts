import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HangmenComponent } from './hangmen.component';

describe('HangmenComponent', () => {
  let component: HangmenComponent;
  let fixture: ComponentFixture<HangmenComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HangmenComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HangmenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
