package netcrackerpe.pe.controller;

import netcrackerpe.pe.entity.dao.PeDao;
import netcrackerpe.pe.entity.dto.CpeDto;
import netcrackerpe.pe.entity.dto.PeDto;
import netcrackerpe.pe.kafka.config.KafkaConfiguration;
import netcrackerpe.pe.service.PeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/pe")
public class PeController {

    @Autowired
    private KafkaConfiguration kafkaConfiguration;

    @Autowired
    private PeService peService;

    private List<PeDao> peDaoList = new ArrayList<>();


    @GetMapping(value = "/all")
    public List<PeDao> getAll() {
        return peService.findAll();
    }

    @PostMapping("/add")
    public void add(@RequestBody PeDao pe) {
        peService.savePe(pe);
    }

    @PostMapping("/delete")
    public void deleteByIp(@RequestBody String ip) {
        peService.deletePe(ip);
    }

    @GetMapping("/{ip}")
    public PeDao getPeByIp(@PathVariable("ip") String ip) {
        return peService.getPeByIp(ip);
    }

    @PostMapping("/fan")
    public @ResponseBody
    ResponseEntity<HttpStatus> changeFanStatus(@RequestBody PeDto peDto) {
        PeDao peDao = peService.getPeByIp(peDto.getIp());
        if (peDao.isFanActive() == peDto.isFanActive()) {
            return new ResponseEntity<HttpStatus>(HttpStatus.CONFLICT);
        } else {
            peDao.setFanActive(peDto.isFanActive());
            peService.savePe(peDao);
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        }
    }

    @PostMapping("/сpeData")
    public void adoption(@RequestBody List<CpeDto> list) {

        List<PeDao> listPe = peService.findAll();
        Integer speed = 0;

        for (int i = 0; i < listPe.size(); i++) {
            for (int s = 0; s < list.size(); s++) {
                if (list.get(s).getPeIpAddress().equals(listPe.get(i).getIp())) {
                    speed += list.get(s).getDownlinkSpeed();
                }
            }
            peService.getPeByIp(listPe.get(i).getIp()).setDownlinkSpeed(speed);
            peService.savePe(peService.getPeByIp(listPe.get(i).getIp()));
            speed = 0;
        }
    }

    @PostMapping("/sendPe")
    public void sendPe() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8080/cpe/peData";
            List<PeDao> list = peService.findAll();
            restTemplate.postForEntity(url, list, List.class);
        } catch (Exception e) { }
    }
}