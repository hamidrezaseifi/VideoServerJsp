package de.seifi.videomanagerJsp.dao.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import de.seifi.videomanagerJsp.dao.IPathSubtitleDao;
import de.seifi.videomanagerJsp.dao.helper.JpaUnitConfiguration;
import de.seifi.videomanagerJsp.models.PathSubtitleModel;

@Repository
public class PathSubtitleDao implements IPathSubtitleDao {

  @Autowired
  JpaUnitConfiguration jpaUnitConfiguration;

  @Override
  public List<PathSubtitleModel> readAll() {

    final EntityManager entityManager = this.jpaUnitConfiguration.getEntityManager();

    final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    final CriteriaQuery<PathSubtitleModel> query = criteriaBuilder.createQuery(PathSubtitleModel.class);
    final Root<PathSubtitleModel> root = query.from(PathSubtitleModel.class);
    query.select(root);

    final Predicate predicate = criteriaBuilder.equal(root.get("state"), 1);
    query.where(predicate);

    final TypedQuery<PathSubtitleModel> typedQuery = entityManager.createQuery(query);

    // final String qr =
    // typedQuery.unwrap(org.hibernate.query.Query.class).getQueryString();
    // System.out.println("search workflow query: " + qr);

    final List<PathSubtitleModel> results = typedQuery.getResultList();
    entityManager.close();
    return results;
  }

}
