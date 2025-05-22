package com.Mahdi.hadiji.Controller;

import lombok.*;

//Réponse contenant le token + le role
@Getter
@Setter
@NoArgsConstructor@AllArgsConstructor
public class LoginResponse {
    private String token;
    private String role;


}
