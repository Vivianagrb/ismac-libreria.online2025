package com.distribuida.service;

import com.distribuida.dao.CarritoItemRepository;
import com.distribuida.dao.CarritoRepository;
import com.distribuida.dao.ClienteRepository;
import com.distribuida.dao.LibroRepository;
import com.distribuida.model.Carrito;
import com.distribuida.model.CarritoItem;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CarritoServiceImpl implements CarritoService{

    private final CarritoRepository carritoRepository;
    private final CarritoItemRepository carritoItemRepository;
    private final ClienteRepository clienteRepository;
    private final LibroRepository libroRepository;

    private static final BigDecimal IVA = new BigDecimal("0.15");

    public CarritoServiceImpl(CarritoRepository carritoRepository,
                              CarritoItemRepository carritoItemRepository,
                              ClienteRepository clienteRepository,
                              LibroRepository libroRepository
                                ) {
        this.carritoRepository = carritoRepository;
        this.carritoItemRepository = carritoItemRepository;
        this.clienteRepository = clienteRepository;
        this.libroRepository = libroRepository;
    }

    @Override
    @Transactional
    public Carrito getOrCreateByClienteId(int clienteId, String token) {
        var cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado: " + clienteId));

        var carritoOpt = carritoRepository.findByCliente(cliente);
        if (carritoOpt.isPresent())  return carritoOpt.get();

        var carrito = new Carrito();
        carrito.setCliente(cliente);
        carrito.setToken(token);
        carrito.recomprobacionTotalesCompat();
        return carritoRepository.save(carrito);
    }

    @Override
    public Carrito addItem(int clienteId, int libroId, int cantidad) {
        if(cantidad <= 0) throw new IllegalArgumentException("Cantidad debe ser > 0");

        var carrito = getOrCreateByClienteId(clienteId,  null);
        var libro = libroRepository.findById(libroId)
                .orElseThrow(() -> new IllegalArgumentException("Libro no encontrado" + libroId));

        var itemOpt = carritoItemRepository.findByCarritoAndLibro(carrito, libro) ;

        if(itemOpt.isPresent()){
            var item= itemOpt.get();
            item.setCantidad(item.getCantidad() + cantidad);
            item.setPrecioUnitario(BigDecimal.valueOf(libro.getPrecio()));
            item.calcTotal();
            carritoItemRepository.save(item);
        }else{

            var item = new CarritoItem();
            item.setCarrito (carrito);
            item.setLibro(libro);
            item.setCantidad (cantidad);
            item.setPrecioUnitario (BigDecimal.valueOf(libro.getPrecio()));
            item.calcTotal();
            carrito.getItems().add(item);
        }
        carrito.recomputarTotales (IVA);
        return carritoRepository.save(carrito);
    }

//   @Override
//    public Carrito getorCreateByClienteld(int clienteId, String token) {
//        return null;
//    }


    @Override
    @Transactional

    public Carrito updateItemCantidad(int clienteId, long carritoItemId, int nuevaCantidad) {
        if (nuevaCantidad < 0) { throw new IllegalArgumentException("Cantidad no puede ser negativa");

        var carrito =  getByClienteId(clienteId);

        var item = carritoItemRepository.findById(carritoItemId)
                .orElseThrow(() -> new IllegalArgumentException("Item no encontrado: " + carritoItemId));

        if (!item.getCarrito().getIdCarrito().equals(carrito.getIdCarrito())) {
            throw new IllegalArgumentException("El item no pertenece al carrito del cliente");
        }

            if(nuevaCantidad == 0){
                carrito.getItems().remove(item);
//carritoRepository.delete(item);
            }else{
                item.setCantidad (nuevaCantidad);
                carritoItemRepository.save(item);
            }
            carrito.recomputarTotales(IVA);
            return carritoRepository.save(carrito);
        }
        @Override
        @Transactional
        public void removeItem(int clienteId, long carritoItemId) {
//updateItemCantidad(clienteId, carritoItemId),
        }

        @Override
        public void clear(int clienteId) {
        }
