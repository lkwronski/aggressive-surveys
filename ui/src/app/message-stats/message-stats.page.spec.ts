import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MessageStatsPage } from './message-stats.page';

describe('MessageStatsPage', () => {
  let component: MessageStatsPage;
  let fixture: ComponentFixture<MessageStatsPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MessageStatsPage ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MessageStatsPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
