package at.htl.control;

import at.htl.entity.Schoolclass;
import at.htl.entity.Teacher;
import at.htl.entity.Unit;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class InitBean {

    @Inject
    TeacherRepository teacherRepository;

    @Inject
    SchoolclassRepository schoolclassRepository;

    @Inject
    UnitRepository unitRepository;

    public void init(@Observes StartupEvent ev) {
        Teacher aistleitner = new Teacher(1000L, "Gerald", "Aistleitner", "216");
        Teacher lackinger = new Teacher(1001L, "Herbert", "Lackinger", "108");

        teacherRepository.save(aistleitner);
        teacherRepository.save(lackinger);
        teacherRepository.save(new Teacher(1002L, "David", "Klewein", "216"));
        teacherRepository.save(new Teacher(1003L, "Michael", "Bucek", "108"));
        teacherRepository.save(new Teacher(1004L, "Thomas", "Stuetz", "E152"));
        teacherRepository.save(new Teacher(1004L, "Thomas", "Stuetz", "E152"));

        Schoolclass class5bhitm = new Schoolclass("5BHITM", "E58-2");

        schoolclassRepository.save(new Schoolclass("5AHITM", "135"));
        schoolclassRepository.save(class5bhitm);

        unitRepository.save(new Unit(1, 1, "SEW", aistleitner, class5bhitm));
        unitRepository.save(new Unit(1, 2, "SEW", aistleitner, class5bhitm));
        unitRepository.save(new Unit(1, 3, "ITP", lackinger, class5bhitm));
    }
}
