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
}
