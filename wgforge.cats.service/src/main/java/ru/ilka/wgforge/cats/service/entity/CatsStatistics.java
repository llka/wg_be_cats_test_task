package ru.ilka.wgforge.cats.service.entity;

public class CatsStatistics {
    private Statistics tailLengthStat;
    private Statistics whiskersLengthStat;

    public CatsStatistics() {
    }

    public CatsStatistics(Statistics tailLengthStat, Statistics whiskersLengthStat) {
        this.tailLengthStat = tailLengthStat;
        this.whiskersLengthStat = whiskersLengthStat;
    }

    public Statistics getTailLengthStat() {
        return tailLengthStat;
    }

    public void setTailLengthStat(Statistics tailLengthStat) {
        this.tailLengthStat = tailLengthStat;
    }

    public Statistics getWhiskersLengthStat() {
        return whiskersLengthStat;
    }

    public void setWhiskersLengthStat(Statistics whiskersLengthStat) {
        this.whiskersLengthStat = whiskersLengthStat;
    }

    @Override
    public String toString() {
        return "CatsStatistics{" +
                "tailLengthStat=" + tailLengthStat +
                ", whiskersLengthStat=" + whiskersLengthStat +
                '}';
    }
}
