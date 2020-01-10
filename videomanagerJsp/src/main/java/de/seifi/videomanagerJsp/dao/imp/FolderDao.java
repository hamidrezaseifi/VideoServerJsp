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

import de.seifi.videomanagerJsp.dao.IFolderDao;
import de.seifi.videomanagerJsp.models.FolderModel;

@Repository
public class FolderDao implements IFolderDao {

  // @Autowired
  // JpaUnitConfiguration jpaUnitConfiguration;

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<FolderModel> readAll() {

    final CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
    final CriteriaQuery<FolderModel> query = criteriaBuilder.createQuery(FolderModel.class);
    final Root<FolderModel> root = query.from(FolderModel.class);
    query.select(root);

    final Predicate predicate = criteriaBuilder.equal(root.get("state"), 1);
    query.where(predicate);

    final TypedQuery<FolderModel> typedQuery = this.entityManager.createQuery(query);

    // final String qr =
    // typedQuery.unwrap(org.hibernate.query.Query.class).getQueryString();
    // System.out.println("search workflow query: " + qr);

    final List<FolderModel> results = typedQuery.getResultList();

    return results;
  }

  @Override
  public FolderModel readById(final Long id) {

    final FolderModel model = this.entityManager.find(FolderModel.class, id);
    return model;
  }

  @Transactional
  @Override
  public FolderModel save(final FolderModel folder) {

    this.entityManager.getTransaction().begin();
    final FolderModel model = this.entityManager.merge(folder);
    this.entityManager.getTransaction().commit();
    return model;
  }

  @Override
  public void delete(final FolderModel folder) {

    if (folder != null) {

      final FolderModel exists = this.entityManager.contains(folder) ? folder : this.entityManager.merge(folder);

      this.entityManager.getTransaction().begin();
      this.entityManager.remove(exists);
      this.entityManager.getTransaction().commit();

    }

  }

}
