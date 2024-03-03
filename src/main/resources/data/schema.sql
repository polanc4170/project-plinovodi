--                   --
-- Delete all tables --
--                   --

ALTER TABLE IF EXISTS user_interventions
DROP CONSTRAINT if EXISTS FK_interventions_intervention;

ALTER TABLE IF EXISTS user_interventions
DROP CONSTRAINT IF EXISTS FK_interventions_report;

DROP TABLE IF EXISTS holiday			CASCADE;
DROP TABLE IF EXISTS user_report		CASCADE;
DROP TABLE IF EXISTS user_intervention	CASCADE;
DROP TABLE IF EXISTS user_interventions	CASCADE;

--                   --
-- Create all tables --
--                   --

CREATE TABLE holiday (
	primary_key	BIGSERIAL		NOT NULL,
	local_date	DATE			NOT NULL,
	name		VARCHAR(255)	NOT NULL,
	PRIMARY KEY (primary_key)
);

CREATE TABLE user_report (
	primary_key	BIGSERIAL		NOT NULL,
	id			VARCHAR(64)		NOT NULL UNIQUE,
	first_name	VARCHAR(32)		NOT NULL,
	last_name	VARCHAR(32)		NOT NULL,
	date_start	DATE			NOT NULL,
	PRIMARY KEY (primary_key)
);

CREATE TABLE user_intervention (
	primary_key	BIGSERIAL		NOT NULL,
	id			VARCHAR(64)		NOT NULL UNIQUE,
	date_start	DATE			NOT NULL,
	hour_start	INTEGER			NOT NULL,
	hour_end	INTEGER			NOT NULL,
	desc_short	VARCHAR(50)		NOT NULL,
	desc_long	VARCHAR(500)	NOT NULL,
	PRIMARY KEY (primary_key)
);

--                       --
-- Create indexes		 --
--                       --

CREATE INDEX IDX_holiday_date
ON holiday (local_date);

CREATE INDEX IDX_holiday_name
ON holiday (name);

CREATE INDEX IDX_report_id
ON user_report (id);

CREATE INDEX IDX_report_full_name
ON user_report (first_name, last_name);

CREATE INDEX IDX_report_date
ON user_report (date_start);

CREATE INDEX IDX_intervention_id
ON user_intervention (id);

CREATE INDEX IDX_intervention_date
ON user_intervention (date_start);

--                       --
-- Create mapping tables --
--                       --

CREATE TABLE user_interventions (
	id_intervention	BIGINT NOT NULL UNIQUE,
	id_report		BIGINT NOT NULL
);

ALTER TABLE IF EXISTS user_interventions
ADD CONSTRAINT FK_interventions_intervention
FOREIGN KEY (id_intervention)
REFERENCES user_intervention;

ALTER TABLE IF EXISTS user_interventions
ADD CONSTRAINT FK_interventions_report
FOREIGN KEY (id_report)
REFERENCES user_report;
