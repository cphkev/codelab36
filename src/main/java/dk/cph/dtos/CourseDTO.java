package dk.cph.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class CourseDTO {

    private String name;
    private String courseName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Set<StudentDTO> students;
    private TeacherDTO teacher;
}
