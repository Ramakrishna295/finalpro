package com.nkxgen.spring.jdbc.Bal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.nkxgen.spring.jdbc.Dao.AccountApplicationDaoInterface;
import com.nkxgen.spring.jdbc.Dao.AccountTypeInterface;
import com.nkxgen.spring.jdbc.Dao.BankUserInterface;
import com.nkxgen.spring.jdbc.Dao.LoanApplicationDaoInterface;
import com.nkxgen.spring.jdbc.Dao.LoanTypesInterface;
import com.nkxgen.spring.jdbc.model.Account;
import com.nkxgen.spring.jdbc.model.AccountApplication;
import com.nkxgen.spring.jdbc.model.AccountApplicationViewModel;
import com.nkxgen.spring.jdbc.model.AccountTypeView;
import com.nkxgen.spring.jdbc.model.AccountViewModel;
import com.nkxgen.spring.jdbc.model.BankUser;
import com.nkxgen.spring.jdbc.model.BankUserInput;
import com.nkxgen.spring.jdbc.model.BankUserViewModel;
import com.nkxgen.spring.jdbc.model.LoanAccount;
import com.nkxgen.spring.jdbc.model.LoanAccountViewModel;
import com.nkxgen.spring.jdbc.model.LoanApplication;
import com.nkxgen.spring.jdbc.model.LoanApplicationViewModel;
import com.nkxgen.spring.jdbc.model.LoanViewModel;
import com.nkxgen.spring.jdbc.model.LoansTypes;
import com.nkxgen.spring.jdbc.model.accountTypes;

public class View implements ViewInterface {
	@Autowired
	LoanApplicationDaoInterface ll;
	@Autowired
	LoanTypesInterface loan;
	@Autowired
	AccountApplicationDaoInterface ac;
	@Autowired
	BankUserInterface bankUserService;
	@Autowired
	private AccountTypeInterface account;

	List<LoanAccountViewModel> viewlist = new ArrayList<>();
	List<LoanApplicationViewModel> viewlist1 = new ArrayList<>();
	List<AccountViewModel> viewlist2 = new ArrayList<>();
	List<AccountApplicationViewModel> viewlist4 = new ArrayList<>();
	List<LoanViewModel> viewlist5 = new ArrayList<>();
	List<BankUserViewModel> viewlist3 = new ArrayList<>();
	List<AccountTypeView> viewlist6 = new ArrayList<>();
	List<Account> viewlist7 = new ArrayList<>();

	public List<LoanAccountViewModel> set(String typee) {
		viewlist.clear();
		List<LoanAccount> list = ll.getLoanAccountsByLoanType(typee);
		for (LoanAccount l : list) {
			LoanAccountViewModel la = new LoanAccountViewModel();
			la.setValuesFromLoanAccount(l);
			viewlist.add(la);
		}
		return viewlist;
	}

	public List<AccountViewModel> set12(List<Account> typee) {
		viewlist2.clear();
		for (Account l : typee) {
			AccountViewModel la = new AccountViewModel();
			AccountViewModel la1 = la.mapToViewModel(l);
			viewlist2.add(la1);
		}
		return viewlist2;
	}

	public List<LoanApplicationViewModel> set1(String typee) {
		viewlist1.clear();
		List<LoanApplication> list = ll.getLoanApplicationsByStatus(typee);
		for (LoanApplication l : list) {
			LoanApplicationViewModel la = new LoanApplicationViewModel();
			la.copyFromEntity(l);
			viewlist1.add(la);
		}
		return viewlist1;
	}

	public List<AccountApplicationViewModel> set7(String typee) {
		viewlist4.clear();
		List<AccountApplication> list = ac.getAccountsappByType(typee);
		for (AccountApplication l : list) {
			AccountApplicationViewModel la = new AccountApplicationViewModel();
			la.setEntityModelValues(l);
			viewlist4.add(la);
		}
		return viewlist4;
	}

