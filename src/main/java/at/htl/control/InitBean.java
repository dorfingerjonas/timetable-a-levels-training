package at.htl.control;

import at.htl.entity.Teacher;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class InitBean {

    @Inject
    TeacherRepository teacherRepository;

    public void init(@Observes StartupEvent ev) {
        teacherRepository.save(new Teacher(1000L, "Gerald", "Aistleitner", "216"));
        teacherRepository.save(new Teacher(1001L, "Herbert", "Lackinger", "108"));
        teacherRepository.save(new Teacher(1002L, "David", "Klewein", "216"));
        teacherRepository.save(new Teacher(1003L, "Michael", "Bucek", "108"));
        teacherRepository.save(new Teacher(1004L, "Thomas", "Stuetz", "E152"));
        teacherRepository.save(new Teacher(1004L, "Thomas", "Stuetz", "E152"));
    }
}
