package com.lossp.microservice.transactionimpl.service;

import com.lossp.microservice.transactionintf.transaction.ITransactionService;
import org.apache.dubbo.config.annotation.Service;

@Service
public class TransactionService implements ITransactionService {
    @Override
    public void saveTransaction() {
        System.out.println("========the transaction service is saving the order !!!");
    }
}
