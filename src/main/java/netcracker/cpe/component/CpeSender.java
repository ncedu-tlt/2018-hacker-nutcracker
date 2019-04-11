package netcracker.cpe.component;

import netcracker.cpe.integration.CpeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CpeSender {

	@Autowired
	CpeController cpeController;

	@Scheduled ( fixedRate = 5000 )
	public void dialogWithPe ( ) {
		cpeController.sendCpe();
	}
}