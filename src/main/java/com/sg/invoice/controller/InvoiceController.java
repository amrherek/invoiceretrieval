package com.sg.invoice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sg.invoice.service.InvoiceService;

@RestController

public class InvoiceController {

	@Autowired
	InvoiceService invoiceService;

	@GetMapping("/{projectId}/{invoiceId}")
	public ResponseEntity<byte[]> getInvoicePdf(@PathVariable String invoiceId, @PathVariable String projectId) {
		return invoiceService.getInvoicePdf(invoiceId, projectId);
	}
}
