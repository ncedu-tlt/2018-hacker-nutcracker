package netcrackerpe.pe.service;


import netcrackerpe.pe.entity.dao.PeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TemperatureControll {

    @Autowired
    private PeService peService;

    @Scheduled(fixedRate = 5000)
    public void controllTemperature() {
        List<PeDao> pe = peService.findAll();

        for (int i = 0; i < pe.size(); i++) {
            generateTemperature(pe.get(i));
            if (pe.get(i).isFanActive()) {
                fan(pe.get(i));
            }
            peService.savePe(pe.get(i));
        }

    }

    public void generateTemperature(PeDao pe) {
        Integer newTemperature;

        newTemperature = pe.getTemperature() + 2;
        pe.setTemperature(newTemperature);

        if (pe.getTemperature() < 23) {
            pe.setTemperature(23);
        }
        if (pe.getTemperature() > 99 && !pe.isFanActive()) {
            pe.setFanActive(true);
        }
    }

    public void fan(PeDao pe) {
        Integer cooling = 5;
        Integer currentTemperature = pe.getTemperature();
        pe.setTemperature(currentTemperature - cooling);
    }
}
