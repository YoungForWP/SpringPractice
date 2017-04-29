CREATE TABLE `CATEGORY` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  constraint pk_category_id primary key (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `COMMODITY` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `price` DOUBLE NOT NULL,
  `category_id` bigint(20) NOT NULL,
  `color` varchar(64),
  `specification` varchar(64),
  `description` varchar(64),
  `amount` DOUBLE NOT NULL,
  `picture` varchar(200),
  constraint pk_product_id primary key (`id`),
  constraint fk_product_catagory_id foreign key (`category_id`) references CATEGORY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


