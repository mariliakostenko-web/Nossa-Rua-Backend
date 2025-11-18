package adapter.input.api.controller;

import adapter.input.api.dto.AdicionarIncidenteDTO;
import adapter.input.api.dto.IncidenteDTO;
import adapter.input.api.service.IncidentesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidentes")
@Slf4j
@RequiredArgsConstructor
public class IncidentesController {

    private final IncidentesService incidentesService;

    @PostMapping("/adicionar")
    public ResponseEntity<String> adicionarIncidentes(@Valid @RequestBody AdicionarIncidenteDTO dados) {
        log.info("Recebendo requisição para adicionar incidente: {}", dados);

        try {
            String mensagem = incidentesService.adicionarIncidente(dados);
            return ResponseEntity.status(HttpStatus.CREATED).body(mensagem);

        } catch (IllegalArgumentException iae) {
            log.error("Erro de validação ao adicionar incidente: {}", iae.getMessage());
            return ResponseEntity.badRequest().body(iae.getMessage());

        } catch (Exception e) {
            log.error("Erro inesperado ao processar adição de incidente", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno ao processar a requisição.");
        }
    }

    @GetMapping
    public ResponseEntity<List<IncidenteDTO>> listarIncidentes() {
        log.info("Recebendo requisição para listar todos os incidentes");

        try {
            List<IncidenteDTO> incidentes = incidentesService.listarIncidentes();
            return ResponseEntity.ok(incidentes);

        } catch (Exception e) {
            log.error("Erro inesperado ao listar incidentes", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
