package p.zolotaya.touristagency.data.entity;

import java.util.Objects;

public class Tourist {
    private int id;
    private String name;
    private String surname;
    private String passportId;
    private boolean isVisa;

    public Tourist() {
        super();
    }

    public Tourist(String name, String surname, String passportId, boolean isVisa) {
        this.name = name;
        this.surname = surname;
        this.passportId = passportId;
        this.isVisa = isVisa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassportId() {
        return passportId;
    }

    public void setPassportId(String passportId) {
        this.passportId = passportId;
    }

    public boolean isVisa() {
        return isVisa;
    }

    public void setVisa(boolean visa) {
        isVisa = visa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tourist tourist = (Tourist) o;
        return id == tourist.id &&
                passportId == tourist.passportId &&
                isVisa == tourist.isVisa &&
                Objects.equals(name, tourist.name) &&
                Objects.equals(surname, tourist.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, passportId, isVisa);
    }

    @Override
    public String toString() {
        return "Tourist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", passportId=" + passportId +
                ", isVisa=" + isVisa +
                '}';
    }
}