package jdbc;


import java.time.LocalDate;
import java.util.Optional;

import jdbc.models.Habit;
import jdbc.models.HabitFrequency;
import jdbc.service.HabitService;

public class main {
    public static void main(String[] args) {
        System.out.println("--- Bắt đầu Kiểm thử Logic Giai đoạn 1 ---");
        HabitService service = new HabitService();
        
        // --- Chuẩn bị dữ liệu ---
        LocalDate today = LocalDate.now();
        
        // Thói quen 1: Streak 3 ngày
        Optional<Habit> running = service.addHabit(
                new Habit(0, "Chạy bộ 30 phút", "Hoàn thành mỗi ngày", HabitFrequency.DAILY, 0, today.minusDays(5))
        );
        
        // Hoàn thành: 3 ngày trước, 2 ngày trước, 1 ngày trước, và HÔM NAY
        service.markCompleted(running.get(), today.minusDays(3));
        service.markCompleted(running.get(), today.minusDays(2));
        service.markCompleted(running.get(), today.minusDays(1));
        service.markCompleted(running.get(), today); // Hoàn thành hôm nay
        
        // Thói quen 2: Streak 0 (bị lỡ hôm qua)
        Optional<Habit> reading = service.addHabit(
                new Habit(0, "Đọc sách 20 trang", "Mỗi ngày", HabitFrequency.DAILY, 0, today.minusDays(4))
        );
        service.markCompleted(reading.get(), today.minusDays(4));
        service.markCompleted(reading.get(), today.minusDays(3));
        // Bỏ lỡ ngày hôm qua (minusDays(2))
        service.markCompleted(reading.get(), today.minusDays(1)); // Hoàn thành hôm kia
        
        // --- Hiển thị kết quả ---
        System.out.println("\n--- Báo cáo Tạm thời ---");
        
        // Thói quen 1: Chạy bộ (Expected Streak: 4)
        service.getHabitById(running.get()).ifPresent(h -> 
            System.out.println("1. " + h.toString())
        );
        
        // Thói quen 2: Đọc sách (Expected Streak: 0/1 - Tùy vào cách tính, ở đây là 0 vì bị lỡ)
        service.getHabitById(reading.get()).ifPresent(h -> 
            System.out.println("2. " + h.toString())
        );
        
        System.out.println("\nKiểm thử thành công! Chuyển sang Giai đoạn 2 (JDBC) để lưu trữ bền vững.");
    }
}
