package landry.paysted.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import landry.paysted.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
