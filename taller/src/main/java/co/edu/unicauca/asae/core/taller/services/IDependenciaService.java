package co.edu.unicauca.asae.core.taller.services;

import java.util.List;

import co.edu.unicauca.asae.core.taller.models.Dependencia;

public interface IDependenciaService {
    
    public boolean guardarDependencia(Dependencia objDependencia);
    public boolean actualizarDependencia(Dependencia objDependencia);
    public List<Dependencia> listarDependencias();
    public Dependencia buscarDependencia(Integer id);
    public boolean eliminarDependencia(Integer id);
    
}