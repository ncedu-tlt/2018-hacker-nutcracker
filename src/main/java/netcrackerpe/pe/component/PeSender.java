package netcrackerpe.pe.component;

import netcrackerpe.pe.controller.PeController;
import netcrackerpe.pe.entity.dao.PeDao;
import netcrackerpe.pe.service.PeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PeSender {

    private static final String TOPIC = "pe_topic";

    @Autowired
    private KafkaTemplate<String, PeDao> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplateString;

    @Autowired
    private PeService peService;

    @Autowired
    private PeController peController;

    @Scheduled(fixedRate = 2000)
    public void conversationWithCpe() {
        peController.sendPe();
        for (PeDao pe : peService.findAll()) {
            kafkaTemplate.send(TOPIC, pe);
        }
        String linkToAddCpe = "http://localhost:8081/pe/add";
        String linkToDeleteCpe = "http://localhost:8081/pe/delete";
        String linkToInternet = "http://localhost:8081/pe/fan";
        String totalString = linkToAddCpe + ',' + linkToDeleteCpe + ',' + linkToInternet;
        kafkaTemplateString.send(TOPIC, totalString);
    }
}
