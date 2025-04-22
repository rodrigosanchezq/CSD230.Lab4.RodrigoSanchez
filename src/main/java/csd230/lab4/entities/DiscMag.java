package csd230.lab4.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.util.Date;

@Entity
public class DiscMag extends Magazine {
    @Column(name = "has_disc", nullable = true)
    private boolean hasDisc;

    public boolean getHasDisc() {
        return hasDisc;
    }

    public void setHasDisc(boolean hasDisc) {
        this.hasDisc = hasDisc;
    }

    public DiscMag() {
        hasDisc=false;
    }

    public DiscMag(boolean hasDisc) {
        this.hasDisc = hasDisc;
    }

    public DiscMag(double price, String title, int copies, int orderQty, Date currIssue, boolean hasDisc) {}
}