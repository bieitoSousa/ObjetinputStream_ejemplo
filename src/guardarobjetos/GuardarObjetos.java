/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guardarobjetos;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author bieito
 */



public class GuardarObjetos implements Serializable{
    final static String urlpath = "C:\\Users\\bieito\\Documents\\temp\\Java";
	final static String urlhistorico = urlpath+"historicoArray.dat";
	final  File fhistorico = new File (urlhistorico);
	final File fpath = new File (urlpath);
        ObjectInputStream  in = null;
        MiObjectOutputStream ou = null;
        public Historico [] h = {new Historico("a",0),new Historico("b",0),new Historico("c",0),new Historico("d",0)};
        
        public Historico [] h2 = {new Historico("a",2),new Historico("b",2),new Historico("c",2),new Historico("d",2)};
        
        public Historico [] h3 = {new Historico("a",5),new Historico("b",7),new Historico("c",20),new Historico("d",10)};
 
        
        
        
public void recorrerarray(Historico [] h){

    for(int i=0;i<h.length;i++){
   System.out.println( h[i].toString() );
    }

}        
        
    void EscribirObjetos(Historico [] h) throws FileNotFoundException, IOException, ClassNotFoundException{
        int i =0; 
        try{   
             // Primero se abre el fichero
            FileOutputStream ficli = new FileOutputStream( this.fhistorico, true );
            ObjectOutputStream flusal = null;
             
        for(i=0;i<h.length;i++){
         System.out.println("Escribo en"+fhistorico.getPath()+ " => "+ h[i].toString() );
         Historico his = h[i];

            // Si el fichero ya tenía registros
            if (tieneRegistros()){
                System.out.println("Tiene registros");
                // Uso esta redefinición de la clase para
                // evitar que escriba datos de la cabecera que corromperían
                // la posterior lectura del fichero
                flusal = new MiObjectOutputStream(ficli);
                    System.out.println("se trabaja con MiObjectOutputStream");
         }else {
                System.out.println("no tine registros");   
                flusal =  new ObjectOutputStream(ficli);
                System.out.println("se trabaja con ObjectOutputStream");
            }
            //Ahora se escribe
            if (flusal != null)
                System.out.println("intenta escribir "+ his.toString()+"   ------------- "+i+"º objeto");
                flusal.writeObject(his);
                System.out.println("escribe "+ his.toString()+"   ------------- "+i+"º objeto");
            }
            // Cerrar
            flusal.close() ;
            ficli.close() ;
            System.out.println("cierra ....");
       
        
             System.out.println("------------------------------------------------he escrito " +i +"  objetos");
         }catch (IOException ex) {
            
        }
    
    }
        
 /*       
        
ou=new MiObjectOutputStream(new FileOutputStream(this.fhistorico));
ObjectOutputStream or=new ObjectOutputStream(new FileOutputStream(this.fhistorico));


if (){}else{}

 for(int i=0;i<h.length;i++){
   System.out.println("Escribo en"+fhistorico.getPath()+ " => "+ h[i].toString() );

}
    }
*/
 Historico[]  LeerObjetos() throws FileNotFoundException, IOException, ClassNotFoundException{
     System.out.println("estoy leyendo objetos");
   int i =0; 
 Historico [] aux = new Historico[4];
    try{
    in=new ObjectInputStream(new FileInputStream(this.fhistorico));
   
                while(true){
               aux[i]=(Historico) in.readObject();
                System.out.println("Leeo "+fhistorico.getPath()+ " => "+ h[i].toString() );
                i++;
           }
    }catch (EOFException e){
        System.out.println(" se ha terminado el contenido");
        
    }finally{in.close();}
     System.out.println("--------------------------------------------------------he leido "+ i +"objetos");
   return aux;             
}

 
 
 
 public boolean tieneRegistros(){
        boolean tiene = false ;
            long dimension =this.fhistorico.length();
                if (dimension>4){
                    tiene =true;
                }
        return(tiene) ;
    }



    
    
    
    

    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
    
        GuardarObjetos go = new GuardarObjetos ();
       go.h=go.h2;
     
      
       go.EscribirObjetos(go.h3);// escrivo objetos
       System.out.println(" ---------  arry Antes--------- ");
       go.recorrerarray(go.h);
       System.out.println(" --------- fin arry Antes--------- ");
       System.out.println(" ---------arry Despues--------- ");
       go.h=go.LeerObjetos();// cambio a lo que leo
       System.out.println(" ---------Fin arry Despues--------- ");
       go.recorrerarray(go.h);
        
        
    }
    
}
