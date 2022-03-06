import { Component, OnInit } from '@angular/core';
import { BackendService } from './services/backend.service';
import { Class, Teacher, Unit } from './models/models';
import { WebsocketService } from './services/websocket.service';

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

  constructor(private readonly backend: BackendService,
              private readonly webSocket: WebsocketService) {
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

    this.webSocket.connect().subscribe(value => {
      const unit = JSON.parse(JSON.parse(value)) as Unit;
      const index = this.units?.findIndex(u => u.day === unit.day && u.unit === unit.unit);

      if (unit.hasChanged === this.webSocket.sessionId) {
        unit.hasChanged = undefined;
      } else {
        unit.hasChanged = 'other';
      }

      if (this.units && unit.schoolclass?.id === this.selectedClass?.id) {
        if (index && index !== -1) {
          this.units[index] = unit;
        } else {
          this.units = [ ...this.units, unit ];
        }
      }
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
          unit.hasChanged = this.webSocket.sessionId!;
          this.webSocket.sendMessage(JSON.stringify(unit));
        });
      });
    }
  }
}
