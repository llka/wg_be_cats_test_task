package ru.ilka.wgforge.cats.service.entity;

import java.util.List;
import java.util.Objects;

public class Statistics {
    private double mean;
    private double median;
    private List<Integer> mode;

    public Statistics() {
    }

    public Statistics(double mean, double median, List<Integer> mode) {
        this.mean = mean;
        this.median = median;
        this.mode = mode;
    }

    public double getMean() {
        return mean;
    }

    public void setMean(double mean) {
        this.mean = mean;
    }

    public double getMedian() {
        return median;
    }

    public void setMedian(double median) {
        this.median = median;
    }

    public Object[] getMode() {
        return mode.toArray();
    }

    public String getModeAsString() {
        StringBuilder modeAsString = new StringBuilder("{");
        for (int i = 0; i < mode.size(); i++) {
            modeAsString.append(mode.get(i));
            if (i != mode.size() - 1) {
                modeAsString.append(";");
            }
        }
        modeAsString.append("}");
        return modeAsString.toString();
    }

    public void setMode(List<Integer> mode) {
        this.mode = mode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Statistics that = (Statistics) o;
        return Double.compare(that.mean, mean) == 0 &&
                Double.compare(that.median, median) == 0 &&
                Objects.equals(mode, that.mode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mean, median, mode);
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "mean=" + mean +
                ", median=" + median +
                ", mode=" + mode +
                '}';
    }
}
