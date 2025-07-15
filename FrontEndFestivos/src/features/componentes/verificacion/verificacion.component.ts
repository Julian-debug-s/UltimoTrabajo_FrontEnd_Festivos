import { Component } from '@angular/core';
import { ReferenciasMaterialModule } from '../../../shared/modulos/referencias-material.module';
import { FormsModule } from '@angular/forms';
import { FestivosService } from '../../../core/servicios/festivos.service';
import { MatDialog } from '@angular/material/dialog';
import { FestivosDTO } from '../../../shared/entidades/FestivosDTO';

@Component({
  selector: 'app-verificacion',
  imports: [
    ReferenciasMaterialModule,
    FormsModule
  ],
  templateUrl: './verificacion.component.html',
  styleUrl: './verificacion.component.scss'
})

export class VerificacionComponent {

  public fecha: Date | null = null;

  constructor(private servicioFestivos: FestivosService){

  }

  public verificar(){

    if(this.fecha){
      this.servicioFestivos.verificar(this.fecha).subscribe({
        next: (response) => {
          if(response){
            window.alert('Es festivo')
          } else if (response.valueOf()==false){
            window.alert('No es festivo')
          }
        },
        error: (error) => {
          window.alert(error);
        }
      });
    }
  }

  
}
