package com.personal.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "countdown_timer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CountDownTimer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @Builder.Default
    @OneToMany(mappedBy = "countDownTimer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CountDownStartFinish> startFinishList = new ArrayList<>();

    public void addStartFinish(CountDownStartFinish sf) {
        startFinishList.add(sf);
        sf.setCountDownTimer(this);
    }
}
