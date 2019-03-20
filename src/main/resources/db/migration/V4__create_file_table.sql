CREATE TABLE IF NOT EXISTS file(
  id bigint NOT NULL AUTO_INCREMENT,
  filename varchar(255) NOT NULL,
  extenstion varchar(255) NULL,
  mime_type varchar(255) NULL,
  uuid varchar(50) null,
  user_id bigint NOT NULL,
  created_at datetime not null default current_timestamp,
	PRIMARY KEY (id),
	CONSTRAINT file_user_fk FOREIGN KEY (user_id) REFERENCES user (id)
);