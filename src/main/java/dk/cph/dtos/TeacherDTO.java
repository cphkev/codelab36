package dk.cph.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class TeacherDTO {

    private String name;
    private String zoom;
    private Set<CourseDTO> courses;
}
