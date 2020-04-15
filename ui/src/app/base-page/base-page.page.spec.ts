import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { BasePagePage } from './base-page.page';

describe('BasePagePage', () => {
  let component: BasePagePage;
  let fixture: ComponentFixture<BasePagePage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BasePagePage ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(BasePagePage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
