package com.mortgage.loan.controller;

import com.mortgage.loan.entity.Loan;
import com.mortgage.loan.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
public class LoanController {

    @Autowired
    private LoanService loanService;

    @GetMapping("/")
    public String getHome(){
        return "Deployed Spring boot application successfully...";
    }
    @GetMapping("/loans")
    public ModelAndView getHome(Model model){
        List<Loan> loans = loanService.getLoans();
        final var modelAndView = new ModelAndView();
        modelAndView.addObject("loans", loans);
        modelAndView.setViewName("index");
        return modelAndView;

    }

    @PostMapping("loan/create")
    public ResponseEntity<Loan> createLoan(@RequestBody Loan loan){
        loanService.saveLoan(loan);
        return new ResponseEntity<Loan>(HttpStatus.CREATED);
    }

    @GetMapping("loan/list")
    public ResponseEntity<List<Loan>> getLoansList(){
        List<Loan> loans = loanService.getLoans();
        return ResponseEntity.ok(loans);
    }

    @GetMapping("loan/{loanNo}")
    public ResponseEntity<Optional<Loan>> getLoanByLoanId(@PathVariable Integer loanNo){
        Optional<Loan> loan = loanService.getLoanByLoanNo(loanNo);
        if(loan.isPresent()){
            return ResponseEntity.ok(loan);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("loan/{loanNo}")
    public ResponseEntity<Loan> updateLoanByLoanId(@PathVariable Integer loanNo, @RequestBody Loan loan){
        Loan updateLoan = loanService.updateLoan(loanNo, loan);
        return ResponseEntity.ok(updateLoan);
    }

}