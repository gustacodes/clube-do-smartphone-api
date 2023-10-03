package com.clube.smartphone.controllers;

import com.clube.smartphone.entities.Compra;
import com.clube.smartphone.entities.Financeiro;
import com.clube.smartphone.entities.Produtos;
import com.clube.smartphone.enums.Pagamento;
import com.clube.smartphone.services.CompraService;
import com.clube.smartphone.services.ProdutosServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comprar")
public class CompraController {

    private CompraService compraService;

    public CompraController(ProdutosServices produtosServices, CompraService compraService) {
        this.compraService = compraService;
    }

    @PutMapping("/{id}/{quantidade}/{pagamento}")
    public ResponseEntity<Compra> compra(@PathVariable Long id, @PathVariable Integer quantidade, @PathVariable Pagamento pagamento) {

        Compra compra = compraService.compra(id, quantidade, pagamento);
        return ResponseEntity.ok().body(compra);

    }
}
