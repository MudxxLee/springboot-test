package com.laiwen.dao.mapper;

import com.laiwen.dao.AccountSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountSetRepository extends JpaRepository<AccountSet, String>,JpaSpecificationExecutor<AccountSet>{

	AccountSet findByAccountNumber(String accountNumber);

}