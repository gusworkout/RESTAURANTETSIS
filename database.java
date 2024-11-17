//LIBRERIAS
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import static javax.swing.UIManager.get;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//CONEXION
PreparedStatement ps;
    ResultSet rs;
    
    Connection conexion;
    String puerto = "3306";
    String usuario = "root";
    String contra = "";
    String ip = "localhost";
    
    String database = "comidabd";
    String url="jdbc:mysql://"+ip+":"+puerto+"/"+database;
    
    public Connection CrearConexion(){
    String cadena = "com.mysql.cj.jdbc.Driver";
    
    try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        conexion = DriverManager.getConnection(url,usuario,contra);
    }catch( Exception e ){
        JOptionPane.showMessageDialog(null, "error al conectarse a la base de datos!" + e.toString());
     }
    return conexion;
    }

//INICIAR SESION

private void BotonIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        // TODO add your handling code here:
        
        String usuarioCorrecto = Cedula.getText();
        String contrasenaCorrecta = Contra.getText();       
        
    try {
        CrearConexion();
        String contrasenaBd = null;
        String cedulaBd = null;
        String nombre = null;
        ps = CrearConexion().prepareStatement("SELECT cedula, contrasena, nombre FROM usuario WHERE cedula=?");
        ps.setString(1, usuarioCorrecto);
        rs = ps.executeQuery();
        
        if(rs.next()){
         contrasenaBd = rs.getString(2); 
         cedulaBd = rs.getString(1);
       
        }
        JOptionPane.showMessageDialog(null, "INICIASTE SESION DE MANERA CORRECTA");
        
        if(contrasenaCorrecta.equals(contrasenaBd) && usuarioCorrecto.equals(cedulaBd)){
            uiPrincipal  b2 = new uiPrincipal();
            this.hide();
            b2.show();
        } else {
        JOptionPane.showMessageDialog(null, "usuario o contraseña incorrectos");
        }
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, "Error"+ e.toString());
    }
        
    } 
