package org.example.cxc_hrm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.cxc_hrm.entity.enums.Priority;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "projects")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProjectEntity extends BaseEntity{
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate deadLine;
    private Priority priority;
    private String description;
    private PhotoEntity photo;

}
