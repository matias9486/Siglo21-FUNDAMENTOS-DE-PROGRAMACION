import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class Actividad1 {
    public static boolean comprobarTextoVacio(String texto){
        return (texto == null || texto.isEmpty() || texto.trim().isEmpty());
    }
    public static boolean validarDni(String numero) {
        String regex = "^\\d{8}$";
        return numero.matches(regex);
    }
    public static boolean validarFecha(String fecha) {
        String regex = "^(0[1-9]|[12][0-9]|3[01])\\/(0[1-9]|1[0-2])\\/(19|20)\\d\\d$";
        return fecha.matches(regex);
    }
    public static void main(String[] args) {
        RegistroPersonas registroPersonas = new RegistroPersonas();

        int opcion = 0;

        do {
            try {
                opcion = Integer.parseInt(JOptionPane.showInputDialog(null,"Menu\n" +
                        "1. Agregar Persona\n"+
                        "2. Mostrar Personas registradas\n"+
                        "3. Salir\n"+
                        "\nElige una opcion","Super Registro de Personas 2023", JOptionPane.INFORMATION_MESSAGE));

                switch (opcion) {
                    case 1:
                        //Pedir datos y validar datos
                        String nombre = JOptionPane.showInputDialog(null,"Ingrese nombre:", "Agregar persona", JOptionPane.INFORMATION_MESSAGE);
                        if(comprobarTextoVacio(nombre))
                            throw new IllegalArgumentException("Ingrese nombre");

                        String apellido = JOptionPane.showInputDialog(null,"Ingrese apellido:", "Agregar persona", JOptionPane.INFORMATION_MESSAGE);
                        if(comprobarTextoVacio(apellido))
                            throw new IllegalArgumentException("Ingrese apellido");

                        String dni = JOptionPane.showInputDialog(null,"Ingrese DNI(8 numeros):", "Agregar persona", JOptionPane.INFORMATION_MESSAGE);
                        if(comprobarTextoVacio(dni))
                            throw new IllegalArgumentException("Ingrese DNI");
                        if(!validarDni(dni))
                            throw new IllegalArgumentException("Ingrese 8 digitos para DNI");

                        String fechaNacimiento = JOptionPane.showInputDialog(null,"Ingrese fecha con formato(09/04/1986): ", "Agregar persona", JOptionPane.INFORMATION_MESSAGE);
                        if(comprobarTextoVacio(fechaNacimiento))
                            throw new IllegalArgumentException("Ingrese fecha");
                        if(!validarFecha(fechaNacimiento))
                            throw new IllegalArgumentException("Ingrese fecha con el formato correspondiente");

                        //Instanciar fecha, persona y registrarla
                        LocalDate fecha = LocalDate.parse(fechaNacimiento, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        Persona nueva = new Persona(nombre, apellido, dni, fecha);
                        registroPersonas.agregarPersona(nueva);
                        JOptionPane.showMessageDialog(null,"Persona registrada con exito","Registrar Persona", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case 2:
                        String stringPersonas= registroPersonas.getPersonas().stream().map(persona -> persona.toString()).collect(Collectors.joining("\n"));
                        String msj = stringPersonas.isEmpty()? "No se registraron personas." : stringPersonas;
                        JOptionPane.showMessageDialog(null, msj,"Personas registradas", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case 3:
                        JOptionPane.showMessageDialog(null, "Gracias por usar Super Registro de Personas 2023 :)", "Información",JOptionPane.INFORMATION_MESSAGE);
                        break;
                    default:
                        throw new IllegalArgumentException("Opción incorrecta.");
                }
            } catch (IllegalArgumentException | NullPointerException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Se ha producido un error", JOptionPane.ERROR_MESSAGE);
            }
        } while (opcion!= 3);

    }
}
