package p.zolotaya.touristagency.data.entity;

import java.util.Objects;

public class Offer {
    private int id;
    private String country;
    private String city;
    private int dayPrice;
    private boolean isHot;

    public Offer() {
        super();
    }

    public Offer(String country, String city, int dayPrice, boolean isHot) {
        super();
        this.country = country;
        this.city = city;
        this.dayPrice = dayPrice;
        this.isHot = isHot;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getIsHot() {
        return isHot;
    }

    public void setHot(boolean hot) {
        isHot = hot;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getDayPrice() {
        return dayPrice;
    }

    public void setDayPrice(int dayPrice) {
        this.dayPrice = dayPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offer offer = (Offer) o;
        return id == offer.id &&
                dayPrice == offer.dayPrice &&
                isHot == offer.isHot &&
                Objects.equals(country, offer.country) &&
                Objects.equals(city, offer.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country, city, dayPrice, isHot);
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", dayPrice=" + dayPrice +
                ", isHot=" + isHot +
                '}';
    }
}
