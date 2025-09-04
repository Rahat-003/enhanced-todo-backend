package com.personal.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "timer_start_finish")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimerStartFinish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "timer_id", nullable = false)
    private Timer timer;

    @Column(name = "start_time_in_millis", nullable = false)
    private Long startTimeInMillis;

    @Column(name = "end_time_in_millis", nullable = false)
    private Long endTimeInMillis;
}
