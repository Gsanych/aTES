package inc.popug.api.gateway.dto;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserSignupDTO implements Serializable {

    private Long personId;
    @NotEmpty(message = "First name can't be null or empty")
    private String firstName;
    private String middleName;
    @NotEmpty(message = "Last name can't be null or empty")
    private String lastName;
    @NotEmpty(message = "User name can't be null or empty")
    private String userName;
    @NotEmpty(message = "Must be provide password")
    @Size(min = 8, max = 32, message = "Password must be between 2 and 32 characters long")
    private String password;
    @NotNull(message = "Must be provide date of birth")
    private Date dateOfBirth;
    @NotEmpty(message = "Must be provide email")
    private String email;
    @NotEmpty(message = "Must be provide employer")
    private String employer;

}
