
package dm.dev.data;

import lombok.Data;

@Data
@SuppressWarnings("unused")
public class Location {

    private String city;
    private Coordinates coordinates;
    private String country;
    private String postcode;
    private String state;
    private Street street;
    private Timezone timezone;

}
