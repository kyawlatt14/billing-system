package net.kkl.billingsystem.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.kkl.billingsystem.common.responses.AllResponse;
import net.kkl.billingsystem.common.dto.LoginDTO;
import net.kkl.billingsystem.common.dto.UserDTO;
import net.kkl.billingsystem.services.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
@Validated
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("registeredByUser")
    public ResponseEntity<AllResponse> registeredByUser (@RequestBody @Valid UserDTO userDTO){
        return ResponseEntity.ok(authenticationService.registeredByUser(userDTO));
    }

    @PostMapping("registeredByAdmin")
    public ResponseEntity<AllResponse> registeredByAdmin (@RequestBody @Valid UserDTO userDTO){
        return ResponseEntity.ok(authenticationService.registeredByAdmin(userDTO));
    }

    @PostMapping("login")
    public ResponseEntity<AllResponse> login (@RequestBody @Valid LoginDTO loginDTO){
        return ResponseEntity.ok(authenticationService.login(loginDTO));
    }
}
