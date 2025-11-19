package jdbc.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jdbc.dao.HabitDao;
import jdbc.dao.HabitLogDao;
import jdbc.models.Habit;
import jdbc.models.HabitLog;

// Trong HabitService.java


public class HabitService {
    private static final int habit = 0;
    private final HabitDao habitDAO = new HabitDao();
    private final HabitLogDao logDAO = new HabitLogDao();
    
    // 1. Thêm thói quen mới
    public Optional<Habit> addHabit(Habit newHabit) {
        try {
            return habitDAO.insertHabit(newHabit);
        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm thói quen vào DB: " + e.getMessage());
            return Optional.empty();
        }
    }
    
    // 2. Lấy danh sách thói quen
    public List<Habit> getAllHabits() {
        try {
            return habitDAO.getAllHabits();
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách thói quen từ DB: " + e.getMessage());
            return Collections.emptyList(); // Trả về danh sách rỗng nếu lỗi
        }
    }
    
    // 3. Ghi nhận hoàn thành (Cần bổ sung logic kiểm tra log đã tồn tại chưa)
    public void markCompleted(Habit habitId, LocalDate date) {
        // BƯỚC 2: Nếu chưa, thêm log mới
        logDAO.insertLog(new HabitLog(habitId, date));
        
        // BƯỚC 3: Cập nhật Streak
        updateHabitStreak(habit);
    }
    
    // 4. Thuật toán tính Streak (Dùng logs từ DB)
    private void updateHabitStreak(int habitId) {
        try {
            List<HabitLog> logs = logDAO.getLogsForHabit(habitId); 
            
            // --- THUẬT TOÁN TÍNH STREAK CỦA BẠN (Đã hoàn thành ở Giai đoạn 1) ---
            int calculatedStreak = 0; 
            // ... (thực hiện logic tính toán trên List<HabitLog> logs) ...
            
            // Sau khi tính xong, cập nhật lại DB
            habitDAO.updateStreak(habitId, calculatedStreak);
            
        } catch (SQLException e) {
            System.err.println("Lỗi khi tính toán và cập nhật streak: " + e.getMessage());
        }
    }

    public void markCompleted(Habit habitId, LocalDate minusDays) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'markCompleted'");
    }

    public Optional<Habit> getHabitById(Habit habit) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getHabitById'");
    }
}