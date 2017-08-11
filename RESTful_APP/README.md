[![Build Status](https://circleci.com/gh/xtbmaster/RESTful_APP.svg?style=shield&circle-token=:circle-token)](https://circleci.com/gh/xtbmaster/RESTful_APP/6)

# restParserTest

This rest application is used to parse files in a parallel way with up to three processes. Parsed data are stored into a database.
There are two endpoints you can use:

**/upload-form.jsp** - which provides a form to upload text files to parse 
and
**/words** - which displays all parsed values and the amount of its occurencies.

**Note**: files should contain values, separated by comma and a space e.g. _"a, b, c, d, e, f, g, h"_

In order to run the app please create a database in _postgresql_ with the following query:

**CREATE DATABASE test_db
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'en_US.UTF-8'
       LC_CTYPE = 'en_US.UTF-8'
       CONNECTION LIMIT = -1;**

then create a table:

**CREATE TABLE public.parsed_file
(
  word_id integer NOT NULL,
  count integer,
  value character varying(255),
  CONSTRAINT parsed_file_pkey PRIMARY KEY (word_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.parsed_file
  OWNER TO postgres;**

After setting up a database, you can clone the source code and start the app through your IDE via JBoss WildFly container 
or manually copying the war file into
**standalone/deployments** folder of the WildFly server package and run **bin/standalone.sh (.bat)** 
Please use the following url to access the endpoints:
**localhost:8080/com.ArtSoft.restParser-1.0-SNAPSHOT/your_endpoint_here**

at the end you shoud get something like


[
   {
      "count": 32,
      "value": "a"
   },
   {
      "count": 15,
      "value": "b"
   },
   {
      "count": 34,
      "value": "c"
   },
   ...
]


Used frameworks and technologies:

- Java 8
- Maven
- Spring
- JBoss WildFly
- PostgreSQL
- Hibernate

