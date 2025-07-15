package apifestivos.apifestivos.controladores;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import apifestivos.apifestivos.entidades.DTOs.FestivoDTO;
import apifestivos.apifestivos.interfaces.IFestivoServicio;

@RestController
@RequestMapping("/api/festivos")
public class FestivoControlador {

    @Autowired
    private IFestivoServicio servicio;

    @RequestMapping(value = "/verificar/{año}/{mes}/{dia}", method = RequestMethod.GET)
    public ResponseEntity<?>  verificar(@PathVariable int año, @PathVariable int mes,
            @PathVariable int dia) {
        try {
            LocalDate fecha = LocalDate.of(año, mes, dia);
            boolean esFestivo = servicio.verificar(fecha);
            return ResponseEntity.ok(esFestivo);
        } catch (DateTimeException e) {
            return ResponseEntity.badRequest().body("Fecha inválida: " + e.getMessage());
        }
    }

    @RequestMapping(value = "/listar/{año}", method = RequestMethod.GET)
    public List<FestivoDTO> listar(@PathVariable int año) {
        return servicio.listar(año);
    }

}
