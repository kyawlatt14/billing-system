package net.kkl.billingsystem.services;

import net.kkl.billingsystem.common.responses.AllResponse;
import net.kkl.billingsystem.common.dto.LoginDTO;
import net.kkl.billingsystem.common.dto.UserDTO;

public interface AuthenticationService {
    AllResponse registeredByUser(UserDTO userDTO);
    AllResponse login(LoginDTO loginDTO);
    AllResponse registeredByAdmin(UserDTO userDTO);
}
