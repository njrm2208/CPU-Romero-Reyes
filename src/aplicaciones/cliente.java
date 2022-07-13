package aplicaciones;

import interfacesRmi.objCoordinador;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class cliente {

    public static void main(String[] args) {

        int segundos, i = 1;
        String ip = "localhost";

        //INGRESAR IP DEL SERVIDOR
        System.out.print("Ingrese la ip del servidor: ");
        Scanner leerIP = new Scanner(System.in);
        ip = leerIP.nextLine();

        try {
            //REGISTRO DE LAS FUNCIONES
            Registry registry = LocateRegistry.getRegistry(ip, 1099);
            objCoordinador miCoordinador = (objCoordinador) registry.lookup("miCoordinador");

            //VERIFICAR CUANTOS MONITORES HAY FUNCIONANDO
            System.out.println("Monitores activos: " + miCoordinador.iniClient());

            if (miCoordinador.iniClient() > 0) {

                //OBTENER EL INTERVALO DE MEDICION
                System.out.print("Intervalo:");
                Scanner leer = new Scanner(System.in);
                segundos = leer.nextInt();

                while (true) {
                    //DEVUELVE EL VALOR DE LOADVG
                    System.out.println(i + ": " + miCoordinador.getLoadAvg());
                    i++;

                    //ESPERAMOS PARA VOLVER A HACER LA CONSULTA
                    Thread.sleep(segundos * 1000);
                }
            } else {
                System.out.println("Saliendo, por causa de monitores!");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}
