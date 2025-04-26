package com.bakhytzhan.ums.config;

import com.bakhytzhan.ums.model.Course;
import com.bakhytzhan.ums.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@RequiredArgsConstructor
@Profile("!test")
public class DataInitializer implements CommandLineRunner {

    private final CourseRepository courseRepository;

    @Override
    public void run(String... args) throws Exception {
        if (courseRepository.count() == 0) {
            List<Course> courses = List.of(
                    Course.builder()
                            .name("Introduction to Computer Science")
                            .description("Learn the basics of computer science and programming.")
                            .credits(5)
                            .build(),
                    Course.builder()
                            .name("Data Structures and Algorithms")
                            .description("Deep dive into data structures and algorithms.")
                            .credits(6)
                            .build(),
                    Course.builder()
                            .name("Database Management Systems")
                            .description("Study relational databases, SQL, and normalization.")
                            .credits(4)
                            .build(),
                    Course.builder()
                            .name("Operating Systems")
                            .description("Learn about processes, memory management, and file systems.")
                            .credits(5)
                            .build(),
                    Course.builder()
                            .name("Software Engineering Principles")
                            .description("Explore software development methodologies and best practices.")
                            .credits(4)
                            .build()
            );

            courseRepository.saveAll(courses);
            System.out.println("Sample courses added to the database ✅");
        }
    }
}
