package com.nkxgen.spring.jdbc.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nkxgen.spring.jdbc.events.LoginEvent;
import com.nkxgen.spring.jdbc.events.LogoutEvent;
import com.nkxgen.spring.jdbc.model.User;
import com.nkxgen.spring.jdbc.validation.MailSender;;

@Controller
public class LoginController {

	@Autowired
	ApplicationEventPublisher applicationEventPublisher;

	// =====================================================================================================
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {

		return "LoginPage";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String login2() {
		applicationEventPublisher.publishEvent(new LogoutEvent("Logged Out"));
		return "LoginPage";
	}

	String otp;
	@Autowired
	MailSender obj;

	@RequestMapping(value = "/EnterOtp", method = RequestMethod.POST)
	public String enterOtp(@RequestParam("email") String to) {

		otp = obj.send(to);

		return "EnterOtp";
	}

	@RequestMapping(value = "/EnterEmail")
	public String sendOtp() {
		return "EnterEmail";
	}

	@RequestMapping(value = "/ConfirmPass", method = RequestMethod.POST)
	public String cp(@RequestParam("otp") String otp1) {
		System.out.println("Entered OTP : " + otp1 + " Sent OTP : " + otp);
		if (otp1.equals(otp))
			return "confirmPass";
		else
			return "EnterOtp";
	}

	@RequestMapping(value = "/verifylogin", method = RequestMethod.POST)
	public String loginProcess(@Validated User u, Model model) {
		System.out.println("User Page Requested");
		model.addAttribute("userName", u.getusername());
		model.addAttribute("password", u.getpassword());
		if (u.getpassword().equals("a"))
			return "NewFile";
		else
			return "sorry";
	}

	@RequestMapping(value = "/Test", method = RequestMethod.GET)
	public String main_page(Model model) {

		applicationEventPublisher.publishEvent(new LoginEvent("Logged In"));
		return "BankHomePage";
	}

	// ============================================================================================================================
	@RequestMapping(value = "/add_user", method = RequestMethod.GET)
	public String user1(Model model) {

		return "new_bank_user";

	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String user(Model model) {

		return "user_details";

	}
	// ================================================================================================================================

	// =============================================================================

	@RequestMapping(value = "/customers", method = RequestMethod.GET)
	public String customers(Model model) {
		System.out.println("requested for the customer_entry");
		return "customer_edit_details_form";
	}

	@RequestMapping(value = "/for", method = RequestMethod.GET)
	public String cusacc(Model model) {
		return "cuslogin";
	}
	// ===========================================================================

}
