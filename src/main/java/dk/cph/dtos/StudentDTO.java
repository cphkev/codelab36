package dk.cph.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class StudentDTO {

    private String name;
    private String email;
    private Set<CourseDTO> courses;
}
