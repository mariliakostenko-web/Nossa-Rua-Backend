package adapter.input.api.service;

import adapter.input.api.dto.AdicionarIncidenteDTO;
import adapter.input.api.dto.IncidenteDTO;
import adapter.input.api.response.MensagemResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class IncidentesService {

    @Transactional
    public MensagemResponse adicionarIncidente(AdicionarIncidenteDTO dados) {
        log.info("Processando adição de incidente: {}", dados);

        validarDados(dados);
        validarCoordenadas(dados.getLat(), dados.getLng());

        String mensagem = String.format(
                "Incidente '%s' adicionado com sucesso",
                dados.getTitulo()
        );

        log.info("Incidente processado com sucesso: {}", mensagem);
        return new MensagemResponse(mensagem);
    }

    public List<IncidenteDTO> listarIncidentes() {
        log.info("Listando todos os incidentes mockados");
        return criarIncidentesMock();
    }

    private List<IncidenteDTO> criarIncidentesMock() {
        List<IncidenteDTO> incidentes = new ArrayList<>();

        incidentes.add(new IncidenteDTO(
                1L,
                "Sem placa",
                "13/11/2025 08:30:00",
                "-23.539288",
                "-47.466292",
                "Sem Placa",
                "Cruzamento sem placa na Av. Comitre"
        ));

        incidentes.add(new IncidenteDTO(
                2L,
                "Buraco na via",
                "13/11/2025 10:15:00",
                "-23.537744",
                "-47.466242",
                "Buraco",
                "Buraco em frente ao tauste"
        ));

        incidentes.add(new IncidenteDTO(
                3L,
                "Vegetação alta na via",
                "13/11/2025 12:45:00",
                "-23.536264",
                "-47.465272",
                "Vegetação na via",
                "Vegetação na via em frente a estabelecimento comercial"
        ));

        incidentes.add(new IncidenteDTO(
                4L,
                "Animal atropelado",
                "13/11/2025 14:20:00",
                "-23.535508",
                "-47.467818",
                "Animais na Via",
                "Animal atropelado bloqueando parcialmente a via"
        ));

        incidentes.add(new IncidenteDTO(
                5L,
                "Falta de placa",
                "13/11/2025 16:00:00",
                "-23.537598",
                "-47.465340",
                "Sem Placa",
                "Cruzamento sem placa na Av"
        ));

        incidentes.add(new IncidenteDTO(
                6L,
                "Buraco grande",
                "13/11/2025 17:30:00",
                "-23.535039",
                "-47.466158",
                "Buraco",
                "Buraco em frente ao shopping"
        ));

        log.info("Total de incidentes mockados: {}", incidentes.size());
        return incidentes;
    }

    private void validarDados(AdicionarIncidenteDTO dados) {
        if (dados == null) {
            throw new IllegalArgumentException("Dados do incidente não podem ser nulos");
        }

        if (dados.getData() == null || dados.getData().trim().isEmpty()) {
            throw new IllegalArgumentException("Data não pode ser nula ou vazia");
        }

        if (dados.getLat() == null || dados.getLat().trim().isEmpty()) {
            throw new IllegalArgumentException("Latitude não pode ser nula ou vazia");
        }

        if (dados.getLng() == null || dados.getLng().trim().isEmpty()) {
            throw new IllegalArgumentException("Longitude não pode ser nula ou vazia");
        }

        validarFormatoData(dados.getData());
    }

    private void validarFormatoData(String data) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            LocalDateTime.parse(data, formatter);
        } catch (DateTimeParseException e) {
            log.error("Formato de data inválido: {}", data);
            throw new IllegalArgumentException("Formato de data inválido. Use o formato: dd/MM/yyyy HH:mm:ss");
        }
    }

    private void validarCoordenadas(String lat, String lng) {
        try {
            double latitude = Double.parseDouble(lat);
            double longitude = Double.parseDouble(lng);

            if (latitude < -90 || latitude > 90) {
                throw new IllegalArgumentException("Latitude deve estar entre -90 e 90");
            }

            if (longitude < -180 || longitude > 180) {
                throw new IllegalArgumentException("Longitude deve estar entre -180 e 180");
            }

        } catch (NumberFormatException e) {
            log.error("Coordenadas inválidas - Lat: {}, Lng: {}", lat, lng);
            throw new IllegalArgumentException("Coordenadas devem ter valores numéricos válidos");
        }
    }
}
