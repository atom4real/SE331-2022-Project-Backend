package Project.security.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import Project.security.entity.JwtUser;
import Project.security.entity.User;
import Project.security.service.UserService;
import Project.util.JwtTokenUtil;
import Project.util.ProjectMapper;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AuthenticationRestController {

    @Value("${jwt.header}")
    String tokenHeader;
    final AuthenticationManager authenticationManager;
    final JwtTokenUtil jwtTokenUtil;
    final UserDetailsService userDetailsService;
    final UserService userService;

    @PostMapping("${jwt.route.authentication.path}")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        User user = userService.findByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails, user.getAuthorities(),user.getId());
        Map result = new HashMap();
        result.put("token", token);
        return ResponseEntity.ok(result);
    }


    @GetMapping("${jwt.route.authentication.refresh}")
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);

        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("credential")
    public ResponseEntity<?> getCredential(HttpServletRequest request) {
        log.info("======called credential======");
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        User user = userService.findByUsername(username);
        log.info("user: " + user.getId());
        log.info("======end credential=========");
        return ResponseEntity.ok(ProjectMapper.INSTANCE.getUserDto(user));
    }

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody User registered) {
        User user = userService.addUser(registered);
        return ResponseEntity.ok(ProjectMapper.INSTANCE.getUserDto(user));
    }
}
