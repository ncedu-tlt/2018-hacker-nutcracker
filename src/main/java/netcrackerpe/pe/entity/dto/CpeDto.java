package netcrackerpe.pe.entity.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CpeDto {

    private String ip;
    private String type;
    private boolean isInternetActive;
    private Integer maxDownlinkSpeed;
    private Integer downlinkSpeed;
    private String peIpAddress;
    private Integer coordinateX;
    private Integer coordinateY;

}
