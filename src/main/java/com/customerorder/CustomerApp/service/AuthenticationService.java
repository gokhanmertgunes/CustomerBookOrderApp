package com.customerorder.CustomerApp.service;

import com.customerorder.CustomerApp.authentication.AuthenticationRequest;
import com.customerorder.CustomerApp.authentication.AuthenticationResponse;
import com.customerorder.CustomerApp.authentication.RegisterRequest;
import com.customerorder.CustomerApp.model.Customer;
import com.customerorder.CustomerApp.model.Role;
import com.customerorder.CustomerApp.repository.ICustomerRepository;
import com.customerorder.CustomerApp.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService
{
    private final ICustomerRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request)
    {
        var customer = Customer.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        repository.save(customer);
        var jwtToken = jwtService.generateToken(customer);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request)
    {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var customer = repository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(customer);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}







