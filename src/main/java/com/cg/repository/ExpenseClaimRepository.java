package com.cg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.entity.Employee;
import com.cg.entity.ExpenseClaim;

public interface ExpenseClaimRepository extends JpaRepository<ExpenseClaim, Integer> {

	ExpenseClaim findExpenseClaimByEmployee(Employee emp);

	ExpenseClaim findExpenseClaimByProjectProjectCode(int projectId);

	ExpenseClaim findExpenseClaimByExpenseClaimId(int checkExpenseClaimCode);
}
