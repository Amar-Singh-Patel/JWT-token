package com.rpsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.rpsa.dto.PaymentDetailsDTO;
import com.rpsa.entity.PaymentDetails;
import com.rpsa.repository.PaymentSummary;
import com.rpsa.service.PaymentService;

import java.util.*;

@RestController
//@RequestMapping(")
public class PaymentDetailsController {

	@Autowired

	private PaymentService paymentService;

	@PostMapping("/save")
	public ResponseEntity<String> createDetails(@RequestBody Map<String, Object> request) {
		try {
			PaymentDetails paymentDetails = new PaymentDetails();

			// Extract and set payment details from request
			paymentDetails.setAmount((String) request.get("amount"));
			paymentDetails.setPayeeName((String) request.get("payeeName"));
			paymentDetails.setPeriodPaidFor((String) request.get("periodPaidFor"));
			paymentDetails.setPaymentInitiatedDate((String) request.get("paymentInitiatedDate"));
			paymentDetails.setCdpocode((String) request.get("cdpoCode"));
			paymentDetails.setRefNumber((String) request.get("refNumber"));
			paymentDetails.setAccNo((String) request.get("accNo"));
			paymentDetails.setIfscCode((String) request.get("ifscCode"));
			paymentDetails.setCt_id((String) request.get("ctid"));
			paymentDetails.setCrdate((String) request.get("crdate"));

			// Extract applId from spId if present
			Map<String, Object> spId = (Map<String, Object>) request.get("spId");
			if (spId != null && spId.containsKey("applId")) {
				paymentDetails.setApplId(String.valueOf(spId.get("applId")));
			} else {
				paymentDetails.setApplId((String) request.get("applId")); // Handling direct applId
			}

			// Validate that no fields are missing
			if (isMissingField(paymentDetails)) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("Error: Missing required fields in payment details.");
			}

			// Save payment details
			PaymentDetails savedDetails = paymentService.saveAndUpdateDetails(paymentDetails);

			if (savedDetails != null) {
				return ResponseEntity.status(HttpStatus.CREATED).body("Successful");
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("Failed to save payment details. Please check your request data.");
			}
		} catch (Exception e) {
			e.printStackTrace(); // Print full error in logs
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error processing request: " + e.getMessage());
		}
	}

	private boolean isMissingField(PaymentDetails paymentDetails) {
		// TODO Auto-generated method stub
		return false;
	}

	@GetMapping("/amar/{id}")
	public ResponseEntity<Map<String, String>> getFilteredDetailsById(@PathVariable Long id) {
		Optional<PaymentDetails> detailsById = paymentService.getDetailsById(id);

		return detailsById.map(details -> {
			Map<String, String> filteredResponse = new HashMap<>();
			filteredResponse.put("amount", details.getAmount());
			filteredResponse.put("periodPaidFor", details.getPeriodPaidFor());
			filteredResponse.put("paymentInitiatedDate", details.getPaymentInitiatedDate());

			return ResponseEntity.ok(filteredResponse);
		}).orElseGet(() -> ResponseEntity.notFound().build());
	}

//	@GetMapping("/filter")
//	public List<PaymentDetailsDTO> getFilteredDetailsByDate() {
//		List<PaymentDetails> detailsById = paymentService.getFilteredDetailsByDate();
//		List<PaymentDetailsDTO> list = new ArrayList<>();
//		PaymentDetailsDTO dto = new PaymentDetailsDTO();
//		for (int i=0;i<detailsById.size();i++) {
//
//			dto.setAmount(detailsById.get(i).getAmount());
//			dto.setPeriodPaidFor(detailsById.get(i).getPeriodPaidFor());
//			dto.setPaymentInitiatedDate(detailsById.get(i).getPaymentInitiatedDate());
//			list.add(dto);
//		}
//		
//		return list;
//
//	}
//}
@GetMapping("/filter")
public List<PaymentDetailsDTO> getFilteredDetailsByDate() {
    List<PaymentDetails> detailsById = paymentService.getFilteredDetailsByDate();
    List<PaymentDetailsDTO> list = new ArrayList<>();

    for (PaymentDetails paymentDetail : detailsById) {
        PaymentDetailsDTO dto = new PaymentDetailsDTO(); // Create a new instance for each iteration
        dto.setAmount(paymentDetail.getAmount());
        dto.setPeriodPaidFor(paymentDetail.getPeriodPaidFor());
        dto.setPaymentInitiatedDate(paymentDetail.getPaymentInitiatedDate());
        list.add(dto);
    }

    return list;
}

//@PostMapping("/summary")
//public ResponseEntity<List<PaymentSummary>> getPaymentSummaries(@RequestBody Map<String, String> requestBody) {
//    String applId = requestBody.get("applid");
//    if (applId == null || applId.isEmpty()) {
//        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "applid is required");
//    }
//    return ResponseEntity.ok(paymentService.getAllPaymentSummariesByApplId(applId));
//}}


@PostMapping("/summary")
public ResponseEntity<String> getPaymentSummaries(@RequestBody Map<String, String> requestBody) {
    String applId = requestBody.get("applid");
    if (applId == null || applId.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "applid is required");
    }

    List<PaymentSummary> payments = paymentService.getAllPaymentSummariesByApplId(applId);

    if (payments.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No payment summaries found");
    }

    // Constructing a raw text response (not JSON)
    StringBuilder response = new StringBuilder();
    for (PaymentSummary payment : payments) {
        response.append("Amount: ").append(payment.getAmount()).append("\n")
                .append("Period Paid For: ").append(payment.getPeriodPaidFor()).append("\n")
                .append("Payment Initiated Date: ").append(payment.getPaymentInitiatedDate()).append("\n\n");
    }

    return ResponseEntity.ok(response.toString());
}
}


















