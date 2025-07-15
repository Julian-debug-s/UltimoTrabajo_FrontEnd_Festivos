import { Routes } from '@angular/router';
import { VerificacionComponent } from '../features/componentes/verificacion/verificacion.component';
import { ListadoComponent } from '../features/componentes/listado/listado.component';

export const routes: Routes = [
    { path:"", redirectTo: "verificar", pathMatch: "full"},
    { path: "verificar", component: VerificacionComponent},
    { path: "listar", component: ListadoComponent}

];
