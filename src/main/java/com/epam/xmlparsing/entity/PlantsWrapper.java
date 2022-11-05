package com.epam.xmlparsing.entity;

import javax.xml.bind.annotation.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

@XmlRootElement(name = "plants", namespace = "plants")
public class PlantsWrapper {

    @XmlElements({
            @XmlElement(name = "tree", namespace = "plants", type = Tree.class),
            @XmlElement(name = "flower", namespace = "plants", type = Flower.class)
    })
    private final List<Plant> plants = new ArrayList<>();

    public List<Plant> getPlants() {
        return plants;
    }

    public int size() {
        return plants.size();
    }

    public boolean isEmpty() {
        return plants.isEmpty();
    }

    public Iterator<Plant> iterator() {
        return plants.iterator();
    }

    public Object[] toArray() {
        return plants.toArray();
    }

    public boolean add(Plant plant) {
        return plants.add(plant);
    }

    public boolean addAll(Collection<? extends Plant> c) {
        return plants.addAll(c);
    }

    public boolean addAll(int index, Collection<? extends Plant> c) {
        return plants.addAll(index, c);
    }

    public void replaceAll(UnaryOperator<Plant> operator) {
        plants.replaceAll(operator);
    }

    public void sort(Comparator<? super Plant> c) {
        plants.sort(c);
    }

    public void clear() {
        plants.clear();
    }

    public Plant get(int index) {
        return plants.get(index);
    }

    public Plant set(int index, Plant element) {
        return plants.set(index, element);
    }

    public void add(int index, Plant element) {
        plants.add(index, element);
    }

    public Plant remove(int index) {
        return plants.remove(index);
    }

    public ListIterator<Plant> listIterator() {
        return plants.listIterator();
    }

    public ListIterator<Plant> listIterator(int index) {
        return plants.listIterator(index);
    }

    public List<Plant> subList(int fromIndex, int toIndex) {
        return plants.subList(fromIndex, toIndex);
    }

    public Spliterator<Plant> spliterator() {
        return plants.spliterator();
    }

    public boolean removeIf(Predicate<? super Plant> filter) {
        return plants.removeIf(filter);
    }

    public Stream<Plant> stream() {
        return plants.stream();
    }

    public Stream<Plant> parallelStream() {
        return plants.parallelStream();
    }

    public void forEach(Consumer<? super Plant> action) {
        plants.forEach(action);
    }
}
