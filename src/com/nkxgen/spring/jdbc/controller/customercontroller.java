package com.nkxgen.spring.jdbc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nkxgen.spring.jdbc.Dao.CustomerDaoInterface;
import com.nkxgen.spring.jdbc.model.Customer;
import com.nkxgen.spring.jdbc.model.CustomerSub;
import com.nkxgen.spring.jdbc.model.Customertrail;
import com.nkxgen.spring.jdbc.model.service;

@Controller
public class customercontroller {
	@Autowired
	private CustomerDaoInterface cd;

	@RequestMapping(value = "/customer_data_trail_save", method = RequestMethod.POST)
	public String customer_data_trail_save(@Validated Customertrail customer, Model model) {
		cd.save_Trail(customer);
		return "Account_new_application_form";
	}

	// =================================================
	// real saving
	@RequestMapping(value = "/save_to_customer_database", method = RequestMethod.POST)
	public String save_to_customer_database(@RequestParam("CustomerId") Long customerId) {
		long customerid = customerId;
		System.out.println("print the value: " + customerid);
		Customertrail cus = cd.getCustomerById(customerid);
		System.out.println("print the value returning: " + cus.getId());

		Customer customer = service.dotheservice(cus);

		cd.save(customer);
		// change the return
		return "Account_new_application_form";
	}

	// =========================================================
	@RequestMapping(value = "/customers", method = RequestMethod.POST)
	public String customers(Model model) {

		List<Customertrail> customerList = cd.getAllCustomers();
		model.addAttribute("customerList", customerList);
		return "customer_edit_details_form";

	}

	// ===============================================================
	// editing the existing data
	// =========================================================
	@RequestMapping(value = "/customer_data_updation", method = RequestMethod.POST)
	public String CustomerDataUpdation(Customertrail updatedCustomer) {

		cd.updateCustomerDataById(updatedCustomer);
		return "customer_edit_details_form";

	}

	// =========================================================================================
	@RequestMapping(value = "/save_to_customersub_database", method = RequestMethod.POST)
	public String save_to_customersub_database(@Validated CustomerSub CustomerSub, Model model) {

		Customertrail customer2 = cd.getRealCustomerById(CustomerSub.getCustomerId());
		cd.changethese(customer2, CustomerSub);
		return "Any_Type_account_info";

	}
}