package dm.dev.helper;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;

public class PhotoHelper {

    public static ImageView getImageView(String imgUrl) {
        return ImageViewBuilder.create()
                .image(new Image(imgUrl))
                .build();
    }
}
