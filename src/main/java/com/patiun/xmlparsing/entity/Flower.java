package com.patiun.xmlparsing.entity;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "flower", namespace = "plants")
@XmlType(name = "flower", namespace = "plants")
@XmlAccessorType(XmlAccessType.FIELD)
public class Flower extends Plant {

    @XmlElement(namespace = "plants")
    private int petalNumber;

    public Flower() {
    }

    public Flower(int id, String origin, String name, Soil soilType, int neededTemperature, boolean needsLight, int weeklyRequiredWater, int petalNumber) {
        super(id, origin, name, soilType, neededTemperature, needsLight, weeklyRequiredWater);
        this.petalNumber = petalNumber;
    }

    public Flower(FlowerBuilder builder) {
        super(builder);
        this.petalNumber = builder.petalNumber;
    }

    public int getPetalNumber() {
        return petalNumber;
    }

    public static class FlowerBuilder extends PlantBuilder {
        private int petalNumber;

        public FlowerBuilder() {
        }

        public void setPetalNumber(int petalNumber) {
            this.petalNumber = petalNumber;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Flower flower = (Flower) o;
        return petalNumber == flower.petalNumber;
    }

    @Override
    public int hashCode() {
        int prime = 17;
        prime = 31 * prime + super.hashCode();
        prime = 31 * prime + petalNumber;
        return prime;
    }

    @Override
    public String toString() {
        return super.toString() + "Flower{" +
                "petalNumber=" + petalNumber +
                '}';
    }
}
