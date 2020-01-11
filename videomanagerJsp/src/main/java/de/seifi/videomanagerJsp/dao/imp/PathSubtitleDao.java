package de.seifi.videomanagerJsp.dao.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import de.seifi.videomanagerJsp.dao.IPathSubtitleDao;
import de.seifi.videomanagerJsp.models.PathSubtitleModel;

@Repository
public class PathSubtitleDao implements IPathSubtitleDao {

  // @Autowired
  // JpaUnitConfiguration jpaUnitConfiguration;

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<PathSubtitleModel> readAll() {

    // final EntityManager entityManager = this.jpaUnitConfiguration.getEntityManager();

    final CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
    final CriteriaQuery<PathSubtitleModel> query = criteriaBuilder.createQuery(PathSubtitleModel.class);
    final Root<PathSubtitleModel> root = query.from(PathSubtitleModel.class);
    query.select(root);

    final Predicate predicate = criteriaBuilder.equal(root.get("state"), 1);
    query.where(predicate);

    final TypedQuery<PathSubtitleModel> typedQuery = this.entityManager.createQuery(query);

    // final String qr =
    // typedQuery.unwrap(org.hibernate.query.Query.class).getQueryString();
    // System.out.println("search workflow query: " + qr);

    final List<PathSubtitleModel> results = typedQuery.getResultList();

    return results;
  }

  @Override
  public PathSubtitleModel getPathSubtitlesFromPath(final String path) {

    return this.queryPathSubtitlsFromPath(path);
  }

  private PathSubtitleModel queryPathSubtitlsFromPath(final String path) {

    // final String sql = " select id, path, suburl from tblpathsubtitle where path = ?";

    // final EntityManager entityManager = this.jpaUnitConfiguration.getEntityManager();

    final CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
    final CriteriaQuery<PathSubtitleModel> query = criteriaBuilder.createQuery(PathSubtitleModel.class);
    final Root<PathSubtitleModel> root = query.from(PathSubtitleModel.class);
    query.select(root);

    final Predicate predicate = criteriaBuilder.equal(root.get("path"), path);
    query.where(predicate);

    final TypedQuery<PathSubtitleModel> typedQuery = this.entityManager.createQuery(query);

    // final String qr =
    // typedQuery.unwrap(org.hibernate.query.Query.class).getQueryString();
    // System.out.println("search workflow query: " + qr);

    final List<PathSubtitleModel> results = typedQuery.getResultList();

    return results.size() > 0 ? results.get(0) : null;

  }

  @Override
  public PathSubtitleModel readById(final Long id) {

    final PathSubtitleModel model = this.entityManager.find(PathSubtitleModel.class, id);
    return model;
  }

  @Transactional
  @Override
  public PathSubtitleModel save(final PathSubtitleModel model) {

    this.entityManager.getTransaction().begin();
    final PathSubtitleModel savedModel = this.entityManager.merge(model);
    this.entityManager.getTransaction().commit();
    return savedModel;
  }

  @Override
  public void delete(final PathSubtitleModel model) {

    if (model != null) {

      final PathSubtitleModel exists = this.entityManager.contains(model) ? model : this.entityManager.merge(model);

      this.entityManager.getTransaction().begin();
      this.entityManager.remove(exists);
      this.entityManager.getTransaction().commit();

    }

  }

}
