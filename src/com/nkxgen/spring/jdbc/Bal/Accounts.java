package com.nkxgen.spring.jdbc.Bal;

import java.util.List;

import com.nkxgen.spring.jdbc.model.Account;

public interface Accounts {

	public List<Account> calcIntrst(List<Account> amnt);
}
