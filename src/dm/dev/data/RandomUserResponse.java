
package dm.dev.data;

import java.util.List;
import lombok.Data;

import javax.ws.rs.QueryParam;

@Data
@SuppressWarnings("unused")
public class RandomUserResponse {

    private Info info;
    private List<User> results;

}
