export interface Class {
  id: string;
  room: string;
}

export interface Teacher {
  id: number;
  firstName: string;
  lastName: string;
  room: string;
}

export interface Unit {
  id: number;
  day: number;
  unit: number;
  subject: string;
  teacher: Teacher;
  schoolclass: Class | null;
  hasChanged?: 'self' | 'other' | number;
}
