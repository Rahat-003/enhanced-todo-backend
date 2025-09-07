package com.personal.domain;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "start_finish")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StartFinish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "timer_id")
    private Timer timer;

    @ManyToOne
    @JoinColumn(name = "countdown_timer_id")
    private CountDownTimer countDownTimer;

    @Column(nullable = false)
    private Long startTimeInMillis;

    @Column(nullable = false)
    private Long endTimeInMillis;
}
