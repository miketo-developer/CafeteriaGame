package util;

/**
 *
 * @author Miketo
 */
public class Utilities {
    
    /**
     * Modifica el ancho de la línea del texto a un tamaño definido 
     * dividiéndolo en varias líneas.
     * 
     * @param cad El texto a dividir
     * @param tam El ancho de la línea
     * @return El texto dividido en varias líneas.
     */
    public static String setCad(String cad, int tam) {
        int lastIndex = 0;
        int index;
        StringBuilder cadMod = new StringBuilder();
        
        do {
            index = lastIndex + tam;

            if (index < cad.length()) {
                if (Character.isAlphabetic(cad.charAt(index))) {
                    index = cad.substring(0, index).lastIndexOf(" ");
                }
                
                cadMod.append(cad.substring(lastIndex, index)).append("\n");
            } else {
                cadMod.append(cad.substring(lastIndex, cad.length())).append("\n");
            }

            lastIndex = index + 1;
        } while (index < cad.length());
        
        return cadMod.toString();
    }
}
