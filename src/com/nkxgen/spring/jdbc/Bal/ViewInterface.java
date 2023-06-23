package com.nkxgen.spring.jdbc.Bal;

import java.util.List;

import com.nkxgen.spring.jdbc.model.Account;
import com.nkxgen.spring.jdbc.model.AccountApplicationViewModel;
import com.nkxgen.spring.jdbc.model.AccountTypeView;
import com.nkxgen.spring.jdbc.model.AccountViewModel;
import com.nkxgen.spring.jdbc.model.BankUser;
import com.nkxgen.spring.jdbc.model.BankUserInput;
import com.nkxgen.spring.jdbc.model.BankUserViewModel;
import com.nkxgen.spring.jdbc.model.LoanAccountViewModel;
import com.nkxgen.spring.jdbc.model.LoanApplicationViewModel;
import com.nkxgen.spring.jdbc.model.LoanViewModel;

public interface ViewInterface {

	List<LoanAccountViewModel> set(String typee);

	List<LoanApplicationViewModel> set1(String typee);

	List<AccountApplicationViewModel> set7(String typee);

	List<LoanApplicationViewModel> set6(String typee);

	List<AccountViewModel> set2(String typee);

	List<BankUserViewModel> set3();

	List<BankUserViewModel> set4(BankUser status);

	List<BankUserViewModel> set5(String status);

	LoanViewModel set8(int status);

	List<LoanViewModel> set9();

	AccountTypeView set10(int status);

	List<AccountTypeView> set11();

	List<Account> checkdate(List<Account> l);

	List<Account> checkdates(List<Account> l);

	List<AccountViewModel> set12(List<Account> l1);

	BankUser set13(BankUserInput status);

}
