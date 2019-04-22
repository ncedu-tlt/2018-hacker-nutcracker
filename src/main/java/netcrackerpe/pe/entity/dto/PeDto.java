package netcrackerpe.pe.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class PeDto {

    private String ip;
    private boolean fanActive;
}
