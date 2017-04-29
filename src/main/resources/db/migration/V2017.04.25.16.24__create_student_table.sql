CREATE TABLE `STUDENT` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `age` bigint(20) NOT NULL,
  `name` varchar(64) NOT NULL,
  `gender` varchar(64) NOT NULL,
  `class_id` bigint(20) NOT NULL,
  constraint pk_student_id primary key (`id`),
  constraint fk_student_class_id foreign key (`class_id`) references KCLASS(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;