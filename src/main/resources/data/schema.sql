--                   --
-- Delete all tables --
--                   --

DROP TABLE IF EXISTS holiday			CASCADE;
DROP TABLE IF EXISTS user_form			CASCADE;
DROP TABLE IF EXISTS user_intervention	CASCADE;
DROP TABLE IF EXISTS user_interventions	CASCADE;

--                   --
-- Create all tables --
--                   --

CREATE TABLE holiday (
	id			BIGSERIAL		NOT NULL,
	local_date	DATE			NOT NULL,
	name		VARCHAR(255)	NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE user_form (
	id			BIGSERIAL		NOT NULL,
	uuid		VARCHAR(64)		NOT NULL UNIQUE,
	first_name	VARCHAR(32)		NOT NULL,
	last_name	VARCHAR(32)		NOT NULL,
	local_date	DATE			NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE user_intervention (
	id			BIGSERIAL		NOT NULL,
	uuid		VARCHAR(64)		NOT NULL UNIQUE,
	local_date	DATE			NOT NULL,
	hour_start	INTEGER			NOT NULL,
	hour_end	INTEGER			NOT NULL,
	desc_short	VARCHAR(50)		NOT NULL,
	desc_long	VARCHAR(500)	NOT NULL,
	PRIMARY KEY (id)
);

--                       --
-- Create mapping tables --
--                       --

CREATE TABLE user_interventions (
	intervention_id	BIGINT NOT NULL UNIQUE,
	form_id			BIGINT NOT NULL
);

ALTER TABLE IF EXISTS user_interventions
ADD CONSTRAINT FKgi3lc7ft7jslmtu5ivxa6c811
FOREIGN KEY (intervention_id)
REFERENCES user_intervention;

ALTER TABLE IF EXISTS user_interventions
ADD CONSTRAINT FKrs4nhepnda6kiff94ypsx78d1
FOREIGN KEY (form_id)
REFERENCES user_form;
