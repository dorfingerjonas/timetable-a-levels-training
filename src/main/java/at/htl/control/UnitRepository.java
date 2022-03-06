package at.htl.control;

import at.htl.entity.Unit;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class UnitRepository implements PanacheRepositoryBase<Unit, Long> {

    @Transactional
    public Unit save(Unit unit) {
        return getEntityManager().merge(unit);
    }
}
