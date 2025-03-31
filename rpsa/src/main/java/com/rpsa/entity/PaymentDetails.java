package com.rpsa.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity


@Table(name = "paymentdetail", schema = "public")
public class PaymentDetails {  
	@Id
	@Column(name = "mothersapiid", nullable = false, unique = true)
	private Long mothersapiid;

    
    @Column(name= "amount", nullable= false)
    private String amount;
	
    @Column(name= "payeeName", nullable= false)
    private String payeeName;
   
	@Column(name= "periodPaidFor", nullable= false)
    private String periodPaidFor;
   
	@Column(name= "paymentInitiatedDate", nullable= false)
    private String paymentInitiatedDate;
    
	@Column(name= " cdpocode", nullable= false)
	private String cdpocode;
	
    @Column(name= "refnumber", nullable= false)
	private String refNumber;
    
    @Column(name= "accNo", nullable= false)
	private String accNo;
   
	
    @Column(name= "ifscCode", nullable= false)
    private String ifscCode;
   
    @Column(name= "crdate", nullable= false)
	private String crdate;
   
    @Column(name= "applId", nullable= false)
	private String applId;

   @Column(name= " ct_id", nullable= false, updatable = false)
    private String ct_id;
    
    private String cdponame;
	public Long getMothersapiid() {
		return mothersapiid;
	}
	public String getAmount() {
		return amount;
	}
	public String getPayeeName() {
		return payeeName;
	}
	public String getPeriodPaidFor() {
		return periodPaidFor;
	}
	public String getPaymentInitiatedDate() {
		return paymentInitiatedDate;
	}
	public String getCdpocode() {
		return cdpocode;
	}
	public String getRefNumber() {
		return refNumber;
	}
	public String getAccNo() {
		return accNo;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public String getCrdate() {
		return crdate;
	}
	public String getApplId() {
		return applId;
	}
	public String getCdponame() {
		return cdponame;
	}
	public void setMothersapiid(Long mothersapiid) {
		this.mothersapiid = mothersapiid;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}
	public void setPeriodPaidFor(String periodPaidFor) {
		this.periodPaidFor = periodPaidFor;
	}
	public void setPaymentInitiatedDate(String paymentInitiatedDate) {
		this.paymentInitiatedDate = paymentInitiatedDate;
	}
	public void setCdpocode(String cdpocode) {
		this.cdpocode = cdpocode;
	}
	public void setRefNumber(String refNumber) {
		this.refNumber = refNumber;
	}
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public void setCrdate(String crdate) {
		this.crdate = crdate;
	}
	public void setApplId(String applId) {
		this.applId = applId;
	}
	public void setCt_id(String ct_id) {
		this.ct_id = ct_id;
	}
	public void setCdponame(String cdponame) {
		this.cdponame = cdponame;
	}
	public String getCt_id() {
		return ct_id;
	}
	public void setAmount(Double amount2) {
		// TODO Auto-generated method stub
		
	}}