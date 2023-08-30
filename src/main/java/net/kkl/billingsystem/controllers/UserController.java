package net.kkl.billingsystem.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.kkl.billingsystem.common.responses.AllResponse;
import net.kkl.billingsystem.common.dto.PayDTO;
import net.kkl.billingsystem.common.responses.TransactionHistoryResponse;
import net.kkl.billingsystem.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
@Validated
public class UserController {
    private final UserService userService;

    @PostMapping("pay")
    public ResponseEntity<AllResponse> payBillInSystem(@RequestBody @Valid PayDTO payDTO){
        return ResponseEntity.ok(userService.payBillInSystem(payDTO));
    }

    @PostMapping("transaction")
    public ResponseEntity<TransactionHistoryResponse> findByTransactionId(@RequestParam(name = "id")Integer transaction_id){
        return ResponseEntity.ok(userService.findByTransactionId(transaction_id));
    }
}
