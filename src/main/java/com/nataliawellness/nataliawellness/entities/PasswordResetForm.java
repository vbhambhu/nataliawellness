package com.nataliawellness.nataliawellness.entities;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class PasswordResetForm {


    @NotEmpty(message = "Email field is required.")
    @Email(message = "Email field is not an valid email address.")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
