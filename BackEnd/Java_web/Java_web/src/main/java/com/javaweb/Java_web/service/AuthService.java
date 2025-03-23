package com.javaweb.Java_web.service;

import com.javaweb.Java_web.dto.request.AccountAdminRequest;
import com.javaweb.Java_web.dto.request.AuthRequest;
import com.javaweb.Java_web.dto.request.IntrospectRequest;
import com.javaweb.Java_web.dto.response.AuthResponse;
import com.javaweb.Java_web.dto.response.IntrospectResponse;
import com.javaweb.Java_web.entity.AccountAdmin;
import com.javaweb.Java_web.repository.AccountAdminRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Date;
import java.util.StringJoiner;


@Service
public class AuthService {
    @Autowired
    private AccountAdminRepository accountAdminRepository ;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY  ;


    private String generateToken (AccountAdmin accountAdmin) {

        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512) ;
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(accountAdmin.getLoginName())
                .issuer("coffee_management.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .claim("scope" , buildScope(accountAdmin) )
                .build() ;

        Payload payload = new Payload(jwtClaimsSet.toJSONObject()) ;

        JWSObject jwsObject = new JWSObject(jwsHeader , payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize() ;
        } catch (JOSEException e) {
//            log.error("Cannot create token" , e) ;
            throw new RuntimeException(e);
        }
    }

    public AuthResponse login(AuthRequest authRequest) {
        AccountAdmin accountAdmin = accountAdminRepository.findByLoginNameAndDeletedFalse(authRequest.getLoginName()).orElseThrow(() -> new RuntimeException("Not found"));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10) ;
        boolean result =  passwordEncoder.matches(authRequest.getPassword(),accountAdmin.getPassword() ) ;
        if (!result)
            throw new RuntimeException("No") ;

        String token = generateToken(accountAdmin) ;
        return AuthResponse.builder()
                .token(token)
                .authenticated(true)
                .build() ;
    }

    public IntrospectResponse introspect (IntrospectRequest introspectRequest) throws JOSEException, ParseException {
        String token = introspectRequest.getToken() ;

        JWSVerifier jwsVerifier = new MACVerifier(SIGNER_KEY.getBytes()) ;
        SignedJWT signedJWT = SignedJWT.parse(token) ;

        Date expTime = signedJWT.getJWTClaimsSet().getExpirationTime() ;
        boolean verified = signedJWT.verify(jwsVerifier) ;
        return IntrospectResponse.builder()
                .valid(verified && expTime.after((new Date())))
                .build();
    }

    private String buildScope(AccountAdmin accountAdmin) {
        StringJoiner stringJoiner = new StringJoiner(" ") ;
        if (!CollectionUtils.isEmpty(accountAdmin.getRoles())){
            accountAdmin.getRoles().forEach(stringJoiner::add);
        }
        return stringJoiner.toString() ;
    }


}
