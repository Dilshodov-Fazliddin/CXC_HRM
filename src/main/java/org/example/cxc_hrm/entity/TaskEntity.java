package org.example.cxc_hrm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.example.cxc_hrm.entity.enums.Position;
import org.example.cxc_hrm.entity.enums.Priority;
import org.example.cxc_hrm.entity.enums.Status;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "tasks")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TaskEntity extends BaseEntity{
    private String name;
    private Position position;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate estimate;
    private Priority priority;
    @ManyToOne
    private ProjectEntity projectEntity;
    private Status status;
}
