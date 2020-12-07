package hacker.security;

import hacker.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RegistrationForm {

    @NotNull
    @Size(min = 5, message = "Username must have at least 5 characters")
    private String username;

    @NotNull
    @Size(min = 6, message = "Password must have at least 6 characters")
    private String password;

    @NotNull
    @NotEmpty(message = "Name is required")
    private String fullName;

    @NotNull
    @NotEmpty(message = "Must input job role (If student please input Student)")
    private String jobRole;

    @NotNull
    @NotEmpty(message = "Please provide LinkedIn URL (Enter N/A if not applicable)")
    private String linkedInURL;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(username, passwordEncoder.encode(password), fullName, jobRole, linkedInURL);
    }

    public static User updateUser(User updateUser, PasswordEncoder passwordEncoder, RegistrationForm registrationForm) {
        updateUser.setFullName(registrationForm.getFullName());
        updateUser.setJobRole(registrationForm.getJobRole());
        updateUser.setLinkedInURL(registrationForm.getLinkedInURL());
        updateUser.setPassword(passwordEncoder.encode(registrationForm.getPassword()));

        return updateUser;
    }

}
