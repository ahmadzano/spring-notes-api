CREATE TABLE IF NOT EXISTS note (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  content varchar(999) DEFAULT NULL,
  type varchar(20) null,
  user_id bigint(20) NOT NULL,
  created_at datetime not null default current_timestamp,
  uuid varchar(50) null,
  PRIMARY KEY (id),
  CONSTRAINT note_user_fk FOREIGN KEY (user_id) REFERENCES user (id)
);
