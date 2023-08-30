package net.kkl.billingsystem.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.kkl.billingsystem.common.responses.AllResponse;
import net.kkl.billingsystem.common.dto.BillDTO;
import net.kkl.billingsystem.common.Constant;
import net.kkl.billingsystem.exceptions.ApplicationErrorException;
import net.kkl.billingsystem.services.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/admin")
@RequiredArgsConstructor
@Validated
public class AdminController {
    private final AdminService adminService;

    @PostMapping("add")
    public ResponseEntity<AllResponse> addBillInSystem(@RequestBody @Valid BillDTO billDTO){
        return ResponseEntity.ok(adminService.addBillInSystem(billDTO));
    }

    @PostMapping("list")
    public ResponseEntity<AllResponse> findById(@RequestParam (name = "id")Integer bill_id){
        if(ObjectUtils.isEmpty(bill_id))
            throw new ApplicationErrorException("Bill-Id "+Constant.PARAM_NULL);
        return ResponseEntity.ok(adminService.findById(bill_id));
    }
}
