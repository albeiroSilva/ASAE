package co.edu.unicauca.asae.core.taller.services;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import co.edu.unicauca.asae.core.taller.models.Dependencia;

@Service
public class DependenciaServiceImpl implements IDependenciaService {

    private List<Dependencia> lista;

    public DependenciaServiceImpl(){

        //SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        this.lista = new LinkedList<Dependencia>();

        Dependencia objDependencia1 = new Dependencia();
        objDependencia1.setId(1);
        objDependencia1.setNombre("dependencia 1");
        objDependencia1.setTipo("Administrativa");
        objDependencia1.setLocalizacion("Tulcán");
        objDependencia1.setImagen("dependencia1.png");
        objDependencia1.setFechaCreacion(new Date());


        Dependencia objDependencia2 = new Dependencia();
        objDependencia2.setId(2);
        objDependencia2.setNombre("dependencia 2");
        objDependencia2.setTipo("Académica");
        objDependencia2.setLocalizacion("Paraninfo");
        objDependencia2.setImagen("dependencia2.png");
        objDependencia2.setFechaCreacion(new Date());

        Dependencia objDependencia3 = new Dependencia();
        objDependencia3.setId(3);
        objDependencia3.setNombre("dependencia 3");
        objDependencia3.setTipo("Control");
        objDependencia3.setLocalizacion("Centro");
        objDependencia3.setImagen("dependencia3.png");
        objDependencia3.setFechaCreacion(new Date());

        Dependencia objDependencia4 = new Dependencia();
        objDependencia4.setId(4);
        objDependencia4.setNombre("dependencia 4");
        objDependencia4.setTipo("Académica");
        objDependencia4.setLocalizacion("Guacas");
        objDependencia4.setImagen("dependencia4.png");
        objDependencia4.setFechaCreacion(new Date());

        lista.add(objDependencia1);
        lista.add(objDependencia2);
        lista.add(objDependencia3);
        lista.add(objDependencia4);

    }


    @Override
    public boolean guardarDependencia(Dependencia objDependencia) { 
        objDependencia.setId(this.lista.size()+1);       
        return this.lista.add(objDependencia);
    }

    @Override
    public boolean actualizarDependencia(Dependencia objDependencia) {
        boolean bandera = false;
        for (int i = 0; i < this.lista.size(); i++) {
            if (this.lista.get(i).getId() == objDependencia.getId()) {
                this.lista.set(i, objDependencia);
                bandera = true;
                break;
            }
        }
        return bandera;
    }

    @Override
    public List<Dependencia> listarDependencias() {
        return this.lista;
    }

    @Override
    public Dependencia buscarDependencia(Integer id) {
        Dependencia objDependencia = null;
        for (int i = 0; i < this.lista.size(); i++) {
            if (this.lista.get(i).getId() == id) {
                objDependencia = this.lista.get(i);
                break;
            }
        }
        return objDependencia;
    }

    @Override
    public boolean eliminarDependencia(Integer id) {
        boolean bandera = false;
        for (int i = 0; i < this.lista.size(); i++) {
            if (this.lista.get(i).getId() == id) {
                this.lista.remove(i);
                bandera = true;
                break;
            }
        }
        return bandera;
    }
    
}