package com.personal.domain;

import com.personal.enumeration.Priority;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "task")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Enumerated(EnumType.ORDINAL)
    private Priority priority;

    @Column(name = "added_date", nullable = false)
    private LocalDate addedDate;

    @Column(name = "is_completed", nullable = false)
    private Boolean isCompleted;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "app_user_id", nullable = false)
    private AppUser appUser;

    @Builder.Default
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Timer> timers = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CountDownTimer> countDownTimers = new ArrayList<>();

    public void addTimer(Timer timer) {
        timers.add(timer);
        timer.setTask(this);
    }

    public void addCountDownTimer(CountDownTimer countDownTimer) {
        countDownTimers.add(countDownTimer);
        countDownTimer.setTask(this);
    }
}
