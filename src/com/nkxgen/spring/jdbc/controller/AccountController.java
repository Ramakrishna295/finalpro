package com.nkxgen.spring.jdbc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nkxgen.spring.jdbc.Bal.ViewInterface;
import com.nkxgen.spring.jdbc.Dao.AccountApplicationDaoInterface;
import com.nkxgen.spring.jdbc.Dao.CustomerDaoInterface;
import com.nkxgen.spring.jdbc.events.AccountAppApprovalEvent;
import com.nkxgen.spring.jdbc.events.AccountAppRequestEvent;
import com.nkxgen.spring.jdbc.model.Account;
import com.nkxgen.spring.jdbc.model.AccountApplication;
import com.nkxgen.spring.jdbc.model.AccountApplicationInput;
import com.nkxgen.spring.jdbc.model.AccountApplicationViewModel;
import com.nkxgen.spring.jdbc.model.AccountDocumentInput;
import com.nkxgen.spring.jdbc.model.AccountInput;
import com.nkxgen.spring.jdbc.model.AccountViewModel;
import com.nkxgen.spring.jdbc.model.Accountdocument;
import com.nkxgen.spring.jdbc.model.Customertrail;
import com.nkxgen.spring.jdbc.model.Types;

@Controller
public class AccountController {

	@Autowired
	ApplicationEventPublisher applicationEventPublisher;

	@Autowired
	private AccountApplicationDaoInterface ac;
	@Autowired
	ViewInterface v;

	AccountController(AccountApplicationDaoInterface ac) {
		this.ac = ac;
	}

	@Autowired
	private CustomerDaoInterface cd;

	@RequestMapping("/master_account")
	public String masterAccount(Model model) {
		return "account_master_entry";
	}

	@RequestMapping(value = "/New_account_application", method = RequestMethod.POST)
	public String newAccountApplication(@Validated Types t, Model model) {
		String value = t.gethrefvalue();
		List<AccountApplicationViewModel> list1 = v.set7(value);

		// Check if the list is not empty before accessing the first object
		if (!list1.isEmpty()) {
			AccountApplicationViewModel firstAccount = list1.get(0);
			String acapActyId = firstAccount.getAcap_acty_id();
			System.out.println("acap_acty_id of first object: " + acapActyId);
		}

		model.addAttribute("list_of_account_applications", list1);
		return "New_account_application";
	}

	// =====================================================================================
	@RequestMapping(value = "/Any_Type_account_info", method = RequestMethod.POST)
	public String view_accounts(@Validated Types t, Model model) {
		String value = t.gethrefvalue();
		System.out.println(value);
		List<AccountViewModel> list1 = v.set2(value);
		List<Customertrail> list2 = new ArrayList<>();
		for (AccountViewModel account : list1) {
			Customertrail customer = cd.getRealCustomerById(account.getCustomerId());
			list2.add(customer);
		}
		model.addAttribute("list_of_account", list1);
		model.addAttribute("list_of_customer", list2);
		return "Any_Type_account_info";
	}

	// ===============================================================================================
	@RequestMapping("/result")
	public String ne(Model model) {
		return "New_account_application";
	}

	// ===========================================================================================
	@RequestMapping("/Account_new_application_form")
	public String accountNewApplicationForm(Model model) {
		return "Account_new_application_form";
	}

	// ===========================================================================================
	@RequestMapping("/Any_Type_account_info")
	public String anyTypeAccountInfo(Model model) {
		return "Any_Type_account_info";
	}
	// ===========================================================================================

	@RequestMapping(value = "/account_application_save", method = RequestMethod.POST)
	public String accountApplicationSave(@Validated AccountApplicationInput accountApplication, Model model) {
		AccountApplication account = new AccountApplication();
		account.setInputModelValues(accountApplication);
		ac.save(account);
		applicationEventPublisher.publishEvent(new AccountAppRequestEvent("New Application Form Filled"));
		return "Account_new_application_form";
	}
	// ===========================================================================================

	// ===================================================================================================
	@RequestMapping(value = "/save_to_account_database", method = RequestMethod.POST)
	public String save_to_account_database(@Validated AccountInput account, Model model) {
		Account a = new Account();
		a.setInputModelValues(account);
		ac.save1(a);
		applicationEventPublisher.publishEvent(new AccountAppApprovalEvent("Account Application Approved"));
		// change the return
		return "Account_new_application_form";
	}

	@RequestMapping(value = "/save_to_account_documents_database", method = RequestMethod.POST)
	public String save_to_account_documents_database(@Validated AccountDocumentInput accountdocument, Model model) {
		Accountdocument ad = new Accountdocument();
		ad.setInputModelValues(accountdocument);

		ac.save2(ad);
		// change the return
		return "Account_new_application_form";
	}
	// // ======================================================================================================

}
