import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Objects;

public class Icons {
    private final HashMap<Character, ImageIcon> icons;

    public Icons() {
        icons = new HashMap<>();
        loadIcons();
    }

    private void loadIcons() {
        icons.put('X', createScaledIcon("/assets/x.png"));
        icons.put('O', createScaledIcon("/assets/o.png"));
    }

    private ImageIcon createScaledIcon(String path) {
        ImageIcon originalIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource(path)));
        Image image = originalIcon.getImage();
        Image scaledImage = image.getScaledInstance(Settings.iconSize, Settings.iconSize, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    public ImageIcon getIcon(char player) {
        return icons.get(player);
    }
}
