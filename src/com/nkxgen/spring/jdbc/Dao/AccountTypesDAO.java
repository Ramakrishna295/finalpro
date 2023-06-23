package com.nkxgen.spring.jdbc.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nkxgen.spring.jdbc.model.LoanAccount;
import com.nkxgen.spring.jdbc.model.accountTypes;
import com.nkxgen.spring.jdbc.model.cashChest;
import com.nkxgen.spring.jdbc.model.service;

@Repository
@Transactional
public class AccountTypesDAO implements AccountTypeInterface {
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private service s;

	@Autowired
	LoanApplicationDaoInterface ll;

	// private long laonrepayamount = 0;

	public List<accountTypes> getAllAccounts() {
		String jpql = "SELECT l FROM accountTypes l";
		TypedQuery<accountTypes> query = entityManager.createQuery(jpql, accountTypes.class);
		return query.getResultList();
	}

	public List<accountTypes> getAllAccountDetails() {
		String jpql = "SELECT l FROM accountTypes l";
		TypedQuery<accountTypes> query = entityManager.createQuery(jpql, accountTypes.class);
		return query.getResultList();
	}

	public accountTypes getSelectedAccountDetails(int accountType) {
		accountTypes account = entityManager.find(accountTypes.class, accountType);
		System.out.println("im in the dao of account types");
		System.out.println(account.getAccountType());
		return account;
	}

	public void save(accountTypes accountTypes) {
		entityManager.merge(accountTypes);
	}
	// =================================================

	@Override
	public cashChest getallamount() {
		String query = "SELECT SUM(a.balance) FROM Account a";
		Query q = entityManager.createQuery(query);
		Long totalBalance = (Long) q.getSingleResult();
		String jpql = "SELECT SUM(t.loanAmount - t.deductionAmt) FROM LoanAccount t";
		Long result = entityManager.createQuery(jpql, Long.class).getSingleResult();
		List<LoanAccount> loan = ll.getAllLoans();
		long totalDifference = 0L;
		for (LoanAccount account : loan) {
			long value1 = (account.getLoanAmount() / account.getLoanDuration());
			long value2 = (account.getdeductionAmt() / value1);
			int numberofpayed = (int) (value1 - value2);
			long finalvalue = (long) ((numberofpayed) * (s.calinterest(account.getLoanAmount(),
					account.getdeductionAmt(), account.getLoanDuration(), account.getInterestRate(), "safe")));
			totalDifference += finalvalue;
		}
		Long mainamount = totalBalance + result + totalDifference;
		cashChest c = s.setcashchest(mainamount);

		return c;

	}

	// @Override
	// public void setTheLoanRepay(tempRepayment tarn, LoanAccount Loan) {
	// laonrepayamount += (tarn.getAmount() - (long) (Loan.getLoanAmount() / Loan.getLoanDuration()));
	// }

}
