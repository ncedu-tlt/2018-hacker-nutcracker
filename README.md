Необходимые порты: 8080, 8081, 8082 (9092 - NiFi)
1.Запустить кафку:

.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
.\bin\windows\kafka-server-start.bat .\config\server.properties

.\bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic cpe_topic
.\bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic pe_topic
.\bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic total_topic

2. Запустить Nifi

3.Включить MonitoringService

4.Включить CpeService

5.Включить PeService

6.В браузере: http://localhost:8082/service/welcome
