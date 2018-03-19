package ru.drudenko.sitestatistics.internal;

import ru.drudenko.sitestatistics.domain.Attendance;
import ru.drudenko.sitestatistics.domain.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.math.BigInteger;
import java.time.Instant;

public class RepositoryImpl implements Repository {
    @Override
    public Attendance createAttendance(String userId, String pageId, Instant createDate) {

        Attendance attendance = new Attendance(userId, pageId,createDate);
        EntityManager entityManager = openEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(attendance); // cascades the tool & skill relationships
        entityManager.getTransaction().commit();
        return attendance;
    }

    @Override
    public long getNumberOfVisits(Instant after, Instant before) {
        EntityManager entityManager = openEntityManager();
        Query query = entityManager.createQuery("SELECT count(A) FROM Attendance A WHERE A.createdDate >= :after AND A.createdDate <= :before");
        query.setParameter("after", after);
        query.setParameter("before", before);
        return (long) query.getSingleResult();
    }

    @Override
    public long getNumberOfUniqueUsers(Instant after, Instant before) {
        EntityManager entityManager = openEntityManager();
        Query query = entityManager.createQuery("SELECT count(DISTINCT A.userId) FROM Attendance A WHERE A.createdDate >= :after AND A.createdDate <= :before");
        query.setParameter("after", after);
        query.setParameter("before", before);
        return (long) query.getSingleResult();
    }

    @Override
    public long getNumberOfRegularUsers(Instant after, Instant before) {
        EntityManager entityManager = openEntityManager();
        String sql = "select count(r.user) from (" +
                        "select a.userId as user, count( DISTINCT a.pageId) as countPage from public.attendance a " +
                        "where  a.createdDate >= :after AND a.createdDate <= :before group by a.userId" +
                    ") r where r.countPage > 9";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("after", after);
        query.setParameter("before", before);

        return ((BigInteger) query.getSingleResult()).longValue();
    }

    private static EntityManagerFactory entityManagerFactory = null;

    private static EntityManager openEntityManager() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory("sitestatistics");
        }
        return entityManagerFactory.createEntityManager();
    }
}
