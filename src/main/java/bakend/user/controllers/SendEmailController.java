package bakend.user.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import bakend.user.domain.Authenticate;
import bakend.user.models.LoginRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@PreAuthorize("permitAll()")

@RestController
public class SendEmailController {
    @Autowired
    Authenticate authenticate;

    @PostMapping("/envio")
    public ResponseEntity<?> postMethodName(@RequestBody LoginRequest loginRequest) {

        authenticate.sendMessegeUser(loginRequest.getEmailuser(), loginRequest.getMessage());
        Map<String, String> response = new HashMap<>();
        response.put("mensagge", "enviado con exito");
        response.put("status", "ok");
        return ResponseEntity.ok(response);    }

}
