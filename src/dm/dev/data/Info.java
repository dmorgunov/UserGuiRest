
package dm.dev.data;

import lombok.Data;

@Data
@SuppressWarnings("unused")
public class Info {

    private long page;
    private long results;
    private String seed;
    private String version;

}
