package com.Mahdi.hadiji.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
// he4i t7otna na3mlo hachage le MDP 9bal matetsab fel BD
// el classe he4i bech t5alina nejmo na3rfo el mot de pass yeli da5elneha fel front hia bidha hacher fel BD wala 

@Configuration
class AppConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
