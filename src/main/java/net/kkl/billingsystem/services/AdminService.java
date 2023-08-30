package net.kkl.billingsystem.services;

import net.kkl.billingsystem.common.responses.AllResponse;
import net.kkl.billingsystem.common.dto.BillDTO;

public interface AdminService {
    AllResponse addBillInSystem(BillDTO billDTO);

    AllResponse findById(Integer billId);
}
