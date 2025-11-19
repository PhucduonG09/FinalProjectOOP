package jdbc.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jdbc.models.Habit;
import jdbc.models.HabitFrequency;
import jdbc.models.HabitLog;

public class HabitLogDao  {

    // CREATE: Thêm thói quen mới và trả về ID đã tạo
    public Optional<Habit> insertHabit(Habit habit) throws SQLException {
        // Sử dụng ? để chống SQL Injection
        String sql = "INSERT INTO habits (name, goal, frequency, startDate, currentStreak) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseManager.connect();
             // Yêu cầu MySQL trả về ID tự động tăng (Statement.RETURN_GENERATED_KEYS)
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, habit.getName());
            pstmt.setString(2, habit.getGoal());
            pstmt.setString(3, habit.getFrequency().name()); 
            pstmt.setString(4, habit.getStartDate().toString()); // Lưu LocalDate dưới dạng String (DATE)
            pstmt.setInt(5, habit.getCurrentStreak());
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        int id = rs.getInt(1);
                        habit.setId(id); // Cập nhật ID cho đối tượng Habit
                        return Optional.of(habit);
                    }
                }
            }
            return Optional.empty();

        } // conn, pstmt được tự động đóng bởi try-with-resources
    }

    // READ: Lấy tất cả thói quen
    public List<Habit> getAllHabits() throws SQLException {
        List<Habit> habits = new ArrayList<>();
        String sql = "SELECT * FROM habits";
        
        try (Connection conn = DatabaseManager.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Habit habit = new Habit(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("goal"),
                    HabitFrequency.valueOf(rs.getString("frequency")),
                    rs.getInt("currentStreak"), // Chuyển String thành LocalDate
                    LocalDate.parse(rs.getString("startDate"))
                );
                habits.add(habit);
            }
        }
        return habits;
    }

    // UPDATE: Cập nhật Streak
    public void updateStreak(int habitId, int newStreak) throws SQLException {
        String sql = "UPDATE habits SET currentStreak = ? WHERE id = ?";
        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, newStreak);
            pstmt.setInt(2, habitId);
            pstmt.executeUpdate();
        }
    }
    
    // DELETE: Xóa thói quen
    public void deleteHabit(int id) throws SQLException {
        String sql = "DELETE FROM habits WHERE id = ?";
        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    public List<HabitLog> getLogsForHabit(int habitId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLogsForHabit'");
    }

	public void insertLog(HabitLog habitLog) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'insertLog'");
	}
}
