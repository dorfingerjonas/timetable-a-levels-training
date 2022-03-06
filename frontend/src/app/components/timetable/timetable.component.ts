import { Component, EventEmitter, Input, OnChanges, Output } from '@angular/core';
import { Teacher, Unit } from '../../models/models';

@Component({
  selector: 'app-timetable',
  templateUrl: './timetable.component.html',
  styleUrls: [ './timetable.component.scss' ]
})
export class TimetableComponent implements OnChanges {

  @Input() public maxDays!: number;
  @Input() public maxUnitsPerDay!: number;
  @Input() public units: Unit[] | null;
  @Input() public teachers: Teacher[] | null;
  @Output() public unitsChange: EventEmitter<Unit[]>;

  public days: { label: string, units: Unit[] }[];

  constructor() {
    this.units = null;
    this.teachers = null;
    this.days = [];
    this.unitsChange = new EventEmitter<Unit[]>();
  }

  public ngOnChanges(): void {
    if (this.maxDays) {
      this.days = [];
      const dayLabels = [ 'Mo', 'Di', 'Mi', 'Do', 'Fr', 'Sa', 'So' ].slice(0, this.maxDays);

      if (this.maxUnitsPerDay) {
        dayLabels.forEach(label => {
          const units: Unit[] = [];

          for (let i = 0; i < this.maxUnitsPerDay; i++) {
            units.push({
              id: -1,
              day: dayLabels.indexOf(label) + 1,
              schoolclass: null,
              subject: '',
              unit: i,
              teacher: {
                id: -1,
                firstName: '',
                lastName: '',
                room: ''
              }
            });
          }

          const day = {
            label,
            units
          };

          this.days.push(day);
        });
      }
    }

    if (this.units) {
      this.units.forEach(unit => {
        this.days[unit.day - 1].units[unit.unit] = unit;
      });
    }
  }

  public selectionChange(unit: Unit): void {
    unit.teacher = this.teachers!.find(teacher => teacher.id === unit.teacher.id)!;
    unit.hasChanged = 'self';
    this.emitChanges();
  }

  public subjectChanged(unit: Unit): void {
    unit.hasChanged = 'self';
    this.emitChanges();
  }

  public getUnitNumbers(): number[] {
    return Array.from(Array(this.maxUnitsPerDay).keys());
  }

  private emitChanges(): void {
    this.unitsChange.emit(this.days
      .map(day => day.units.filter(unit => unit.hasChanged))
      .flat());
  }
}
