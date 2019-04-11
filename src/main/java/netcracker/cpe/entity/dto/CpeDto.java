package netcracker.cpe.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CpeDto {

	private String ip;
	private boolean isInternetActive;
}
