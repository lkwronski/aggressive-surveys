import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PollStatsPage } from './poll-stats.page';

describe('PollStatsPage', () => {
  let component: PollStatsPage;
  let fixture: ComponentFixture<PollStatsPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PollStatsPage ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PollStatsPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
