CREATE DATABASE  IF NOT EXISTS `projekt-zespołowy_tracker`;
USE `projekt-zespołowy_tracker`;

-- nowa baza
CREATE TABLE `user_profile` (
  `user_profile_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) BINARY NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  -- `role` varchar(45) DEFAULT NULL,
  -- opcjonalne info
  `sex` CHAR(1) DEFAULT NULL,
  `about_me` TEXT DEFAULT NULL,
  
  -- NA POZNIEJ
  -- adres jako odniesienie do innej tabeli

  PRIMARY KEY (`user_profile_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE `user_friend` (
	`user_id` BIGINT(11) NOT NULL,
	`friend_id` BIGINT(11) NOT NULL,
	PRIMARY KEY (`user_id`, `friend_id`),
	CONSTRAINT `FK_EMP` FOREIGN KEY (`user_id`) REFERENCES `user_profile` (`user_profile_id`),
	CONSTRAINT `FK_COL` FOREIGN KEY (`friend_id`) REFERENCES `user_profile` (`user_profile_id`)
);

CREATE TABLE `friend_request` (
	`friend_request_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
	`user_id` BIGINT(11) NOT NULL,
    `requester_id`BIGINT(11) NOT NULL,
    `requester_username` VARCHAR(45) BINARY NOT NULL,
    -- `friend_request_status` INT(1) NOT NULL,
    
	PRIMARY KEY (`friend_request_id`),
	KEY `user_id` (`user_id`),
	CONSTRAINT `FK_USER` FOREIGN KEY (`user_id`) REFERENCES `user_profile` (`user_profile_id`)
);

CREATE TABLE `message` (
  `message_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `message` TEXT DEFAULT NULL,
  `author` varchar(50) NOT NULL,
  -- date
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `user_profile_message` (
  `user_profile_id` BIGINT(11) NOT NULL,
  `message_id` BIGINT(11) NOT NULL,
  PRIMARY KEY (`user_profile_id`,`message_id`),
  KEY `message_id` (`message_id`),
  
  
  CONSTRAINT `user_profile_message_fk_1` 
   FOREIGN KEY (`user_profile_id`) REFERENCES `user_profile` (`user_profile_id`),
   
  CONSTRAINT `user_profile_message_fk_2` 
   FOREIGN KEY (`message_id`) REFERENCES `message` (`message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `event` (
  `event_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `event_name` varchar(50) DEFAULT NULL,
  `host` varchar(50) NOT NULL,
  `start_date` TIMESTAMP,
  PRIMARY KEY (`event_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

CREATE TABLE `user_profile_event` (
  `user_profile_id` BIGINT(11) NOT NULL,
  `event_id` BIGINT(11) NOT NULL,
  PRIMARY KEY (`user_profile_id`,`event_id`),
  KEY `event_id` (`event_id`),
  
  
  CONSTRAINT `user_profile_event_fk_1` 
   FOREIGN KEY (`user_profile_id`) REFERENCES `user_profile` (`user_profile_id`),
   
  CONSTRAINT `user_profile_event_fk_2` 
   FOREIGN KEY (`event_id`) REFERENCES `event` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*USUWANIE BAZY DANYCH

DROP TABLE user_profile_event;
DROP TABLE event;
DROP TABLE friend_request;
DROP TABLE user_profile_message;
DROP TABLE message;
DROP TABLE user_friend;
DROP TABLE user_profile;
*/
