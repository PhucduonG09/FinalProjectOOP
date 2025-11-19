package jdbc.models;

import java.time.LocalDate;

public class Habit {
    private int id;
    private String name, goal;
    private HabitFrequency frequency;
    private int currentStreak;
    private LocalDate startDate;

    public Habit(int id, String name, String goal, HabitFrequency frequency, int currentStreak, LocalDate startDate) {
        this.id = id;
        this.name = name;
        this.goal = goal;
        this.frequency = frequency;
        this.currentStreak = currentStreak;
        this.startDate = startDate;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGoal() {
        return goal;
    }

    public HabitFrequency getFrequency() {
        return frequency;
    }

    public int getCurrentStreak() {
        return currentStreak;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public void setFrequency(HabitFrequency frequency) {
        this.frequency = frequency;
    }

    public void setCurrentStreak(int currentStreak) {
        this.currentStreak = currentStreak;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Tên: " + name + ", Streak: " + currentStreak + " ngày.";
    }
}
