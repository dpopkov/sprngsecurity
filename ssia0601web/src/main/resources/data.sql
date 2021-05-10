INSERT IGNORE INTO `ssiaweb`.`user` (`id`, `username`, `password`, `algorithm`)
    VALUES ('1', 'jane', '$2a$10$EmbUbFOhDZEUf6W/wpKQGuVtP2dJrSyAMGltrbCoCnxIOoBTTHVYC', 'BCRYPT');
INSERT IGNORE INTO `ssiaweb`.`authority` (`id`, `name`, `user`) VALUES ('1', 'READ', '1');
INSERT IGNORE INTO `ssiaweb`.`authority` (`id`, `name`, `user`) VALUES ('2', 'WRITE', '1');
INSERT IGNORE INTO `ssiaweb`.`product` (`id`, `name`, `price`, `currency`) VALUES ('1', 'Chocolate', '10', 'USD');
