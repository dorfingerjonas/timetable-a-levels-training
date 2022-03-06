package at.htl.control;

import at.htl.entity.Schoolclass;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class SchoolclassRepository implements PanacheRepositoryBase<Schoolclass, String> {

    @Transactional
    public Schoolclass save(Schoolclass schoolclass) {
        return getEntityManager().merge(schoolclass);
    }
}
