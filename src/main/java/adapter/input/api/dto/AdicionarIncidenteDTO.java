package adapter.input.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdicionarIncidenteDTO {
    @NotBlank(message = "Data é obrigatória")
    private String data;

    @NotBlank(message = "Endereço é obrigatório")
    private String lat;

    @NotBlank (message = "Endereço é obrigatório")
    private String lng;

    private String categoria;

    @Size(max = 200, message = "Observação deve ter no máximo 200 caracteres")
    private String observacao;
}
