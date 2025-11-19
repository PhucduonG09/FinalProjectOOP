package jdbc.models;

import java.time.LocalDate;

public class HabitLog {
    private int logId;
    private int habitId;
    private LocalDate dateCompleted;

    public HabitLog(int logId, int habitId, LocalDate dataCompleted) {
        this.logId = logId;
        this.habitId = habitId;
        this.dateCompleted = dataCompleted;
    }

    public HabitLog(Habit habitId2, LocalDate date) {
        //TODO Auto-generated constructor stub
    }

    // Getters
    public int getLogId() {
        return logId;
    }

    public int getHabitId() {
        return habitId;
    }

    public LocalDate getDateCompleted() {
        return dateCompleted;
    }

    // Setters
    public void setLogId(int logId) {
        this.logId = logId;
    }

    public void setHabitId(int habitId) {
        this.habitId = habitId;
    }

    public void setDateCompleted(LocalDate dateCompleted) {
        this.dateCompleted = dateCompleted;
    }
}

