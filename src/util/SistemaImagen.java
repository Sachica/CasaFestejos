/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;
import java.awt.Image;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.Icon;
import javax.swing.ImageIcon;
/**
 *
 * @author kuroy
 */
public class SistemaImagen {
    private static final String DIR = "imagenes_articulos";
    private static final String JPG = ".jpg";
    
    public static void guardarImagen(String archivo_origen, String nombre){
        nombre = nombre.replaceAll(" ", "_");
        File archivo_dir = new File(DIR+File.separator+nombre+JPG);
        if(!archivo_dir.exists()){
            SistemaImagen.crearArchivo(archivo_dir.getAbsolutePath());           
        }
        
        try{
            Files.copy(Paths.get(archivo_origen), Paths.get(archivo_dir.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);
        }catch(java.io.IOException e){
        }
    }
    
    public static Icon getImagen(String name, java.awt.Dimension dimension){
        name = name.replaceAll(" ", "_");
        String dir = DIR+File.separator+name+JPG;
        if(new File(dir).exists()){
            Icon i = new ImageIcon(new ImageIcon(dir).getImage().getScaledInstance((int)dimension.getWidth()+20, (int)dimension.getHeight(), Image.SCALE_DEFAULT));
            return i;
        }
         return null;
    }
    
    public static void eliminarImagen(String name){
        name = name.replaceAll(" ", "_");
        new File(DIR+File.separator+name+JPG).delete();
    }
    
    public static void actualizarImagenes(){
        java.util.ArrayList<modelo.Articulo> articulos;
        try{
            articulos = modeloDAO.ArticuloAdminDAO.getAll(controlador.Controlador.getConnection());
            File file = new File(DIR);
            Integer i = 0;
            for (String archivo : file.list()) {
                for(modelo.Articulo articulo : articulos){
                    String nombreArchivo = archivo.replaceAll("_", " ").split("\\.")[0];
                    if(nombreArchivo.equals(articulo.getNombre())){
                        i++;
                    }
                }
                if(i==0){
                    SistemaImagen.eliminarImagen(archivo.split("\\.")[0]);
                }
                i=0;
            }
        }catch(java.sql.SQLException e){
        }
    }
    
    private static Boolean crearArchivo(String dir){
        try{
            return new File(dir).createNewFile();
        }catch(java.io.IOException e){
        }
        return false;
    }
}
