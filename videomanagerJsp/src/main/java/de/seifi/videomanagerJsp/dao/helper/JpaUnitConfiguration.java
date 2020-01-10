package de.seifi.videomanagerJsp.dao.helper;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

@Component
public class JpaUnitConfiguration {

  // @PersistenceUnit(unitName = "default")
  // private EntityManagerFactory entityManagerFactory;

  @PersistenceContext
  private EntityManager entityManager;

  public EntityManager getEntityManager() {

    // final EntityManager entityManager = this.entityManagerFactory.createEntityManager();

    return this.entityManager;
  }

}
