package com.theIronYard.bean;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by davehochstrasser on 9/28/16.
 */
public class Login {
    @NotBlank
    public String email;

    @NotBlank
    public String password;

    public Boolean loginFailed = false;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public Boolean isLoginFailed() {
        return loginFailed;
    }

    public void setLoginFailed(Boolean loginFailed) {
        this.loginFailed = loginFailed;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }


}
