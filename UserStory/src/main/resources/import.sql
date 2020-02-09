INSERT INTO `user_roles`(`name`, `role_id`) VALUES ('ROLE_ADMIN', 1);
INSERT INTO `user_roles`(`name`, `role_id`) VALUES ('ROLE_USER', 2);
INSERT INTO `user` (`user_id`, `name`, `password`, `date_created`) VALUES (1,'testuser1','$2a$10$jXlure/BaO7K9WSQ8AMiOu3Ih3Am3kmmnVkWWHZEcQryZ8QPO3FgC','2015-11-15 22:14:54');
INSERT INTO `user` (`user_id`, `name`, `password`, `date_created`) VALUES (2,'testuser2','$2a$10$0tFJKcOV/Io6I3vWs9/Tju8OySoyMTpGAyO0zaAOCswMbpfma0BSK','2015-10-15 22:14:54');
INSERT INTO `user_authority`(`role_id`, `user_id`) VALUES (1, 1);
INSERT INTO `user_authority`(`role_id`, `user_id`) VALUES (2, 2);