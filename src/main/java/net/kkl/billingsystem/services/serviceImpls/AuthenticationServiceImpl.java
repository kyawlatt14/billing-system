package net.kkl.billingsystem.services.serviceImpls;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kkl.billingsystem.common.*;
import net.kkl.billingsystem.common.dto.LoginDTO;
import net.kkl.billingsystem.common.dto.UserDTO;
import net.kkl.billingsystem.common.responses.AllResponse;
import net.kkl.billingsystem.common.responses.AuthenticationResponse;
import net.kkl.billingsystem.entities.Token;
import net.kkl.billingsystem.entities.User;
import net.kkl.billingsystem.entities.enums.Role;
import net.kkl.billingsystem.entities.enums.TokenType;
import net.kkl.billingsystem.exceptions.ApplicationErrorException;
import net.kkl.billingsystem.repository.TokenRepository;
import net.kkl.billingsystem.repository.UserRepository;
import net.kkl.billingsystem.services.AuthenticationService;
import net.kkl.billingsystem.services.JwtService;
import net.kkl.billingsystem.utils.DateUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;

    @Override
    public AllResponse registeredByUser(UserDTO userDTO) {
        checkUserEntity(userDTO);
        var savedUser = mapUserRequestToUser(userDTO);
        var jwtToken = jwtService.generateToken(savedUser);
//        var refreshToken = jwtService.generateRefreshToken(savedUser);
        saveUserToken(savedUser, jwtToken);
        log.info("====> {}: "+Constant.REGISTER_SUCCESS_MESSAGE ,userDTO.getUsername());
        return AllResponse.success(Constant.REGISTER_SUCCESS_MESSAGE, AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken("*****")
                .build());
    }
    @Override
    public AllResponse login(LoginDTO loginDTO) {
        var jwtToken = "";
//        var refreshToken ="";
        var user = userRepository.findByUsernameIgnoreCaseLike(loginDTO.getUsername()).orElseThrow(
                ()->new ApplicationErrorException(Constant.USER_NOT_FOUND));
        if (!ObjectUtils.isEmpty(user) && !passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new ApplicationErrorException(Constant.PASSWORD_NOT_WORN);
        } else {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
            jwtToken = jwtService.generateToken(user);
            revokeAllUserTokens(user);
            saveUserToken(user, jwtToken);
//            refreshToken = jwtService.generateRefreshToken(user);
            log.info("====> {} is successfully authenticated...", loginDTO.getUsername());
            return AllResponse.success(Constant.LOGIN_SUCCESS,AuthenticationResponse.builder()
                            .accessToken(jwtToken)
                            .refreshToken("*****")
                    .build());
        }
    }

    @Override
    public AllResponse registeredByAdmin(UserDTO userDTO) {
        checkUserEntity(userDTO);
        var savedAdmin = mapUserRequestToAdmin(userDTO);
        var jwtToken = jwtService.generateToken(savedAdmin);
//        var refreshToken = jwtService.generateRefreshToken(savedUser);
        saveUserToken(savedAdmin, jwtToken);
        log.info("====> {}: "+Constant.REGISTER_SUCCESS_MESSAGE ,userDTO.getUsername());
        return AllResponse.success(Constant.REGISTER_SUCCESS_MESSAGE, AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken("*****")
                .build());
    }

    private User mapUserRequestToAdmin(UserDTO userDTO) {
        var userExisting = userRepository.findByEmail(userDTO.getEmail());
        if(!ObjectUtils.isEmpty(userExisting))
            throw new ApplicationErrorException(Constant.REGISTER_FAIL_MESSAGE);
        return userRepository.save(User.builder()
                .username(userDTO.getUsername())
                .phoneNumber("********"+userDTO.getPhoneNumber()
                        .substring(9,userDTO.getPhoneNumber().length()))
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .role(Role.ADMIN)
                .createdAt(DateUtils.getNowDate())
                .build());
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    private void saveUserToken(User savedUser, String jwtToken) {
        var token = Token.builder()
                .user(savedUser)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private User mapUserRequestToUser(UserDTO userDTO) {
        var userExisting = userRepository.findByEmail(userDTO.getEmail());
        if(!ObjectUtils.isEmpty(userExisting))
            throw new ApplicationErrorException(Constant.REGISTER_FAIL_MESSAGE);
       return userRepository.save(User.builder()
                       .username(userDTO.getUsername())
                       .phoneNumber("********"+userDTO.getPhoneNumber()
                               .substring(9,userDTO.getPhoneNumber().length()))
                       .email(userDTO.getEmail())
                       .password(passwordEncoder.encode(userDTO.getPassword()))
                       .createdAt(DateUtils.getNowDate())
                       .role(Role.USER)
               .build());
    }

    private void checkUserEntity(UserDTO userDTO) {
        if(!"959".equals(userDTO.getPhoneNumber().substring(0,3)))
            throw new ApplicationErrorException(Constant.PHONE_NOT_MATCH);
    }
}
