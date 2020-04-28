import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OrganizesentencesComponent } from './organizesentences.component';

describe('OrganizesentencesComponent', () => {
  let component: OrganizesentencesComponent;
  let fixture: ComponentFixture<OrganizesentencesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OrganizesentencesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OrganizesentencesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
