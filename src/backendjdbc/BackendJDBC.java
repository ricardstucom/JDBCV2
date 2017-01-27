
package backendjdbc;

import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BackendJDBC {

   
    public static void main(String[] args) {
       Backend gestor = new Backend();
        try{
        gestor.conectar();
            System.out.println("Estableciendo la conexión con el servidor...");
            Equipo c = new Equipo("Karlos", "barcelona", LocalDate.of(1996,8,20));
            System.out.println("Insertando Equipo...");
            gestor.insertarEquipo(c);
            System.out.println("Equipo dado de alta");
            
            
            
//            System.out.println("Insertando Jugador...");
//             Jugador p = new Jugador("Patatas",  LocalDate.of(1996,8,20),4, 3,8,"pichichi", "europa");
//             gestor.insertarJugador(p);
//             System.out.println("Jugador dado de alta");
//             
//             
//             System.out.println("Update Jugador...");
//           Jugador e = new Jugador ("petu", LocalDate.of(1995,5,20),6,3,7,"defensa","europa");
//            gestor.updateCanAsisReb(e);
//            System.out.println("Jugador actualizado");
//            
//            
//            System.out.println("Borrando Jugador...");
//            Jugador f = new Jugador("Patatas", LocalDate.of(1996,8,20),4,3,8,"pichichi","europa");
//            gestor.eliminarJugador(f);
//            System.out.println("Jugador borrado");
//            
//            System.out.println("Buscando Jugador...");
//            String name = "Patatas";
//            gestor.obtenerJugadorNombre(name);
//            System.out.println("Jugador encontrado");
            
            
            System.out.println("List Jugador Canastas");
            int contador=1;
            List<Jugador>Resultado=new ArrayList<>();
            Resultado=gestor.ListJugadorCanastas();
            for(int i=0;i<Resultado.size();i++){
                
                System.out.println(contador+"-"+Resultado.get(i));
                contador++;
            }
            
            
            
             
            System.out.println("List Equipos City");
            contador=1;
            List<Equipo>ResultadoEquipos=new ArrayList<>();
            ResultadoEquipos=gestor.ListEquipoCity();
            for(int i=0;i<ResultadoEquipos.size();i++){
                
                System.out.println(contador+"-"+ResultadoEquipos.get(i));
                contador++;
            }
            
            
            System.out.println("List Player Team");
            contador=1;
            Resultado=new ArrayList<>();
            Resultado=gestor.ListPlayerTeam();
            for(int i=0;i<Resultado.size();i++){
                
                System.out.println(contador+"-"+Resultado.get(i));
                contador++;
            }
            
            gestor.desconectar();
        }catch(SQLException ex){
            System.out.println("Error con la bbdd: "+ex.getMessage());
        }
        
        
        
          
    }
    
}
 