package com.Mahdi.hadiji.Controller;

//	Représente le corps de la requête POST (cin + password)

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    private String email;  // Email au lieu du CIN
    private String password;
}
