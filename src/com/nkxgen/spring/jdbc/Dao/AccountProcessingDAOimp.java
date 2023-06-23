package com.nkxgen.spring.jdbc.Dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nkxgen.spring.jdbc.model.Account;

// ...

@Repository
@Transactional
public class AccountProcessingDAOimp implements AccountProcessingDAO {

	@PersistenceContext
	public EntityManager em;

	@Transactional
	public List<Account> getSavAcc(List<Account> acctype) {

		for (Account i : acctype) {
			System.out.println("inside");
			long acc = i.getApplicationId();
			String sql = "CALL smi(:acc)";
			Query query1 = em.createNativeQuery(sql);
			query1.setParameter("acc", acc);

			query1.executeUpdate();

			// System.out.println(acc);
			// StoredProcedureQuery query1 = em.createStoredProcedureQuery("smi");
			// query1.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
			// query1.setParameter(1, acc);
			//
			// query1.execute();
		}
		LocalDate currentDate = LocalDate.now();

		String dateString1 = currentDate.toString();
		for (Account a : acctype) {
			a.setLastUpdate(dateString1);
		}

		return acctype;
	}

	@Override
	@Transactional
	public void executeProcedure(int acc) {
		System.out.println("procedure");
		System.out.println(acc);

		StoredProcedureQuery query = em.createStoredProcedureQuery("smi");
		query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
		query.setParameter(1, acc);

		query.execute();

	}

}
