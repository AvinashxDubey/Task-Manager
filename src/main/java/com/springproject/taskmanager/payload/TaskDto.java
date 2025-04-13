package com.springproject.taskmanager.payload;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private String title;

    private String description;

    private boolean completed;
}
