package apifestivos.apifestivos.interfaces;

import java.time.LocalDate;
import java.util.List;

import apifestivos.apifestivos.entidades.Festivo;
import apifestivos.apifestivos.entidades.DTOs.FestivoDTO;

public interface IFestivoServicio {

    public List<Festivo> listar();

    public Festivo obtener(int id);

    public List<Festivo> buscar(String nombre);

    public Festivo agregar(Festivo festivo);

    public Festivo modificar(Festivo festivo);

    public boolean eliminar(int id);

    public boolean verificar(LocalDate fecha);

    public List<FestivoDTO> listar(int año);

}
