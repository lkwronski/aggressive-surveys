import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AnswerPollPage } from './answer-poll.page';

describe('AnswerPollPage', () => {
  let component: AnswerPollPage;
  let fixture: ComponentFixture<AnswerPollPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AnswerPollPage ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AnswerPollPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
