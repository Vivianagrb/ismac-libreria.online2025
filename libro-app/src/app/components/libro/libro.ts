import { Component, ElementRef, TemplateRef, ViewChild, OnInit } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { HttpClient } from '@angular/common/http';
import { Libro } from '../../model/libro.model';
import { Autor } from '../../model/autor.model';
import { Categoria } from '../../model/categoria.model';
import { MatTableDataSource } from '@angular/material/table';
import Swal from 'sweetalert2';
import { LibroService } from '../../services/libro';
import { AutorService } from '../../services/autor';
import { CategoriaService } from '../../services/categoria';
import { NgForm } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';


@Component({
  selector: 'app-libro',
  standalone: false,
  templateUrl: './libro.html',
  styleUrls: ['./libro.css'],

})
export class LibroComponent implements OnInit {
  libros: Libro[] = [];
  libro: Libro = {} as Libro;
  editar: boolean = false;
  idEditar: number | null = null;
  autores: Autor[] = [];
  categorias: Categoria[] = [];
  dataSource!: MatTableDataSource<Libro>;
  selectedFile?: File;
  imagenPreview: string = '';
  libroSeleccionado: Libro | null = null;

  mostrarColumnas: string[] = [
    'detalles', 'id_libro', 'titulo', 'editorial', 'edicion',
    'idioma', 'fecha_publicacion', 'num_ejemplares', 'precio', 'autor', 'categoria', 'acciones'
  ];

  @ViewChild('formularioLibro', { static: false }) formularioLibro!: ElementRef;
  // @ViewChild(MatPaginator) paginator!: MatPaginator;

  @ViewChild(MatPaginator, { static: false }) paginator!: MatPaginator;

  @ViewChild(MatSort) sort!: MatSort;
  @ViewChild('modalLibro') modalLibro!: TemplateRef<any>;
  @ViewChild('modalDetalles') modalDetalles!: TemplateRef<any>;

  constructor(
    private libroService: LibroService,
    private autorService: AutorService,
    private categoriaService: CategoriaService,
    private dialog: MatDialog,
    private http: HttpClient
  ) { }



  ngOnInit(): void {
    this.findAll();
    this.cargarAutores();
    this.cargarCategorias();
  }

  findAll(): void {
    this.libroService.findAll().subscribe((data: Libro[]) => {
      this.dataSource = new MatTableDataSource<Libro>(data);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

  cargarAutores(): void {
    this.autorService.findAll().subscribe(data => this.autores = data);
  }

  cargarCategorias(): void {
    this.categoriaService.findAll().subscribe(data => this.categorias = data);
  }

  save(): void {
    this.libroService.save(this.libro).subscribe(() => {
      this.libro = {} as Libro;
      this.findAll();
    });
  }

  update(): void {
    if (this.idEditar !== null) {
      this.libroService.update(this.idEditar, this.libro).subscribe(() => {
        this.libro = {} as Libro;
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
      confirmButtonText: 'Sí, eliminar',
      cancelButtonText: 'Cancelar',
      confirmButtonColor: '#d33',
      cancelButtonColor: '#3085d6'
    }).then((result) => {
      // if (result.isConfirmed && this.libro.id_libro) {
      if (result.isConfirmed) {

        this.libroService.delete(this.libro.id_libro).subscribe(() => {
          this.findAll();
          this.libro = {} as Libro;
          Swal.fire('Eliminado', 'El Libro ha sido eliminado', 'success');
        });
      } else {
        this.libro = {} as Libro;
      }
    });
  }


  //interactuar con la pina web

  editarLibro(libro: Libro): void {
    this.libro = { ...libro };
    this.idEditar = libro.id_libro;
    this.editar = true;

    setTimeout(() => {
      this.formularioLibro.nativeElement.scrollIntoView({ behavior: 'smooth', block: 'start' });
    }, 100);
  }

  editarLibroCancelar(form: NgForm): void {
    this.libro = {} as Libro;
    this.idEditar = null;
    this.editar = false;
    form.resetForm();
  }

  guardarLibro(): void {
    console.log('Libro que se va a guardar:', this.libro);
    if (this.editar && this.idEditar !== null) {
      this.update();
    } else {
      this.save();
    }
    this.dialog.closeAll();
  }


  filtroLibro(event: Event): void {
    const filtro = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filtro.trim().toLowerCase();
  }

  nombreCompletoAutor(autor: Autor): string {
    return `${autor.nombre} ${autor.apellido}`;
  }

  abrirModal(libro?: Libro): void {
    if (libro) {
      this.libro = { ...libro };
      this.editar = true;
      this.idEditar = libro.id_libro;
    } else {
      this.libro = {} as Libro;
      this.editar = false;
      this.idEditar = null;
    }
    this.dialog.open(this.modalLibro, {
      width: '800px',
      disableClose: true
    });
  }

  abrirModalDetalle(libro: Libro): void {
    this.libroSeleccionado = libro;
    this.dialog.open(this.modalDetalles, { width: '500px' });
  }

  cerrarModal(): void {
    this.dialog.closeAll();
    this.libroSeleccionado = null;
  }

  compararAutores(a1: Autor, a2: Autor): boolean {
    return a1 && a2 ? a1.id_autor === a2.id_autor : a1 === a2;
  }

  compararCategorias(c1: Categoria, c2: Categoria): boolean {
    return c1 && c2 ? c1.id_categoria === c2.id_categoria : c1 === c2;
  }

  seleccionarArchivo(event: any) {
    //const input = event.target as HTMLInputElement;
    // if (input.files && input.files.length > 0) {
    this.selectedFile = event.target.files[0];
    //  }
  }



  subirImagen(): void {
    if (!this.selectedFile) {
      console.error('No se ha seleccionado un archivo');
      return;
    }

    const formData = new FormData();
    formData.append("file", this.selectedFile);

    if (this.libro && this.libro.portada) {
      formData.append("oldImage", this.libro.portada);
    }


    this.http.post<{ ruta: string }>('http://localhost:8080/api/upload-portada', formData)
      .subscribe((res) => {
        this.libro.portada = res.ruta;
        this.imagenPreview = res.ruta;
      });
  }
}
