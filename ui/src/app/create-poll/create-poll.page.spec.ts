import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatePollPage } from './create-poll.page';

describe('CreatePollPage', () => {
  let component: CreatePollPage;
  let fixture: ComponentFixture<CreatePollPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreatePollPage ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreatePollPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
