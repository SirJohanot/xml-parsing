package com.patiun.xmlparsing.entity;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "tree", namespace = "plants")
@XmlType(name = "tree", namespace = "plants")
@XmlAccessorType(XmlAccessType.FIELD)
public class Tree extends Plant {

    @XmlElement(namespace = "plants")
    private double trunkDiameter;

    public Tree() {
    }

    public Tree(int id, String origin, String name, Soil soilType, int neededTemperature, boolean needsLight, int weeklyRequiredWater, double trunkDiameter) {
        super(id, origin, name, soilType, neededTemperature, needsLight, weeklyRequiredWater);
        this.trunkDiameter = trunkDiameter;
    }

    public Tree(TreeBuilder builder) {
        super(builder);
        this.trunkDiameter = builder.trunkDiameter;
    }

    public double getTrunkDiameter() {
        return trunkDiameter;
    }

    public static class TreeBuilder extends PlantBuilder {
        private double trunkDiameter;

        public void setTrunkDiameter(double trunkDiameter) {
            this.trunkDiameter = trunkDiameter;
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
        Tree tree = (Tree) o;
        return Double.compare(tree.trunkDiameter, trunkDiameter) == 0;
    }

    @Override
    public int hashCode() {
        int prime = 17;
        prime = 31 * prime + super.hashCode();
        prime = 31 * prime + (int) trunkDiameter;
        return prime;
    }

    @Override
    public String toString() {
        return super.toString() + "Tree{" +
                "trunkDiameter=" + trunkDiameter +
                '}';
    }
}
