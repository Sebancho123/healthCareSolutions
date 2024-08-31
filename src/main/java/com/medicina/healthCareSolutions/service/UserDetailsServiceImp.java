package com.medicina.healthCareSolutions.service;

import com.medicina.healthCareSolutions.dto.AuthLoginRequestDTO;
import com.medicina.healthCareSolutions.dto.AuthResponseDTO;
import com.medicina.healthCareSolutions.model.UserSec;
import com.medicina.healthCareSolutions.repository.IUserSecRepository;
import com.medicina.healthCareSolutions.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private IUserSecRepository iUserRepo;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //tenemos el user y necesitamos devolverlo en format userdetails
        //traer nuestro usu de la db
        UserSec userSec = iUserRepo.findUserEntityByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("el usuario" + username + " no fue encontrado"));


        //creamos una lista para los permisos
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        //traer los roles y convertirlos en simpleGrantedAuthority
        userSec.getRoleList()
                .forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRole()))));

        //traer los permisos y convertirlos en simpleGrandtedAuthority
        userSec.getRoleList().stream()
                .flatMap(role -> role.getPermissionList().stream())
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getPermissionName())));


        return new User(
                userSec.getUsername(),
                userSec.getPassword(),
                userSec.isEnabled(),
                userSec.isAccountNotExpired(),
                userSec.isAccountNotLocked(),
                userSec.isCredentialNotExpired(),
                authorityList
        );

    }

    public AuthResponseDTO loginUser(AuthLoginRequestDTO userRequest) {

        //recuperar username y contra
        String username = userRequest.username();
        String password = userRequest.password();

        Authentication authentication = this.authenticate(username, password);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accesstoken = jwtUtils.createToken(authentication);

        return new AuthResponseDTO(username, "Login successful", accesstoken, true);

    }

    private Authentication authenticate(String username, String password) {

        UserDetails userDetails = this.loadUserByUsername(username);

        if(userDetails == null) {
            throw new BadCredentialsException("Invalid username or password");
        }

        if(!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid username or password");
        }

        return new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(),userDetails.getAuthorities());

    }
}
