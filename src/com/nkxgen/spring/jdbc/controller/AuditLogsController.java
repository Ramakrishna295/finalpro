package com.nkxgen.spring.jdbc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nkxgen.spring.jdbc.Dao.AuditLogDAO;
import com.nkxgen.spring.jdbc.model.AuditLogs;

@Controller
public class AuditLogsController {
	
	
	private  AuditLogDAO auditLogDAO;

    @Autowired
    public AuditLogsController(AuditLogDAO auditLogDAO) {
        this.auditLogDAO = auditLogDAO;
    }
    
	@RequestMapping(value="auditLogs")
	public String listAuditLogs(Model model)
	{
		List<AuditLogs>auditLogs = auditLogDAO.getAllAuditLogs();
        model.addAttribute("auditLogs", auditLogs);
		return "auditlogs";
	}
}
