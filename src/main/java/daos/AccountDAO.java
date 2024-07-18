package daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import models.Account;
import utils.JPAConfig;

import java.util.List;

public class AccountDAO extends AbstractDAO<Account> {
    public AccountDAO() {
        super(Account.class);
    }

    public List<Account> getAccountByType(String type) {
        EntityManager entityManager = JPAConfig.getEntityManager();

        String jpql = "SELECT a FROM Account a WHERE a.type = :type";
        TypedQuery<Account> query = entityManager.createQuery(jpql, Account.class);
        query.setParameter("type", type);
        return query.getResultList();
    }

    public Integer getIdByUsername(String username) {
        AccountDAO accountDAO = new AccountDAO();
        Account account = accountDAO.findById(username);
        if (account.getType().equals("student"))
            return account.getStudent().getId();
        return account.getAdmin().getId();
    }
}
