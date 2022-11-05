package com.epam.xmlparsing.entity;

import javax.xml.bind.annotation.*;
import java.util.Objects;

@XmlRootElement(name = "plant", namespace = "plants")
@XmlSeeAlso({Flower.class, Tree.class})
@XmlAccessorType(XmlAccessType.FIELD)
public class Plant {

    @XmlAttribute(required = true)
    private int id;
    @XmlAttribute
    private String origin = "";
    @XmlElement(namespace = "plants")
    private String name;
    @XmlElement(namespace = "plants")
    private Soil soilType;
    @XmlElement(name = "growingTips", namespace = "plants")
    private GrowingTips growingTips;

    public Plant() {
    }

    public Plant(int id, String origin, String name, Soil soilType, int neededTemperature, boolean needsLight, int weeklyRequiredWater) {
        this.id = id;
        this.origin = origin;
        this.name = name;
        this.soilType = soilType;
        growingTips = new GrowingTips(neededTemperature, needsLight, weeklyRequiredWater);
    }

    public Plant(PlantBuilder builder) {
        this.id = builder.id;
        this.origin = builder.origin;
        this.name = builder.name;
        this.soilType = builder.soilType;
        growingTips = new GrowingTips(builder.neededTemperature, builder.needsLight, builder.weeklyRequiredWater);
    }

    public int getId() {
        return id;
    }

    public String getOrigin() {
        return origin;
    }

    public String getName() {
        return name;
    }

    public Soil getSoilType() {
        return soilType;
    }

    public int getNeededTemperature() {
        return growingTips.getNeededTemperature();
    }

    public boolean isNeedsLight() {
        return growingTips.isNeedsLight();
    }

    public int getWeeklyRequiredWater() {
        return growingTips.getWeeklyRequiredWater();
    }

    public static class PlantBuilder {
        private int id;
        private String origin = "";
        private String name;
        private Soil soilType;
        private int neededTemperature;
        private boolean needsLight;
        private int weeklyRequiredWater;

        public PlantBuilder() {
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setSoilType(Soil soilType) {
            this.soilType = soilType;
        }

        public void setNeededTemperature(int neededTemperature) {
            this.neededTemperature = neededTemperature;
        }

        public void setNeedsLight(boolean needsLight) {
            this.needsLight = needsLight;
        }

        public void setWeeklyRequiredWater(int weeklyRequiredWater) {
            this.weeklyRequiredWater = weeklyRequiredWater;
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
        Plant plant = (Plant) o;
        return id == plant.id && Objects.equals(origin, plant.origin) && Objects.equals(name, plant.name) && soilType == plant.soilType && Objects.equals(growingTips, plant.growingTips);
    }

    @Override
    public int hashCode() {
        int prime = 17;
        prime = 31 * prime + id;
        prime = 31 * prime + (origin == null ? 0 : origin.hashCode());
        prime = 31 * prime + (name.hashCode());
        prime = 31 * prime + (soilType.hashCode());
        prime = 31 * prime + growingTips.hashCode();
        return prime;
    }

    @Override
    public String toString() {
        return "Plant{" +
                "id=" + id +
                ", origin='" + origin + '\'' +
                ", name='" + name + '\'' +
                ", soilType=" + soilType +
                ", growingTips=" + growingTips +
                '}';
    }
}
