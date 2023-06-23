package com.nkxgen.spring.jdbc.Dao;

import java.util.List;

import com.nkxgen.spring.jdbc.model.Account;
import com.nkxgen.spring.jdbc.model.InterestCal;

public interface AccountProcessingDAO {

	public List<Account> getSavAcc(List<Account> acctype);

	public void executeProcedure(int accno);

}
