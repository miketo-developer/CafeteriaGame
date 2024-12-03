package util;

/**
 *
 * @author Miketo
 */
public class Strings {
    public static final String green = "green";
    public static final String red = "red";
    public static final String blue = "blue";
    public static final String purple = "purple";
    public static final String yellow = "yellow";
    
    public static final int EXPRESO = 0;
    public static final int DOBLE = 1;
    public static final int AMERICANO = 2;
    
    private static final String[] iconPathsTypeOff = {
        "/imgs/" + blue + "_button_off.png",
        "/imgs/" + purple + "_button_off.png",
        "/imgs/" + yellow + "_button_off.png"
    };
    public static final String[] iconPathsTypeOn = {
        "/imgs/" + blue + "_button_on.png",
        "/imgs/" + purple + "_button_on.png",
        "/imgs/" + yellow + "_button_on.png"
    };
    private static final String[] iconPathsOff = {
        "/imgs/" + green + "_button_off.png",
        "/imgs/" + red + "_button_off.png"
    };
    private static final String[] iconPathsOn = {
        "/imgs/" + green + "_button_on.png",
        "/imgs/" + red + "_button_on.png"
    };
    
    public static final String touchButtons = "Toca los botones de colores para manipular la cafetera";

    public static final boolean ON = true;
    public static final boolean OFF = false;
    
    /**
     * Método para obtener el icono correspondiente según el color y el estado.
     * 
     * @param color El color del botón (ej: "blue", "purple", "yellow", "green", "red").
     * @param isOn  Si el botón está encendido (Strings.ON) o apagado (Strings.OFF).
     * @return La ruta del icono correspondiente, o null si no se encuentra.
     */
    public static String getIconPath(String color, boolean isOn) {
        String[] paths = isOn ? iconPathsTypeOn : iconPathsTypeOff;
        String[] additionalPaths = isOn ? iconPathsOn : iconPathsOff;

        for (String path : paths) {
            if (path.contains(color)) {
                return path;
            }
        }

        for (String path : additionalPaths) {
            if (path.contains(color)) {
                return path;
            }
        }

        return null; // Si no se encuentra el color.
    }
}






    