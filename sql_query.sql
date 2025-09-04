
CREATE TABLE task (
                      id BIGSERIAL PRIMARY KEY,
                      name VARCHAR(255) NOT NULL,
                      description VARCHAR(255),
                      added_date DATE NOT NULL,
                      app_user_id BIGINT NOT NULL,
                      CONSTRAINT fk_task_user FOREIGN KEY (app_user_id) REFERENCES app_user(id)
);

CREATE TABLE timer (
                       id BIGSERIAL PRIMARY KEY,
                       task_id BIGINT NOT NULL,
                       CONSTRAINT fk_timer_task FOREIGN KEY (task_id) REFERENCES task(id)
);

CREATE TABLE countdown_timer (
                                 id BIGSERIAL PRIMARY KEY,
                                 task_id BIGINT NOT NULL,
                                 CONSTRAINT fk_countdown_task FOREIGN KEY (task_id) REFERENCES task(id)
);

CREATE TABLE timer_start_finish (
                                    id BIGSERIAL PRIMARY KEY,
                                    timer_id BIGINT NOT NULL,
                                    start_time_in_millis BIGINT NOT NULL,
                                    end_time_in_millis BIGINT NOT NULL,
                                    CONSTRAINT fk_timer_sf FOREIGN KEY (timer_id) REFERENCES timer(id)
);


CREATE TABLE countdown_start_finish (
                                        id BIGSERIAL PRIMARY KEY,
                                        countdown_timer_id BIGINT NOT NULL,
                                        start_time_in_millis BIGINT NOT NULL,
                                        end_time_in_millis BIGINT NOT NULL,
                                        CONSTRAINT fk_countdown_sf FOREIGN KEY (countdown_timer_id) REFERENCES countdown_timer(id)
);
