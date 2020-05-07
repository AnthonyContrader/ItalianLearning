import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminLayoutComponent } from './admin-layout/admin-layout.component';
import { RouterModule } from '@angular/router';

/**
 * Modulo di layout. Viene caricato nel rputer outlet padre e poi 
 * non viene più ricaricato. Quando clicchiamo su un link ricarichiamo solo l'outlet
 * che sta dentro AdminLayoutComponent
 * 
 * @author Vittorio Valent
 * 
 * @see AdminLayoutComponent
 */
@NgModule({
  declarations: [AdminLayoutComponent],
  imports: [
    CommonModule,
    RouterModule
  ]
})
export class LayoutModule { }
