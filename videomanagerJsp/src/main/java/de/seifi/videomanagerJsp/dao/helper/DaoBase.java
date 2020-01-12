package de.seifi.videomanagerJsp.dao.helper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;

@Transactional
public class DaoBase {

  @PersistenceUnit
  private EntityManagerFactory entityManagerFactory;

  protected EntityManager createEntityManager() {

    final EntityManager entityManager = this.entityManagerFactory.createEntityManager();
    return entityManager;
  }

}
