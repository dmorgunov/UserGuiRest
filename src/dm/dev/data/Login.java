
package dm.dev.data;

import lombok.Data;

@Data
@SuppressWarnings("unused")
public class Login {

    private String md5;
    private String password;
    private String salt;
    private String sha1;
    private String sha256;
    private String username;
    private String uuid;

}
