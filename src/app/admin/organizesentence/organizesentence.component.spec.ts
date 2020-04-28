import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OrganizesentenceComponent } from './organizesentence.component';

describe('OrganizesentenceComponent', () => {
  let component: OrganizesentenceComponent;
  let fixture: ComponentFixture<OrganizesentenceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OrganizesentenceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OrganizesentenceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
