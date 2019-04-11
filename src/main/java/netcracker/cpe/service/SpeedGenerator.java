package netcracker.cpe.service;

import netcracker.cpe.entity.dao.CpeDao;
import netcracker.cpe.entity.dto.PeDto;

import java.util.List;
import java.util.Random;

public class SpeedGenerator {

	public List<CpeDao> generate (PeDto peDto, List<CpeDao> list) {

		Random random = new Random();
		Integer temperature = peDto.getTemperature();
		Integer speed;
		Integer maxSpeed = peDto.getMaxDownlinkSpeed();

		if (temperature < 70) {
			maxSpeed = maxSpeed / list.size();
			if (maxSpeed > 1000) maxSpeed = 1000;
			for (CpeDao cpe : list) {
				speed = random.nextInt(21) + (maxSpeed - 20);
				cpe.setDownlinkSpeed(speed);
			}
		} else if (temperature < 80) {
			maxSpeed = maxSpeed / 2 / list.size();
			if (maxSpeed > 1000) maxSpeed = 1000;
			for (CpeDao cpe : list) {
				speed = random.nextInt(21) + (maxSpeed - 20);
				cpe.setDownlinkSpeed(speed);
			}
		} else if (temperature < 90) {
			maxSpeed = maxSpeed / 4 / list.size();
			if (maxSpeed > 1000) maxSpeed = 1000;
			for (CpeDao cpe : list) {
				speed = random.nextInt(21) + (maxSpeed - 20);
				cpe.setDownlinkSpeed(speed);
			}
		} else if (temperature < 100) {
			maxSpeed = maxSpeed / 8 / list.size();
			if (maxSpeed > 1000) maxSpeed = 1000;
			for (CpeDao cpe : list) {
				speed = random.nextInt(21) + (maxSpeed - 20);
				cpe.setDownlinkSpeed(speed);
			}
		}
		return list;
	}
}
