package com.patiun.xmlparsing.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "growingTips", namespace = "plants")
@XmlAccessorType(XmlAccessType.FIELD)
public class GrowingTips {

    @XmlElement(namespace = "plants")
    private int neededTemperature;
    @XmlElement(namespace = "plants")
    private boolean needsLight;
    @XmlElement(namespace = "plants")
    private int weeklyRequiredWater;

    public GrowingTips() {
    }

    public GrowingTips(int neededTemperature, boolean needsLight, int weeklyRequiredWater) {
        this.neededTemperature = neededTemperature;
        this.needsLight = needsLight;
        this.weeklyRequiredWater = weeklyRequiredWater;
    }

    public int getNeededTemperature() {
        return neededTemperature;
    }

    public void setNeededTemperature(int neededTemperature) {
        this.neededTemperature = neededTemperature;
    }

    public boolean isNeedsLight() {
        return needsLight;
    }

    public void setNeedsLight(boolean needsLight) {
        this.needsLight = needsLight;
    }

    public int getWeeklyRequiredWater() {
        return weeklyRequiredWater;
    }

    public void setWeeklyRequiredWater(int weeklyRequiredWater) {
        this.weeklyRequiredWater = weeklyRequiredWater;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GrowingTips that = (GrowingTips) o;
        return neededTemperature == that.neededTemperature && needsLight == that.needsLight && weeklyRequiredWater == that.weeklyRequiredWater;
    }

    @Override
    public int hashCode() {
        int prime = 17;
        prime = 31 * prime + neededTemperature;
        prime = 31 * prime + (needsLight ? 1 : 0);
        prime = 31 * prime + weeklyRequiredWater;
        return prime;
    }

    @Override
    public String toString() {
        return "GrowingTips{" +
                "neededTemperature=" + neededTemperature +
                ", needsLight=" + needsLight +
                ", weeklyRequiredWater=" + weeklyRequiredWater +
                '}';
    }
}
