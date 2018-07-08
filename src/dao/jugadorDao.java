/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Jugador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author intel
 */
public class jugadorDao extends BaseDAO<Jugador>{

    public jugadorDao(){
        table =new TableData(
                "jugador",
                "idJugador",
                new String[]{"nombre","cartera"});
    }
    @Override
    Jugador mapToObject(ResultSet resultSet) {
        Jugador j = new Jugador();
        try{
            j.setIdJugadr(resultSet.getInt(table.PRIMARY_KEY));
            j.setNombre(resultSet.getString(table.fields[0]));
            j.setCartera(resultSet.getInt(table.fields[1]));
            }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return j;
    }

    @Override
    PreparedStatement getSelectStatement(Connection con, Jugador find, String by) {
        String query = "SELECT * FROM "+table.TABLE_NAME+" WHERE "+by+" = ?";
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = con.prepareStatement(query);
            if (by.equals(table.PRIMARY_KEY))
                preparedStatement.setInt(1, find.getIdJugador());
            if (by.equals(table.fields[0]))
                preparedStatement.setString(1, find.getNombre());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return preparedStatement;
    }

    @Override
    PreparedStatement getInsertStatement(Connection con, Jugador _new) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    PreparedStatement getDeleteStatement(Connection con, Jugador deleteObject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
