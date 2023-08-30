package net.kkl.billingsystem.services;

import net.kkl.billingsystem.common.responses.AllResponse;
import net.kkl.billingsystem.common.dto.PayDTO;
import net.kkl.billingsystem.common.responses.TransactionHistoryResponse;

public interface UserService {
    AllResponse payBillInSystem(PayDTO payDTO);

    TransactionHistoryResponse findByTransactionId(Integer transactionId);
}
