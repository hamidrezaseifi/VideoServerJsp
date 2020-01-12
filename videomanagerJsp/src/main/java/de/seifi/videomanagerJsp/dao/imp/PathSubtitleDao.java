package de.seifi.videomanagerJsp.dao.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import de.seifi.videomanagerJsp.dao.IPathSubtitleDao;
import de.seifi.videomanagerJsp.dao.helper.DaoBase;
import de.seifi.videomanagerJsp.models.PathSubtitleModel;

@Repository
public class PathSubtitleDao extends DaoBase implements IPathSubtitleDao {

  @Override
  public List<PathSubtitleModel> readAll() {

    final EntityManager entityManager = this.createEntityManager();

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

  @Override
  public PathSubtitleModel getPathSubtitlesFromPath(final String path) {

    return this.queryPathSubtitlsFromPath(path);
  }

  private PathSubtitleModel queryPathSubtitlsFromPath(final String path) {

    final EntityManager entityManager = this.createEntityManager();

    final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    final CriteriaQuery<PathSubtitleModel> query = criteriaBuilder.createQuery(PathSubtitleModel.class);
    final Root<PathSubtitleModel> root = query.from(PathSubtitleModel.class);
    query.select(root);

    final Predicate predicate = criteriaBuilder.equal(root.get("path"), path);
    query.where(predicate);

    final TypedQuery<PathSubtitleModel> typedQuery = entityManager.createQuery(query);

    // final String qr =
    // typedQuery.unwrap(org.hibernate.query.Query.class).getQueryString();
    // System.out.println("search workflow query: " + qr);

    final List<PathSubtitleModel> results = typedQuery.getResultList();
    entityManager.close();
    return results.size() > 0 ? results.get(0) : null;

  }

  @Override
  public PathSubtitleModel readById(final Long id) {

    final EntityManager entityManager = this.createEntityManager();
    final PathSubtitleModel model = entityManager.find(PathSubtitleModel.class, id);
    entityManager.close();
    return model;
  }

  @Transactional
  @Override
  public PathSubtitleModel save(final PathSubtitleModel model) {

    final EntityManager entityManager = this.createEntityManager();
    entityManager.getTransaction().begin();
    final PathSubtitleModel savedModel = entityManager.merge(model);
    entityManager.getTransaction().commit();
    entityManager.close();
    return savedModel;
  }

  @Override
  public void delete(final PathSubtitleModel model) {

    if (model != null) {
      final EntityManager entityManager = this.createEntityManager();
      final PathSubtitleModel exists = entityManager.contains(model) ? model : entityManager.merge(model);

      entityManager.getTransaction().begin();
      entityManager.remove(exists);
      entityManager.getTransaction().commit();
      entityManager.close();

    }

  }

}
