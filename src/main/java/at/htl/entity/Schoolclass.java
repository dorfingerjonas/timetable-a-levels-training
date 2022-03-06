package at.htl.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "TT_SCHOOLCLASS")
@Entity
public class Schoolclass extends PanacheEntityBase {

    @Id
    private String id;

    @Column
    private String room;

    public Schoolclass() {
    }

    public Schoolclass(String id, String room) {
        this.id = id;
        this.room = room;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", id, room);
    }
}
