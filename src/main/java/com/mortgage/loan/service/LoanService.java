package com.mortgage.loan.service;

import com.mortgage.loan.entity.Loan;
import com.mortgage.loan.repository.LoanRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    public void saveLoan(Loan Loan){
        loanRepository.save(Loan);
    }

    public Optional<Loan> getLoanByLoanNo(Integer id) {
        return loanRepository.findByLoanNo(Math.toIntExact(id));
    }

    public List<Loan> getLoans() {
        return loanRepository.findAll();
    }

    public Loan updateLoan(Integer loanNo, Loan loan) {
        if(loanNo==null){
            throw new IllegalArgumentException("Loan Number can't be null");
        }
        Loan existingLoan = loanRepository.findByLoanNo(Math.toIntExact(loanNo)).orElseThrow(
                () -> new EntityNotFoundException(String.valueOf(loanNo))
        );
        existingLoan.setFirstName(loan.getFirstName());
        existingLoan.setLastName(loan.getLastName());
        existingLoan.setPhoneNo(loan.getPhoneNo());
        existingLoan.setAddress(loan.getAddress());
        return loanRepository.save(existingLoan);
    }

}