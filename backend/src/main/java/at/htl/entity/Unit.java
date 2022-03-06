package at.htl.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Table(name = "TT_UNIT")
@Entity
public class Unit extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int day;

    @Column
    private int unit;

    @Column
    private String subject;

    @JoinColumn
    @ManyToOne
    private Teacher teacher;

    @JoinColumn
    @ManyToOne
    private Schoolclass schoolclass;

    public Unit() {
    }

    public Unit(int day, int unit, String subject, Teacher teacher, Schoolclass schoolclass) {
        this.day = day;
        this.unit = unit;
        this.subject = subject;
        this.teacher = teacher;
        this.schoolclass = schoolclass;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Schoolclass getSchoolclass() {
        return schoolclass;
    }

    public void setSchoolclass(Schoolclass schoolclass) {
        this.schoolclass = schoolclass;
    }

    @Override
    public String toString() {
        return String.format("%s on day %d in unit %d", subject, day, unit);
    }
}