	public List<LoanApplicationViewModel> set6(String typee) {
		viewlist1.clear();
		List<LoanApplication> list = ll.findByValue(typee);
		for (LoanApplication l : list) {
			LoanApplicationViewModel la = new LoanApplicationViewModel();
			la.copyFromEntity(l);
			viewlist1.add(la);
		}
		return viewlist1;
	}

	public List<AccountViewModel> set2(String typee) {
		viewlist2.clear();
		List<Account> list = ac.getAccountsByType(typee);
		for (Account l : list) {
			AccountViewModel la = new AccountViewModel();
			AccountViewModel la1 = la.mapToViewModel(l);
			viewlist2.add(la1);
		}
		return viewlist2;
	}

	public List<BankUserViewModel> set3() {
		viewlist3.clear();
		List<BankUser> list = bankUserService.getAllBankUsers();
		for (BankUser l : list) {
			BankUserViewModel la = new BankUserViewModel();
			la.setFromEntity(l);
			viewlist3.add(la);
		}
		return viewlist3;
	}

	public BankUser set13(BankUserInput status) {
		BankUser b = bankUserService.getBankUserById((int) status.getBusr_id());

		return b;
	}

	public List<BankUserViewModel> set4(BankUser status) {
		viewlist3.clear();
		List<BankUser> list = bankUserService.getBankUsersByDesignation(status);
		for (BankUser l : list) {
			BankUserViewModel la = new BankUserViewModel();
			la.setFromEntity(l);
			viewlist3.add(la);
		}
		return viewlist3;
	}

	public List<BankUserViewModel> set5(String status) {
		viewlist3.clear();
		List<BankUser> list = bankUserService.getBankUsersByDesignation(status);
		for (BankUser l : list) {
			BankUserViewModel la = new BankUserViewModel();
			la.setFromEntity(l);
			viewlist3.add(la);
		}
		return viewlist3;
	}

	public LoanViewModel set8(int status) {

		LoansTypes loank = loan.getSelectedLoanDetails(status);

		LoanViewModel la = new LoanViewModel();
		LoanViewModel la1 = la.mapEntityToViewModel(loank);
		return la1;

	}

	public List<LoanViewModel> set9() {
		viewlist5.clear();
		List<LoansTypes> loank = loan.getAllLoans();
		for (LoansTypes l : loank) {
			LoanViewModel la = new LoanViewModel();
			LoanViewModel la1 = la.mapEntityToViewModel(l);
			viewlist5.add(la1);
		}
		return viewlist5;
	}

	public AccountTypeView set10(int status) {

		accountTypes loank = account.getSelectedAccountDetails(status);

		AccountTypeView la = new AccountTypeView();
		AccountTypeView la1 = la.mapEntityToViewModel(loank);
		return la1;

	}

	public List<AccountTypeView> set11() {
		viewlist6.clear();
		List<accountTypes> loank = account.getAllAccounts();
		for (accountTypes l : loank) {
			AccountTypeView la = new AccountTypeView();
			AccountTypeView la1 = la.mapEntityToViewModel(l);
			viewlist6.add(la1);
		}
		return viewlist6;
	}

	public List<Account> checkdate(List<Account> l) {
		viewlist7.clear();
		LocalDate currentDate = LocalDate.now();
		LocalDate oneMonthBackDate = currentDate.minusMonths(1);

		for (Account a : l) {
			LocalDate ld = LocalDate.parse(a.getLastUpdate());
			System.out.println(oneMonthBackDate);
			System.out.println(ld);

			if (oneMonthBackDate.equals(ld)) {
				viewlist7.add(a);
			}
		}
		return viewlist7;

	}

	public List<Account> checkdates(List<Account> l) {
		viewlist7.clear();
		LocalDate currentDate = LocalDate.now();
		LocalDate oneMonthBackDate = currentDate.minusMonths(6);

		for (Account a : l) {
			LocalDate ld = LocalDate.parse(a.getLastUpdate());
			System.out.println(oneMonthBackDate);
			System.out.println(ld);

			if (oneMonthBackDate.compareTo(ld) < 0) {
				viewlist7.add(a);
			}
		}
		return viewlist7;

	}

}
