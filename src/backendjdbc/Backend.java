/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backendjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 46989075y
 */
public class Backend {

    private Connection conexion;

    public Backend() {

    }

    //Metodo para meter equipo
    public void insertarEquipo(Equipo c) throws SQLException {
conectar();
        String insert = "insert into equipo values (?, ?, ?);";
        PreparedStatement ps = conexion.prepareStatement(insert);
        ps.setString(1, c.getName());
        ps.setString(2, c.getCity());
        ps.setDate(3, java.sql.Date.valueOf(c.getCreation()));
        ps.executeUpdate();
        ps.close();
        desconectar();
    }

    public void insertarJugador(Jugador p) throws SQLException {
        String insert = "insert into Player values (?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement ps = conexion.prepareStatement(insert);
        ps.setString(1, p.getName());
        ps.setDate(2, java.sql.Date.valueOf(p.getBirth()));
        ps.setInt(3, p.getNbaskets());
        ps.setInt(4, p.getNassists());
        ps.setInt(5, p.getNrebounds());
        ps.setString(6, p.getPosition());
        ps.setString(7, p.getTeam());
        ps.executeUpdate();
        ps.close();
    }

    public void updateCanAsisReb(Jugador e) throws SQLException {

        String update = ("UPDATE player SET nassists=?, nbaskets=?,nrebounds=? WHERE name=?");
        PreparedStatement ps = conexion.prepareStatement(update);
        ps.setInt(1,e.getNassists());
        ps.setInt(2,e.getNbaskets());
        ps.setInt(3,e.getNrebounds());
        ps.setString(4, e.getName());
        ps.executeUpdate();
        ps.close();
    }
    
