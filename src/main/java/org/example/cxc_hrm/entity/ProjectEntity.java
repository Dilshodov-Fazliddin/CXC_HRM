package org.example.cxc_hrm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.cxc_hrm.entity.enums.Priority;

import java.time.LocalDate;
import java.util.List;

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
    @OneToMany
    private List<ImageEntity> photo;

}

