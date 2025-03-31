
package com.rpsa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.rpsa.entity.Cdponame;
import com.rpsa.entity.PaymentDetails;
import com.rpsa.repo.CdponameRepo;
import com.rpsa.repository.PaymentDetailsRepo;
import com.rpsa.repository.PaymentSummary;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PaymentService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);

    @Autowired
    private PaymentDetailsRepo paymentDetailsRepo;

    @Autowired
    private CdponameRepo cdponameRepo;

    public PaymentDetails saveAndUpdateDetails(PaymentDetails paymentDetails) {
        if (paymentDetails == null || paymentDetails.getCt_id() == null) {
            logger.error("Payment details or Ct_id is null");
            return null;
        }

        // Check if a record with the same ct_id exists
        Optional<PaymentDetails> existingRecord = paymentDetailsRepo.findByCt_id(paymentDetails.getCt_id());

        // Fetch Cdponame if available
        String cdpocode = paymentDetails.getCdpocode();
        Optional<Cdponame> cdponameOptional = cdponameRepo.findByCdpo(cdpocode);
        
        if (cdponameOptional.isPresent()) {
            paymentDetails.setCdponame(cdponameOptional.get().getCdponame());
        } else {
            logger.warn("Cdponame not found for cdpocode: " + cdpocode);
            return null;
        }

        if (existingRecord.isPresent()) {
            // Update existing record
            PaymentDetails existing = existingRecord.get();
            existing.setAmount(paymentDetails.getAmount());
            existing.setPayeeName(paymentDetails.getPayeeName());
            existing.setCdponame(paymentDetails.getCdponame());
            existing.setPeriodPaidFor(paymentDetails.getPeriodPaidFor());
            existing.setPaymentInitiatedDate(paymentDetails.getPaymentInitiatedDate());
            existing.setCdpocode(paymentDetails.getCdpocode());
            existing.setRefNumber(paymentDetails.getRefNumber());
            existing.setAccNo(paymentDetails.getAccNo());
            existing.setIfscCode(paymentDetails.getIfscCode());
            existing.setCrdate(paymentDetails.getCrdate());
            existing.setApplId(paymentDetails.getApplId());
            existing.setCt_id(paymentDetails.getCt_id());

            return paymentDetailsRepo.save(existing);
        }

        // If no existing record, find the next available mothersapiid
        Long nextId = paymentDetailsRepo.findNextAvailableId();
        if (nextId == null) nextId = 1L;  // Default to 1 if no record exists
        paymentDetails.setMothersapiid(nextId);

        return paymentDetailsRepo.save(paymentDetails);
    }

    public Optional<PaymentDetails> getDetailsById(Long id) {
        return paymentDetailsRepo.findById(id);
    }

	public List<PaymentDetails> getFilteredDetailsByDate() {
		
		return paymentDetailsRepo.findAll();
	}
	

	public List<PaymentSummary> getAllPaymentSummariesByApplId(String applId) {
        List<PaymentSummary> results = paymentDetailsRepo.findByApplId(applId);
        if (results.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No records found for applId: " + applId);
        }
        return results;
    }
}

	
	
        