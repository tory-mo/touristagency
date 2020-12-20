package p.zolotaya.touristagency.data.entity;

import java.util.Objects;

public class Voucher {
    private int id;
    private Voyage voyage;
    private int totalCost;
    private boolean isPaid;

    public Voucher() {
        super();
    }

    public Voucher(Voyage voyage, int totalCost, boolean isPaid) {
        this.voyage = voyage;
        this.totalCost = totalCost;
        this.isPaid = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Voyage getVoyage() {
        return voyage;
    }

    public void setVoyage(Voyage voyage) {
        this.voyage = voyage;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public boolean getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(boolean isPaid){ this.isPaid = isPaid; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voucher voucher = (Voucher) o;
        return id == voucher.id &&
                totalCost == voucher.totalCost &&
                isPaid == voucher.isPaid &&
                Objects.equals(voyage, voucher.voyage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, voyage, totalCost, isPaid);
    }

    @Override
    public String toString() {
        return "Voucher{" +
                "id=" + id +
                ", voyage=" + voyage +
                ", totalCost=" + totalCost +
                ", isPaid=" + isPaid +
                '}';
    }
}
