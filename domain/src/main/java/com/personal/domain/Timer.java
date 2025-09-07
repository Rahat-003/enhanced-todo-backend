package com.personal.domain;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "timer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Timer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @Builder.Default
    @OneToMany(mappedBy = "timer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TimerStartFinish> startFinishList = new ArrayList<>();

    public void addStartFinish(TimerStartFinish sf) {
        startFinishList.add(sf);
        sf.setTimer(this);
    }
}
