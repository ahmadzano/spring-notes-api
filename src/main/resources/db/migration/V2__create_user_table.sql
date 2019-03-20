CREATE TABLE IF NOT EXISTS user (
	id BIGINT NOT NULL AUTO_INCREMENT,
	username varchar(100) NOT NULL UNIQUE,
	email varchar(100) NULL,
	is_active smallint not null default 0,
 	created_at datetime not null default current_timestamp,
	PRIMARY KEY (id)
);
