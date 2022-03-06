import { Component, OnInit } from '@angular/core';
import { BackendService } from './services/backend.service';
import { Class, Teacher, Unit } from './models/models';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: [ './app.component.scss' ]
})
export class AppComponent implements OnInit {

  public classes: Class[] | null;
  public selectedClass: Class | null;
  public units: Unit[] | null;
  public teachers: Teacher[] | null;
  private unsavedUnits: Unit[] | null;

  constructor(private readonly backend: BackendService) {
    this.classes = null;
    this.selectedClass = null;
    this.units = null;
    this.teachers = null;
    this.unsavedUnits = null;
  }

  public ngOnInit() {
    this.backend.get<Class[]>('class').then(classes => {
      this.classes = classes;
      this.selectedClass = classes[0];
      this.changeClass(this.selectedClass);
    });

    this.backend.get<Teacher[]>('teacher').then(teachers => this.teachers = teachers);
  }

  public changeClass(schoolClass: Class) {
    this.backend.get<Unit[]>(`unit/class/${ schoolClass.id }`).then(units => this.units = units);
  }

  handleUnitsChange(units: Unit[]) {
    this.unsavedUnits = units;
  }

  save(): void {
    if (this.unsavedUnits) {
      this.unsavedUnits.forEach(unit => {
        unit.schoolclass = this.selectedClass;

        this.backend.post<Unit>('unit', unit).then(() => {
          console.log('updated unit', unit);
        });
      });

      this.unsavedUnits = [];
    }
  }
}
