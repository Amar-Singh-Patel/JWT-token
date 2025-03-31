package com.rpsa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rpsa.entity.PaymentDetails;

@Repository
public interface PaymentDetailsRepo extends JpaRepository<PaymentDetails, Long> {

	@Query("SELECT p FROM PaymentDetails p WHERE p.ct_id = :ctId")
	Optional<PaymentDetails> findByCt_id(@Param("ctId") String ctId);

//    @Query("SELECT p.mothersapiid FROM PaymentDetails p ORDER BY p.mothersapiid ASC")
//    List<Long> findAllIds();
	@Query("SELECT mothersapiid FROM PaymentDetails")
	List<Long> findAllIds();

	@Query(value = "SELECT COALESCE(MIN(t1.mothersapiid) + 1, 1) " + "FROM paymentdetail t1 "
			+ "LEFT JOIN paymentdetail t2 ON t1.mothersapiid + 1 = t2.mothersapiid "
			+ "WHERE t2.mothersapiid IS NULL", nativeQuery = true)
	Long findNextAvailableId();

	@Query(value = "SELECT * FROM paymentdetail", nativeQuery = true)
	List<PaymentDetails> findByPaymentDetails();
	
	
	 List<PaymentSummary> findByApplId(String applId);
	
	

//    @Query(value = "SELECT COALESCE(MIN(mothersapiid + 1), 1) " +
//            "FROM paymentdetail WHERE mothersapiid + 1 NOT IN (SELECT mothersapiid FROM paymentdetail)", 
//    nativeQuery = true)
//Long findNextAvailableId();

}

//    @Query("SELECT p FROM PaymentDetails p WHERE p.mothersapiid = ?1")
//    Optional<PaymentDetails> findByMothersapiid(Long id);
//
//    @Modifying
//    @Transactional
//    @Query("UPDATE PaymentDetails p SET p.deleted = true WHERE p.mothersapiid = ?1")
//    void softDeleteById(Long id);
//    @Query("SELECT p FROM PaymentDetails p WHERE p.ct_id = :ctId")
//    Optional<PaymentDetails> findByCt_id(@Param("ctId") String ctId);
