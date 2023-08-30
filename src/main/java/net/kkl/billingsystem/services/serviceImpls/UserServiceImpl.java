package net.kkl.billingsystem.services.serviceImpls;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kkl.billingsystem.common.responses.AllResponse;
import net.kkl.billingsystem.common.Constant;
import net.kkl.billingsystem.common.dto.PayDTO;
import net.kkl.billingsystem.common.responses.PayDTOResponse;
import net.kkl.billingsystem.common.responses.TransactionHistoryResponse;
import net.kkl.billingsystem.entities.Pay;
import net.kkl.billingsystem.exceptions.ApplicationErrorException;
import net.kkl.billingsystem.repository.PayRepository;
import net.kkl.billingsystem.services.UserService;
import net.kkl.billingsystem.utils.DateUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final PayRepository payRepository;
    @Override
    public AllResponse payBillInSystem(PayDTO payDTO) {
        checkPayEntity(payDTO);
        PayDTOResponse payDTOResponse = getPayDTOResponse(payDTO);
        return AllResponse.success("Transaction is successful!",payDTOResponse );
    }

    @Override
    public TransactionHistoryResponse findByTransactionId(Integer transactionId) {
        var transactionExisting = payRepository.findById(transactionId).orElseThrow(
                ()-> new ApplicationErrorException(Constant.TRANSACTION_NOT_FOUND)
        );
        return TransactionHistoryResponse.builder()
                .api_caller(transactionExisting.getApiCaller())
                .id(transactionExisting.getId())
                .amount(transactionExisting.getAmount())
                .reference_no(transactionExisting.getReferenceNo())
                .phone_number("********"+transactionExisting.getPhoneNumber()
                        .substring(9,transactionExisting.getPhoneNumber().length()))
                .build();
    }

    private PayDTOResponse getPayDTOResponse(PayDTO payDTO) {
        var savePay = payRepository.save(Pay.builder()
                        .billId(payDTO.getBillId())
                        .amount(payDTO.getAmount())
                        .apiCaller(payDTO.getApiCaller())
                        .referenceNo(payDTO.getReferenceNo())
                        .phoneNumber(payDTO.getPhoneNumber())
                        .transactionDate(DateUtils.getNowDate())
                .build());
        var payDTOResponse = PayDTOResponse.builder()
                .transaction_date(DateUtils.getNowDateToString(savePay.getTransactionDate()))
                .phone_number("********"+savePay.getPhoneNumber()
                        .substring(9,savePay.getPhoneNumber().length()))
                .amount(savePay.getAmount())
                .transaction_id(savePay.getId())
                .build();
        return payDTOResponse;
    }

    public void checkPayEntity(PayDTO payDTO){
        if(!"959".equals(payDTO.getPhoneNumber().substring(0,3)))
            throw new ApplicationErrorException(Constant.PHONE_NOT_MATCH);
        if(100000 <= payDTO.getAmount() && 0 < payDTO.getAmount())
            throw new ApplicationErrorException(Constant.OVER_PAY);
        if(payRepository.existsByReferenceNo(payDTO.getReferenceNo()))
            throw new ApplicationErrorException(Constant.REFERENCE_NO_IS_EXISTING);
    }
}
