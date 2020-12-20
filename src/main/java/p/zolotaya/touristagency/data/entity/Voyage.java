package p.zolotaya.touristagency.data.entity;

import java.util.Objects;

public class Voyage {
    private int id;
    private Offer offer;
    private int nights;
    private Tourist tourist;
    private String dateStart;

    public Voyage() {
        super();
    }

    public Voyage(Offer offer, int nights, Tourist tourist, String dateStart) {
        this.offer = offer;
        this.nights = nights;
        this.tourist = tourist;
        this.dateStart = dateStart;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public int getNights() {
        return nights;
    }

    public void setNights(int nights) {
        this.nights = nights;
    }

    public Tourist getTourist() {
        return tourist;
    }

    public void setTourist(Tourist tourist) {
        this.tourist = tourist;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voyage voyage = (Voyage) o;
        return id == voyage.id &&
                nights == voyage.nights &&
                Objects.equals(offer, voyage.offer) &&
                Objects.equals(tourist, voyage.tourist) &&
                Objects.equals(dateStart, voyage.dateStart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, offer, nights, tourist, dateStart);
    }

    @Override
    public String toString() {
        return "Voyage{" +
                "id=" + id +
                ", offer=" + offer +
                ", nights=" + nights +
                ", tourist=" + tourist +
                ", dateStart=" + dateStart +
                '}';
    }
}
