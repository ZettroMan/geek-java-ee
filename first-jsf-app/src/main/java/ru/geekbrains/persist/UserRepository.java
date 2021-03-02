package ru.geekbrains.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.util.List;

@Named
@ApplicationScoped
public class UserRepository {

    private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager entityManager;

    @Resource
    private UserTransaction userTransaction;

    @PostConstruct
    public void init() throws Exception {
        if (countAll() == 0) {
            try {
                userTransaction.begin();
                saveOrUpdate(new User(null, "Козьма", "Прутков"));
                saveOrUpdate(new User(null, "Max", "Frei"));
                saveOrUpdate(new User(null, "Emil", "Azhar"));
                saveOrUpdate(new User(null, "Boris", "Akunin"));
                userTransaction.commit();
            } catch (Exception e) {
                logger.error("", e);
                userTransaction.rollback();
            }
        }
    }

    public List<User> findAll() {
        return entityManager.createNamedQuery("findAllUsers", User.class).getResultList();
    }

    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    public Long countAll() {
        return entityManager.createNamedQuery("countAllUsers", Long.class).getSingleResult();
    }

    @Transactional
    public void saveOrUpdate(User user) {
        if (user.getId() == null) {
            entityManager.persist(user);
        }
        entityManager.merge(user);
    }

    @Transactional
    public void deleteById(Long id) {
        entityManager.createNamedQuery("deleteUserById").setParameter("id", id).executeUpdate();
    }
}
