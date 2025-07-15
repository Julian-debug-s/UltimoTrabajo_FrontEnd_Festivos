package apifestivos.apifestivos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import apifestivos.apifestivos.entidades.Festivo;
import apifestivos.apifestivos.entidades.DTOs.FestivoDTO;
import apifestivos.apifestivos.interfaces.IFestivoServicio;
import apifestivos.apifestivos.repositorios.IFestivoRepositorio;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class FestivoServicio implements IFestivoServicio {

    private IFestivoRepositorio repositorio;

    public FestivoServicio(IFestivoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<Festivo> listar() {
        return repositorio.findAll(Sort.by(Sort.Direction.ASC, "nombre"));
    }

    @Override
    public Festivo obtener(int id) {
        return repositorio.findById(id).isEmpty() ? null : repositorio.findById(id).get();
    }

    @Override
    public List<Festivo> buscar(String nombre) {
        // return repositorio.buscar(nombre);
        return null;
    }

    @Override
    public Festivo agregar(Festivo festivo) {
        festivo.setId(0);
        return repositorio.save(festivo);
    }

    @Override
    public Festivo modificar(Festivo festivo) {
        if (repositorio.findById(festivo.getId()).isEmpty())
            return null;
        return repositorio.save(festivo);
    }

    @Override
    public boolean eliminar(int id) {
        try {
            repositorio.deleteById(id);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean verificar(LocalDate fecha) {
        var fechasFestivos = getFechasFestivos(fecha.getYear());
        return fechasFestivos.stream().anyMatch(f -> f.getFecha().equals(fecha));
    }

    private List<FestivoDTO> getFechasFestivos(int año) {
        List<FestivoDTO> fechasFestivos = new ArrayList();
        var festivos = repositorio.findAll();
        for (var festivo : festivos) {
            LocalDate fechaFestivo;
            switch (festivo.getTipo().getId()) {
                case 1: // Fijo
                    fechaFestivo = LocalDate.of(año, festivo.getMes(), festivo.getDia());
                    fechasFestivos.add(new FestivoDTO(festivo.getNombre(), fechaFestivo));
                    break;

                case 2: // Ley Puente Festivo (según Ley 51 de 1983)
                    fechaFestivo = ServicioFechas.siguienteLunes(LocalDate.of(año, festivo.getMes(), festivo.getDia()));
                    fechasFestivos.add(new FestivoDTO(festivo.getNombre(), fechaFestivo));
                    break;
                case 3:
                    fechaFestivo = ServicioFechas.agregarDias(ServicioFechas.getPascua(año), festivo.getDiasPascua());
                    fechasFestivos.add(new FestivoDTO(festivo.getNombre(), fechaFestivo));
                    break;
                case 4:
                    fechaFestivo = ServicioFechas.siguienteLunes(
                            ServicioFechas.agregarDias(ServicioFechas.getPascua(año), festivo.getDiasPascua()));
                    fechasFestivos.add(new FestivoDTO(festivo.getNombre(), fechaFestivo));
                    break;
            }
        }
        return fechasFestivos;
    }

    @Override
    public List<FestivoDTO> listar(int año) {
        return getFechasFestivos(año);
    }

}