    public void modificarEquipoJugador (Jugador j, Equipo e) throws SQLException{
        String update = ("UPDATE SET team=? where jugador=?");
        PreparedStatement ps= conexion.prepareStatement(update);
        ps.executeUpdate();
        ps.close();
    }
    public void eliminarJugador(Jugador j) throws SQLException{
        String delete = ("DELETE FROM player WHERE name=? ");
          PreparedStatement ps = conexion.prepareStatement(delete);
        ps.setString(1, j.getName());
        ps.executeUpdate();
        ps.close();
    }
public Jugador obtenerJugadorNombre (String name)throws SQLException{
        Jugador c = new Jugador();
        conectar();
        String query = ("SELECT * FROM player WHERE name=?");
          PreparedStatement ps = conexion.prepareStatement(query);
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        
        if(rs.next()){
            
            c.setName(rs.getString("name"));
            c.setBirth(LocalDate.MIN);
            c.setNassists(rs.getInt("nassists"));
             c.setNbaskets(rs.getInt("nbaskets"));
              c.setNrebounds(rs.getInt("nrebounds"));
              c.setPosition(rs.getString("position"));
              c.setTeam(rs.getString("equipo"));
              
        }
        rs.close();
        ps.close();
        return c;
    }


public List<Jugador> selectAllJugadoresByNombre(String name) throws SQLException{
     List<Jugador> jugadores = new ArrayList<>();
    String query = ("SELECT * from jugador WHERE name like '%'+name+'%'");
    PreparedStatement ps= conexion.prepareStatement(query);
    ResultSet rs = ps.executeQuery(query);
   
    while(rs.next()){
        Jugador c = new Jugador();
          c.setName(rs.getString("name"));
            c.setBirth(LocalDate.MIN);
            c.setNassists(rs.getInt("nassists"));
             c.setNbaskets(rs.getInt("nbaskets"));
              c.setNrebounds(rs.getInt("nrebounds"));
              c.setPosition(rs.getString("position"));
              jugadores.add(c);
    }
    rs.close();
    ps.close();
    return jugadores;
}

public List<Jugador> selectAllJugadoresByCanastasParam(int canastas) throws SQLException{
    List<Jugador> jugadores = new ArrayList<>();
    String query = ("SELECT * from player WHERE nbaskets >= canastas ORDER BY nassists DESC");
   PreparedStatement ps= conexion.prepareStatement(query);
    ResultSet rs = ps.executeQuery(query);
    
    while(rs.next()){
        Jugador c = new Jugador();
          c.setName(rs.getString("name"));
            c.setBirth(LocalDate.MIN);
            c.setNassists(rs.getInt("nassists"));
             c.setNbaskets(rs.getInt("nbaskets"));
              c.setNrebounds(rs.getInt("nrebounds"));
              c.setPosition(rs.getString("position"));
              c.setTeam(rs.getString("equipo"));
              
    }
    rs.close();
    ps.close();
    return jugadores;
}
public List<Jugador> selectJugadorAsistenciasBetween()throws SQLException{
     List<Jugador> jugadores = new ArrayList<>();
     String query = ("SELECT * FROM player WHERE nassists between =? and =?");
  PreparedStatement ps= conexion.prepareStatement(query);
    ResultSet rs = ps.executeQuery(query);
while(rs.next()){
        Jugador c = new Jugador();
          c.setName(rs.getString("name"));
            c.setBirth(LocalDate.MIN);
            c.setNassists(rs.getInt("nassists"));
             c.setNbaskets(rs.getInt("nbaskets"));
              c.setNrebounds(rs.getInt("nrebounds"));
              c.setPosition(rs.getString("position"));
              c.setTeam(rs.getString("equipo"));
              
    }
rs.close();
    ps.close();
    return jugadores;
}
public List<Jugador> selectJugadorByPosition(String position)throws SQLException{
    List<Jugador> jugadores = new ArrayList<>();
     String query = ("SELECT * FROM player WHERE position like '%'+posicion+'%'");
 PreparedStatement ps= conexion.prepareStatement(query);
    ResultSet rs = ps.executeQuery(query);
while(rs.next()){
        Jugador c = new Jugador();
          c.setName(rs.getString("name"));
            c.setBirth(LocalDate.MIN);
            c.setNassists(rs.getInt("nassists"));
             c.setNbaskets(rs.getInt("nbaskets"));
              c.setNrebounds(rs.getInt("nrebounds"));
              c.setPosition(rs.getString("position"));
              c.setTeam(rs.getString("equipo"));
              
    }
rs.close();
    ps.close();
    return jugadores;
}

public List<Jugador> selectJugadorFecha (LocalDate birth)throws SQLException{
     List<Jugador> jugadores = new ArrayList<>();
     String query = ("SELECT * FROM player WHERE birth <=?");
 PreparedStatement ps= conexion.prepareStatement(query);
    ResultSet rs = ps.executeQuery(query);
while(rs.next()){
        Jugador c = new Jugador();
          c.setName(rs.getString("name"));
            c.setBirth(LocalDate.MIN);
            c.setNassists(rs.getInt("nassists"));
             c.setNbaskets(rs.getInt("nbaskets"));
              c.setNrebounds(rs.getInt("nrebounds"));
              c.setPosition(rs.getString("position"));
              c.setTeam(rs.getString("equipo"));
              
    }
rs.close();
    ps.close();
    return jugadores;
}
public List<Jugador> selectJugadorGroupBy (String poition)throws SQLException{
     List<Jugador> jugadores = new ArrayList<>();
     conectar();
     String query = ("SELECT poition, AVG(nbaskets),AVG(nassists),AVG(nrebounds),MAX(nbaskets), MAX(nassists), MAX(nrebounds),MIN(nbaskets),MIN(nassists),MIN(nrebounds)");
 PreparedStatement ps= conexion.prepareStatement(query);
    ResultSet rs = ps.executeQuery(query);
while(rs.next()){
        Jugador c = new Jugador();
          c.setName(rs.getString("name"));
            c.setBirth(LocalDate.MIN);
            c.setNassists(rs.getInt("nassists"));
             c.setNbaskets(rs.getInt("nbaskets"));
              c.setNrebounds(rs.getInt("nrebounds"));
              c.setPosition(rs.getString("position"));
              c.setTeam(rs.getString("equipo"));
              
    }
rs.close();
    ps.close();
    return jugadores;
}
public List<Jugador> ListJugadorCanastas() throws SQLException{
       List<Jugador> jugadores = new ArrayList<>();
     String query = ("SELECT name, nbaskets FROM player order by nbaskets desc");
 Statement ps= conexion.createStatement();
    ResultSet rs = ps.executeQuery(query);
while(rs.next()){
        Jugador c = new Jugador();
          c.setName(rs.getString("name"));
         c.setNbaskets(rs.getInt("nbaskets"));
         jugadores.add(c);
             
    }
rs.close();
    ps.close();
    return jugadores;
}

public List<Equipo> ListEquipoCity() throws SQLException{
       List<Equipo> equipos = new ArrayList<>();
     String query = ("SELECT name, city, creation FROM team order by city");
 Statement ps= conexion.createStatement();
    ResultSet rs = ps.executeQuery(query);
while(rs.next()){
        Equipo c = new Equipo();
          c.setName(rs.getString("name"));
         c.setCity(rs.getString("city"));
       c.setCreation(LocalDate.MIN);
         equipos.add(c);
             
    }
rs.close();
    ps.close();
    return equipos;
}

public List<Jugador> ListPlayerTeam()throws SQLException{
    List<Jugador> jugadores = new ArrayList<>();
    String query = ("SELECT * FROM player group by Equipo");
    Statement ps= conexion.createStatement();
    ResultSet rs = ps.executeQuery(query);
    while(rs.next()){
        Jugador c = new Jugador();
        c.setName(rs.getString("name"));
            c.setBirth(LocalDate.MIN);
            c.setNassists(rs.getInt("nassists"));
             c.setNbaskets(rs.getInt("nbaskets"));
              c.setNrebounds(rs.getInt("nrebounds"));
              c.setPosition(rs.getString("position"));
              c.setTeam(rs.getString("equipo"));
         jugadores.add(c);
             
    }
rs.close();
    ps.close();
    return jugadores;
}

    public void conectar() throws SQLException {
        String url = "jdbc:mysql://localhost/basket";
        String usr = "root";
        String pass = "";
        conexion = DriverManager.getConnection(url, usr, pass);
    }

    public void desconectar() throws SQLException {
        if (conexion != null) {
            conexion.close();
        }
    }
}
