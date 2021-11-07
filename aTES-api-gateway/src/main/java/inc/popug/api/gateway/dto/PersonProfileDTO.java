package inc.popug.api.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PersonProfileDTO implements Serializable {

    private Long personId;
    private String firstName;
    private String middleName;
    private String lastName;
    private Date dateOfBirth;
    private String gender;
    private String email;
}
