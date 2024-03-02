--                   --
-- Delete all tables --
--                   --

ALTER TABLE IF EXISTS user_interventions
DROP CONSTRAINT if EXISTS FKgi3lc7ft7jslmtu5ivxa6c811;

ALTER TABLE IF EXISTS user_interventions
DROP CONSTRAINT IF EXISTS FKrs4nhepnda6kiff94ypsx78d1;

DROP TABLE IF EXISTS holiday			CASCADE;
DROP TABLE IF EXISTS user_report		CASCADE;
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

CREATE TABLE user_report (
	id			BIGSERIAL		NOT NULL,
	uuid		VARCHAR(64)		NOT NULL UNIQUE,
	first_name	VARCHAR(32)		NOT NULL,
	last_name	VARCHAR(32)		NOT NULL,
	date_start	DATE			NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE user_intervention (
	id			BIGSERIAL		NOT NULL,
	uuid		VARCHAR(64)		NOT NULL UNIQUE,
	date_start	DATE			NOT NULL,
	hour_start	INTEGER			NOT NULL,
	hour_end	INTEGER			NOT NULL,
	desc_short	VARCHAR(50)		NOT NULL,
	desc_long	VARCHAR(500)	NOT NULL,
	PRIMARY KEY (id)
);

--                       --
-- Create indexes		 --
--                       --

CREATE INDEX IDX6lmm4sm5el6pkdvjgf0pmbr2t
ON user_intervention (uuid);

CREATE INDEX IDXng6akgkljq0vge2aq9aaw03l6
ON user_report (uuid);

CREATE INDEX IDXlk4kv6ygiea512vyqm7hjoigd
ON user_report (first_name, last_name);

CREATE INDEX IDXfqrwkkck6luw4jqk642y0376d
ON user_report (date_start);

--                       --
-- Create mapping tables --
--                       --

CREATE TABLE user_interventions (
	intervention_id	BIGINT NOT NULL UNIQUE,
	report_id		BIGINT NOT NULL
);

ALTER TABLE IF EXISTS user_interventions
ADD CONSTRAINT FKgi3lc7ft7jslmtu5ivxa6c811
FOREIGN KEY (intervention_id)
REFERENCES user_intervention;

ALTER TABLE IF EXISTS user_interventions
ADD CONSTRAINT FKrs4nhepnda6kiff94ypsx78d1
FOREIGN KEY (report_id)
REFERENCES user_report;
