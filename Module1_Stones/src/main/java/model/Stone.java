package model;

import java.io.Serializable;

public class Stone implements Comparable<Stone>, Serializable {
    private String name;
    private String type;
    private double weight;
    private float cost;
    private float transparency;

    public Stone(String name, String type, double weight, float cost, float transparency) {
        this.name = name;
        this.type = type;
        this.weight = weight;
        this.cost = cost;
        this.transparency = transparency;
    }


    public double getWeight() {
        return weight;
    }

    public float getCost() {
        return cost;
    }

    public float getTransparency() {
        return transparency;
    }

    public int compareTo(Stone o) {
        return Float.compare(this.cost, o.cost);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stone stone = (Stone) o;

        if (Double.compare(stone.weight, weight) != 0) return false;
        if (Float.compare(stone.cost, cost) != 0) return false;
        if (Float.compare(stone.transparency, transparency) != 0) return false;
        if (name != null ? !name.equals(stone.name) : stone.name != null) return false;
        return type != null ? type.equals(stone.type) : stone.type == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        temp = Double.doubleToLongBits(weight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (cost != +0.0f ? Float.floatToIntBits(cost) : 0);
        result = 31 * result + (transparency != +0.0f ? Float.floatToIntBits(transparency) : 0);
        return result;
    }

    @Override
    public String toString() {
        return "The stone: " +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", cost=" + cost +
                ", transparency=" + transparency +
                ", type - " + type;
    }

}
