package at.htl.control;

import at.htl.entity.Teacher;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class TeacherRepository implements PanacheRepositoryBase<Teacher, Long> {

    @Transactional
    public Teacher save(Teacher teacher) {
        return getEntityManager().merge(teacher);
    }
}
