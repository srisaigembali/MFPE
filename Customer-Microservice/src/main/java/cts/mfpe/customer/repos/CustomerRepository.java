package cts.mfpe.customer.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import cts.mfpe.customer.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
