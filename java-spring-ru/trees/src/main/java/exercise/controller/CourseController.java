package exercise.controller;

import exercise.model.Course;
import exercise.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping(path = "")
    public Iterable<Course> getCorses() {
        return courseRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Course getCourse(@PathVariable long id) {
        return courseRepository.findById(id);
    }

    // BEGIN
    @GetMapping(path = "/{id}/previous")
    public Iterable<Course> getPrevious(@PathVariable long id) {
        Course post = courseRepository.findById(id);
        var path = post.getPath();
        if (path == null) {
            return new ArrayList<>();
        }

        var result = new ArrayList<Course>();
        for (var prev_id : path.split("[.]")) {
            result.add(courseRepository.findById(Long.valueOf(prev_id))
                    .orElseThrow());
        }
        return result;
    }
    // END

}
