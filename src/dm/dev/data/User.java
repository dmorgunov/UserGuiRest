
package dm.dev.data;

import lombok.Data;

@Data
@SuppressWarnings("unused")
public class User {

    private String cell;
    private Dob dob;
    private String email;
    private String gender;
    private Id id;
    private Location location;
    private Login login;
    private Name name;
    private String nat;
    private String phone;
    private Picture picture;
    private Registered registered;

}
