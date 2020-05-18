import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GroupStatsPage } from './group-stats.page';

describe('GroupStatsPage', () => {
  let component: GroupStatsPage;
  let fixture: ComponentFixture<GroupStatsPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GroupStatsPage ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GroupStatsPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
