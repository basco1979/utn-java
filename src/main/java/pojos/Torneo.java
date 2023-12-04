/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import com.mycompany.tp.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Sebas
 */
public abstract class Torneo {
      SessionFactory factory;
    private Session session;
    Transaction trx;
    public void start() 
        {
            factory = HibernateUtil.getSessionFactory();
            session = factory.openSession();
            trx = session.getTransaction();
            trx.begin();              
	}
        
        public void stop()
        {
            session.getTransaction().commit();
            session.close();
        }
        
public void agregarEquipo(String nombre, int titulares, int suplentes, String director){
    try {
			
			Equipo eq = new Equipo();
			eq.setNombre(nombre);
			eq.setTitulares(titulares);
			eq.setSuplentes(suplentes);
			eq.setDirectorTecnico(director);
                        eq.setPuntos(0);
                        eq.setPartidosJugados(0);
                        start();
                        int res = (int)session.save(eq);
			System.out.println("Equipo Agregado con ÉXITO bajo el id: "+res);
			stop();
		}catch(Exception e) {
                    
                    System.err.println("error: ");
                    e.printStackTrace();
		}
}

public void verEquipo(int id) 
        {
		Equipo eq = buscarEquipoPorId(id);                
		if (eq == null)
                {
                    System.out.println("id no encontrado");
                }
                else
                {                    
                    System.out.println("El Nombre de equipo es:\n"+eq.getNombre());                                                    
                    System.out.println("La Cantidad De Titulares es:\n"+eq.getTitulares());                                                    
                    System.out.println("La Cantidad De Suplentes es:\n"+eq.getSuplentes());                                                    
                    System.out.println("El Nombre del DT es:\n"+eq.getDirectorTecnico());                                                    
                }                    
	}
	
	public void actualizarEquipo(int id, String nvoNombre, int nvoTit, int nvoSup, String nvoDt) 
        {
                Equipo aux = buscarEquipoPorId(id);               
                if(aux == null)
                {
                    System.out.println("id no encontrado ");
                }
                else
                {
                    aux.setNombre(nvoNombre);
                    aux.setTitulares(nvoTit);
                    aux.setSuplentes(nvoSup);
                    aux.setDirectorTecnico(nvoDt);                                    
                    start();
                    session.update(aux);
                    stop();
                }
	}
	
	public void borrarEquipo(int id) 
        {
            Equipo eq = buscarEquipoPorId(id);               
            if(eq == null)
            {
                System.out.println("id no encontrado ");
            }
            else
            {
            System.out.println("Borrando el equipo: "+id);
            start();
            session.delete(eq);
            stop();
            }
	}
        
        private Equipo buscarEquipoPorId(int id)
        {              
            start();
            Equipo eq = (Equipo) session.get(Equipo.class,id);            
            stop(); 
            return(eq);             
        }
        
        public Equipo buscarEquipoPorNombre(String nombre)
        {              
            start();
            Criteria criteria = session.createCriteria(Equipo.class);
            Equipo e = (Equipo) criteria.add(Restrictions.eq("nombre",nombre)).uniqueResult();           
            stop(); 
            return(e);             
        }

public void jugarPartido(String eq, int goles,int golesRival ){
    Equipo equipo = buscarEquipoPorNombre(eq);
    if(goles > golesRival){
            equipo.setPuntos(3);
            System.out.println(equipo.getPuntos());
            equipo.setPartidosJugados(1);
    }
    else if(golesRival > goles){
            equipo.setPartidosJugados(1);
    }
    else{
        equipo.setPuntos(1);
        equipo.setPartidosJugados(1);
    }
        start();
        session.update(equipo);                    
        stop();
} 
}
