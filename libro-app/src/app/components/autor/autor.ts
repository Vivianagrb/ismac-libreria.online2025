import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Autor } from '../../model/autor.model';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { AutorService } from '../../services/autor';
import Swal from 'sweetalert2';
import { NgForm } from '@angular/forms';


@Component({
  selector: 'app-autor',
  standalone: false,
  templateUrl: './autor.html',
  styleUrl: './autor.css'
})
export class AutorComponent implements OnInit {

  autores: Autor[] = [];
  autor: Autor = {} as Autor;
  editar: boolean = false;
  idEditar: number | null = null;

  dataSource!: MatTableDataSource<Autor>;
  mostrarColumnas: string[] = ['id_autor', 'nombre', 'apellido', 'pais', 'direccion', 'telefono', 'correo', 'acciones'];

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  @ViewChild('formularioAutor', { static: false })
  formularioAutor!: ElementRef;

  constructor(private autorService: AutorService) { }


  ngOnInit(): void {
    this.findAll();
  }

  findAll(): void {
    this.autorService.findAll().subscribe((data: Autor[]) => {
      this.dataSource = new MatTableDataSource<Autor>(data);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }



  save(): void {
    this.autorService.save(this.autor).subscribe(() => {
      this.autor = {} as Autor;
      this.findAll();
    });

  }

  update(): void {
    if (this.idEditar !== null) {
      this.autorService.update(this.idEditar, this.autor).subscribe(() => {
        this.autor = {} as Autor;
        this.editar = false;
        this.idEditar = null;
        this.findAll();
      });
    }
  }

  delete(): void {
    Swal.fire({
      title: 'Desea eliminar el dato',
      text: 'Esta acción no se puede deshacer',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si, eliminar',
      cancelButtonText: 'Cancelar',
      confirmButtonColor: '#d33',
      cancelButtonColor: '#3085d6'
    }).then((result) => {
      if (result.isConfirmed) {
        this.autorService.delete(this.autor.id_autor).subscribe(() => {
          this.findAll();
          this.autor = {} as Autor;
          Swal.fire('Eliminado', 'El autor ha sido eliminado', 'success');
        });
      } else {
        this.autor = {} as Autor;
      }
    });
  }

  editarAutor(autor: Autor): void {
    this.autor = { ...autor };
    this.idEditar = autor.id_autor;
    this.editar = true;

    setTimeout(() => {
      this.formularioAutor.nativeElement.scrollIntoView({ behavior: 'smooth', block: 'start' });

    }, 100);
  }

  editarAutorCancelar(form: NgForm): void {
    this.autor = {} as Autor;
    this.idEditar = null;
    this.editar = false;
    form.resetForm();
  }

  guardarAutor(form: NgForm): void {
    if (this.editar && this.idEditar !== null) {
      this.update();
      form.resetForm();
    } else {
      this.save();
      form.resetForm();
    }
  }

  buscarAutor(event: Event) {
    const filtro = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filtro.trim().toLowerCase();
  }
}



