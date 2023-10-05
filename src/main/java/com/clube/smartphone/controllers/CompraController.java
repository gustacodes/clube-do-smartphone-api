package com.clube.smartphone.controllers;

import com.clube.smartphone.entities.Compra;
import com.clube.smartphone.enums.Pagamento;
import com.clube.smartphone.services.CompraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comprar")
public class CompraController {

    private CompraService compraService;

    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }

    @GetMapping
    public ResponseEntity<List<Compra>> listar() {
        return ResponseEntity.ok().body(compraService.listar());
    }

    @PutMapping("/{id}/{quantidade}/{pagamento}")
    public ResponseEntity<Compra> compra(@PathVariable Long id, @PathVariable Double quantidade, @PathVariable Pagamento pagamento) {

        Compra compra = compraService.compra(id, quantidade, pagamento);
        return ResponseEntity.ok().body(compra);

    }
}
