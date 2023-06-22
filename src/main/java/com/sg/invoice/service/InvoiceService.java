package com.sg.invoice.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.Data;

@Service
@ConfigurationProperties
@Data
public class InvoiceService {

	@Autowired
	RestTemplate restTemplate;

	@Value("${invoice.retrieval.ksl.url}")
	String baseurl;

	Map<String, String> projectMap;

	public ResponseEntity<byte[]> getInvoicePdf(String invoiceId, String projectId) {
		if (invoiceId == null || projectId == null) {
			return ResponseEntity.ok().body("Missing URL parameters".getBytes());
		}

		// get project name
		String projectName = projectMap.get(projectId);
		if (projectName == null) {
			return ResponseEntity.ok().body("Incorrect URL parameters".getBytes());
		}

		// build URL
		String url = baseurl + "&kslProjectName=" + projectName + "&P_InvoiceNo=" + invoiceId;

		try {
			ResponseEntity<byte[]> response = restTemplate.exchange(url, HttpMethod.GET, null, byte[].class);
			if (response.getStatusCode() != HttpStatus.OK) {
				return ResponseEntity.ok().body(("Failed to retrieve invoice PDF " + invoiceId).getBytes());
			}
			return response;
		} catch (Exception ex) {
			return ResponseEntity.ok().body(("Failed to retrieve invoice PDF " + invoiceId).getBytes());
		}

	}
}
