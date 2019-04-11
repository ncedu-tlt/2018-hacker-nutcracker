package com.netcracker.edu.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

	@KafkaListener ( topics = "total_topic" )
	public void consume (String message) {
		System.out.println(message);

//		GoodsItem[] arrItemsDes = new Gson().fromJson(jsonStr, itemsArrType);
//		System.out.println(Arrays.toString(arrItemsDes));

		//Наверное сплитим по "]" и потом получившиеся массивы объектов десереализуем
		//https://habr.com/ru/post/253266/
	}
}