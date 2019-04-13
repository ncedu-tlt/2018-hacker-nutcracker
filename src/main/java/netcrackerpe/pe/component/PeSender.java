package netcrackerpe.pe.component;


import netcrackerpe.pe.controller.PeController;
import netcrackerpe.pe.entity.dao.PeDao;
import netcrackerpe.pe.service.PeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PeSender {

    private static final String TOPIC = "TOPIC_PE";
    private static Integer counter = 0;

    @Autowired
    private KafkaTemplate<String, List<PeDao>> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplateString;

    @Autowired
    private PeService peService;

    @Autowired
    private PeController peController;

    @Scheduled(fixedRate = 5000)
    public void conversationWithCpe() {
        peController.sendPe();
        System.out.println(peService.findAll());

        kafkaTemplate.send(TOPIC, counter.toString(), peService.findAll());
        String linkToAddCpe = "http://localhost:8081/pe/add";
        String linkToDeleteCpe = "http://localhost:8081/pe/delete";
        String linkToInternet = "http://localhost:8081/pe/fan";
        String totalString = linkToAddCpe + ',' + linkToDeleteCpe + ',' + linkToInternet;
        kafkaTemplateString.send(TOPIC, counter.toString(), totalString);
        counter++;
    }
}
