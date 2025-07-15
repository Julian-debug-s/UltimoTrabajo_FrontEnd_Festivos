import { Component } from '@angular/core';
import { ReferenciasMaterialModule } from '../../../shared/modulos/referencias-material.module';
import { ColumnMode, NgxDatatableModule } from '@swimlane/ngx-datatable';
import { FormsModule } from '@angular/forms';
import { FestivosDTO } from '../../../shared/entidades/FestivosDTO';
import { FestivosService } from '../../../core/servicios/festivos.service';

@Component({
  selector: 'app-listado',
  imports: [
    ReferenciasMaterialModule,
    FormsModule,
    NgxDatatableModule
  ],
  templateUrl: './listado.component.html',
  styleUrl: './listado.component.scss'
})
export class ListadoComponent {

  constructor(private servicioFestivos: FestivosService){

  }

  public anioBusqueda:number= new Date().getFullYear();
  public festivos: FestivosDTO[]=[];
  public modoColumna = ColumnMode;
  public columnas=[
    {prop: "nombre", name: "Festivo" },
    {prop: "fecha", name: "Fecha" }
  ];

  public listar(){
    this.servicioFestivos.listar(this.anioBusqueda).subscribe({
      next: (response) =>{
        this.festivos = response
      },
      error: (error) =>{
        window.alert(error)
      }
    });

    }
}


