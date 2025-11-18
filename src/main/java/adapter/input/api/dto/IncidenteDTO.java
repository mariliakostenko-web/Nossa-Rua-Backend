package adapter.input.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncidenteDTO {
    private Long id;
    private String data;
    private String lat;
    private String lng;
    private String categoria;
    private String observacao;
}
