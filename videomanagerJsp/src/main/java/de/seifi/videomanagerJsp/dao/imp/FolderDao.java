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

import de.seifi.videomanagerJsp.dao.IFolderDao;
import de.seifi.videomanagerJsp.dao.helper.DaoBase;
import de.seifi.videomanagerJsp.models.FolderModel;

@Transactional
@Repository
public class FolderDao extends DaoBase implements IFolderDao {

  @Override
  public List<FolderModel> readAll(final boolean readDisabled) {

    final EntityManager entityManager = this.createEntityManager();

    final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    final CriteriaQuery<FolderModel> query = criteriaBuilder.createQuery(FolderModel.class);
    final Root<FolderModel> root = query.from(FolderModel.class);
    query.select(root);

    if (readDisabled == false) {
      final Predicate predicate = criteriaBuilder.equal(root.get("state"), 1);
      query.where(predicate);
    }

    final TypedQuery<FolderModel> typedQuery = entityManager.createQuery(query);

    // final String qr =
    // typedQuery.unwrap(org.hibernate.query.Query.class).getQueryString();
    // System.out.println("search workflow query: " + qr);

    final List<FolderModel> results = typedQuery.getResultList();
    entityManager.close();
    return results;
  }

  @Override
  public FolderModel readById(final Long id) {

    final EntityManager entityManager = this.createEntityManager();

    final FolderModel model = entityManager.find(FolderModel.class, id);
    entityManager.close();
    return model;
  }

  @Override
  public FolderModel save(final FolderModel folder) {

    final EntityManager entityManager = this.createEntityManager();
    // final EntityManager entityManager = emf.createEntityManager();

    entityManager.getTransaction().begin();
    final FolderModel model = entityManager.merge(folder);
    entityManager.getTransaction().commit();
    entityManager.close();

    return model;
  }

  @Override
  public void delete(final FolderModel folder) {

    if (folder != null) {

      final EntityManager entityManager = this.createEntityManager();
      final FolderModel exists = entityManager.contains(folder) ? folder : entityManager.merge(folder);

      entityManager.getTransaction().begin();
      entityManager.remove(exists);
      entityManager.getTransaction().commit();
      entityManager.close();

    }

  }

}
