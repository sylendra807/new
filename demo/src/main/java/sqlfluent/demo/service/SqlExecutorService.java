package sqlfluent.demo.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SqlExecutorService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public List<?> runQuery(String sql) {
        try {
            System.out.println("Executing SQL: " + sql);
            return em.createNativeQuery(sql).getResultList();
        } catch (Exception e) {
            System.err.println("SQL execution failed: " + e.getMessage());
            throw e;
        }
    }
}
