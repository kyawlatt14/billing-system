package net.kkl.billingsystem.services.serviceImpls;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kkl.billingsystem.common.responses.AllResponse;
import net.kkl.billingsystem.common.dto.BillDTO;
import net.kkl.billingsystem.common.Constant;
import net.kkl.billingsystem.entities.Bill;
import net.kkl.billingsystem.exceptions.ApplicationErrorException;
import net.kkl.billingsystem.repository.BillRepository;
import net.kkl.billingsystem.services.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {
    private final BillRepository billRepository;
    @Override
    public AllResponse addBillInSystem(BillDTO billDTO) {
        var bill = billRepository.findByNameIgnoreCaseLike(billDTO.getName());
        if(!ObjectUtils.isEmpty(bill))
            throw new ApplicationErrorException(Constant.BILL_FAIL_MESSAGE);
        var saveBill= billRepository.save(Bill.builder()
                .name(billDTO.getName())
                .description(ObjectUtils.isEmpty(billDTO.getDescription())?"NULL":billDTO.getDescription())
                .build());
        log.info("====> {} {}",billDTO.getName(),Constant.BILL_SUCCESS_MESSAGE);
        return AllResponse.success(billDTO.getName()+": " +Constant.BILL_SUCCESS_MESSAGE,saveBill);
    }

    @Override
    public AllResponse findById(Integer billId) {
        var bill = billRepository.findById(billId);
        return AllResponse.success("Transaction is successful!", Arrays.asList(bill));
    }
}
