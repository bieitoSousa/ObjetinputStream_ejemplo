/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guardarobjetos;

import java.io.Serializable;

/**
 *
 * @author bieito
 */
public class Historico  implements Serializable{
   
        public String  codigo ;
        public int  historico ;

            Historico (String  codigo,int  historico){
            this.codigo=codigo;
            this.historico=historico;
            }

            @Override
            public String toString(){
            return "Codigo : " + codigo + "  cantidad " + historico ;
            }


} 




