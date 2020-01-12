package de.seifi.videomanagerJsp.dao.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import de.seifi.videomanagerJsp.dao.ISetingsDao;
import de.seifi.videomanagerJsp.dao.helper.DaoBase;
import de.seifi.videomanagerJsp.models.SettingModel;

@Repository
public class SetingsDao extends DaoBase implements ISetingsDao {

  @Override
  public List<SettingModel> readAll() {

    final EntityManager entityManager = this.createEntityManager();

    final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    final CriteriaQuery<SettingModel> query = criteriaBuilder.createQuery(SettingModel.class);
    final Root<SettingModel> root = query.from(SettingModel.class);
    query.select(root);

    final Predicate predicate = criteriaBuilder.equal(root.get("state"), 1);
    query.where(predicate);

    final TypedQuery<SettingModel> typedQuery = entityManager.createQuery(query);

    // final String qr =
    // typedQuery.unwrap(org.hibernate.query.Query.class).getQueryString();
    // System.out.println("search workflow query: " + qr);

    final List<SettingModel> results = typedQuery.getResultList();
    entityManager.close();
    return results;
  }

}
