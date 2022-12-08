CREATE DATABASE  IF NOT EXISTS `service_storage` CHARACTER SET utf8 COLLATE utf8_general_ci;
CREATE DATABASE  IF NOT EXISTS `service_waste` CHARACTER SET utf8 COLLATE utf8_general_ci;
CREATE USER `yokudlela`@`%` IDENTIFIED BY `yokudlela`;
GRANT ALL PRIVILEGES ON `service_storage`.* TO `yokudlela`@`%`; 
GRANT ALL PRIVILEGES ON `service_waste`.* TO `yokudlela`@`%`; 