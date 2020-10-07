package co.edu.unicauca.asae.core.taller.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.edu.unicauca.asae.core.taller.models.Dependencia;
import co.edu.unicauca.asae.core.taller.services.IDependenciaService;
import co.edu.unicauca.asae.core.taller.util.Utileria;

@Controller
@RequestMapping("/dependencias")
public class DependenciasController {

    @Autowired
    private IDependenciaService servicioGestinarDependencias;

    @PostMapping("/guardar")
    public String guardarDependencia(Dependencia objDependencia, BindingResult result, @RequestParam("archivoImagen") MultipartFile multiPart, RedirectAttributes attributes) {
        boolean bandera;
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println("Ocurrió un error" + error.getDefaultMessage());
            }
            return "dependencias/formDependencia";
        }
        if(!multiPart.isEmpty()) {
            String ruta="C:/Users/USERPC/Desktop/ASAE/taller/src/main/resources/static/images/";
            String nombreImagen=Utileria.guardarArchivo(multiPart, ruta);
            //System.out.println("NOMBRE: "+nombreImagen);
            if(nombreImagen!=null) {
                objDependencia.setImagen(nombreImagen);
            }
        }

        bandera = this.servicioGestinarDependencias.guardarDependencia(objDependencia);

        if (bandera == true) {
            attributes.addFlashAttribute("mensajeExito", "Dependencia agregada con éxito");
        } else {
            attributes.addFlashAttribute("mensajeError", "Ocurrió un error al agregar la Dependencia");
        }
        return "redirect:/dependencias/mostrarDependencias";
    }

    @PostMapping("/guardarDependenciaActualizada")
    public String guardarDependenciaActualizada(Dependencia objDependencia, BindingResult result, @RequestParam("archivoImagen") MultipartFile multiPart, RedirectAttributes attributes) {
        boolean bandera;
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println("Ocurrió un error" + error.getDefaultMessage());
            }
            return "dependencias/formDependenciaEditar";
        }
        
        if(!multiPart.isEmpty()) {
            String ruta="C:/Users/USERPC/Desktop/ASAE/taller/src/main/resources/static/images/";
            String nombreImagen=Utileria.guardarArchivo(multiPart, ruta);
            
            if(nombreImagen!=null) {
                objDependencia.setImagen(nombreImagen);
            }
        }

        bandera = this.servicioGestinarDependencias.actualizarDependencia(objDependencia);

        if (bandera == true) {
            attributes.addFlashAttribute("mensajeExito", "Dependencia actualizada con éxito");
        } else {
            attributes.addFlashAttribute("mensajeError", "Ocurrió un error al agregar la Dependencia");
        }
        return "redirect:/dependencias/mostrarDependencias";

    }

    @GetMapping("/crear")
    public String crearDependencia(Dependencia objDependencia) {
        return "dependencias/formDependencia";
    }

    @GetMapping("/mostrarDependencias")
    public String mostrarDependencias(Model modelo) {
        List<Dependencia> lista = servicioGestinarDependencias.listarDependencias();
        modelo.addAttribute("dependencias", lista);
        return "dependencias/listarDependencias";
    }

    @GetMapping("/editar/{id}")
    public String verDetalleDependencia(@PathVariable int id, Model modelo) {
        Dependencia objDependencia = servicioGestinarDependencias.buscarDependencia(id);
        modelo.addAttribute("dependencia", objDependencia);
        return "dependencias/formDependenciaEditar";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarDependencia(@PathVariable int id, RedirectAttributes attributes) {

        boolean bandera = this.servicioGestinarDependencias.eliminarDependencia(id);

        if (bandera == true) {
            attributes.addFlashAttribute("mensajeExito", "Dependencia eliminada con éxito");
        } else {
            attributes.addFlashAttribute("mensajeError", "Ocurrió un error al eliminar la Dependencia");
        }
        return "redirect:/dependencias/mostrarDependencias";
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));

    }

    @PostMapping("/save")
    public String guardar(Dependencia dependencia, BindingResult result, Model model, @RequestParam("imagen") MultipartFile multiPart, RedirectAttributes attributes) {

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println("Ocurrió un error" + error.getDefaultMessage());
            }
            return "dependencias/formDependencia";
        }

        if(!multiPart.isEmpty()) {
            String ruta="d:/dependencias/img-dependencias/";
            String nombreImagen=Utileria.guardarArchivo(multiPart, ruta);
            if(nombreImagen!=null) {
                dependencia.setImagen(nombreImagen);
            }
        }

        servicioGestinarDependencias.guardarDependencia(dependencia);
        attributes.addFlashAttribute("mensajeExito", "Registro guardado con éxito");
        return "redirect:/dependencias/mostrarDependencias";

    }

}