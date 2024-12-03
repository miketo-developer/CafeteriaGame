package test;

/**
 *
 * @author Miketo
 */
public class Nieto extends Hijo {
    private String saludoHijo;

    public Nieto() {
        saludoHijo = "UwU";
    }
    
    public void printAtributos() {
        System.out.println("this = " + this.getSaludoDeAbuelo());
        System.out.println("this = " + this.getSaludoHijo());
        System.out.println("this = " + this.saludoHijo);
    }
}
