package de.seifi.videomanagerJsp.dao.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import de.seifi.videomanagerJsp.dao.ITabDao;
import de.seifi.videomanagerJsp.dao.helper.DaoBase;
import de.seifi.videomanagerJsp.models.TabModel;

@Repository
public class TabDao extends DaoBase implements ITabDao {

  @Override
  public List<TabModel> readAll() {

    final EntityManager entityManager = this.createEntityManager();

    final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    final CriteriaQuery<TabModel> query = criteriaBuilder.createQuery(TabModel.class);
    final Root<TabModel> root = query.from(TabModel.class);
    query.select(root);

    final Predicate predicate = criteriaBuilder.equal(root.get("state"), 1);
    query.where(predicate);

    final TypedQuery<TabModel> typedQuery = entityManager.createQuery(query);

    // final String qr =
    // typedQuery.unwrap(org.hibernate.query.Query.class).getQueryString();
    // System.out.println("search workflow query: " + qr);

    final List<TabModel> results = typedQuery.getResultList();
    entityManager.close();
    return results;
  }

}
