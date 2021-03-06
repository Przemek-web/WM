/*
 * Projekt końcowy realizowany w ramach studiów podyplomowych Nowoczesne aplikacje biznesowe Java EE edycja 8
 */
package pl.lodz.p.it.isdp.wm.ejb.facade;

import java.sql.SQLNonTransientConnectionException;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import org.eclipse.persistence.exceptions.DatabaseException;
import pl.lodz.p.it.isdp.wm.ejb.interceptor.LoggingInterceptor;
import pl.lodz.p.it.isdp.wm.exception.AppBaseException;
import pl.lodz.p.it.isdp.wm.model.OfficeAccount;

@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Interceptors(LoggingInterceptor.class)
@RolesAllowed("Administration")
public class OfficeAccountFacade extends AbstractFacade<OfficeAccount> {

    @PersistenceContext(unitName = "pl.lodz.p.it.spjava_WMPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OfficeAccountFacade() {
        super(OfficeAccount.class);
    }

    @RolesAllowed({"Administration", "Office"})
    public OfficeAccount findByLogin(String login) throws AppBaseException {
        try {
            TypedQuery<OfficeAccount> tq = em.createNamedQuery("OfficeAccount.findByLogin", OfficeAccount.class);
            tq.setParameter("lg", login);
            return tq.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (PersistenceException e) {
            final Throwable cause = e.getCause();
            if (cause instanceof DatabaseException && cause.getCause() instanceof SQLNonTransientConnectionException) {
                throw AppBaseException.createExceptionDatabaseConnectionProblem(e);
            } else {
                throw AppBaseException.createExceptionDatabaseQueryProblem(cause);
            }
        }
    }
}
