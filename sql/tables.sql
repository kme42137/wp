
CREATE TABLE `visitor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(80) NOT NULL,
  `firstName` varchar(80) NOT NULL,
  `lastName` varchar(80) NOT NULL,
  `eMail` varchar(100) NOT NULL,
  `password` varchar(60) NOT NULL,
  `isMerchant` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `merchant` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `visitor_id` bigint(20) NOT NULL,  
  `nameToDisplay` varchar(80) DEFAULT NULL,
  `introduction` TEXT DEFAULT NULL,
  `description` TEXT DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `MERCHANT_CONSTRAINT_1` FOREIGN KEY (`visitor_id`) REFERENCES `visitor` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  FULLTEXT ndx (`nameToDisplay`, `introduction`, `description`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `town` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(80) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `towns_of_merchant` (
 	`town_id` bigint(20) NOT NULL,
	`merchant_id` bigint(20) NOT NULL,
	PRIMARY KEY (`town_id`, `merchant_id`),
	CONSTRAINT `TOWNS_OF_MERCHANT_CONSTRAINT_1` FOREIGN KEY (`town_id`) REFERENCES `town` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `TOWNS_OF_MERCHANT_CONSTRAINT_2` FOREIGN KEY (`merchant_id`) REFERENCES `merchant` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `image` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `img_type` int(1) NOT NULL,
  `contact_id` bigint(20) NOT NULL,
  `location` varchar(200) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `IMAGE_CONSTRAINT` FOREIGN KEY (`contact_id`) REFERENCES `merchant` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=latin1;