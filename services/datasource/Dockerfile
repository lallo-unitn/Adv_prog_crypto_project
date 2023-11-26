FROM postgres:latest

COPY backup.sql /docker-entrypoint-initdb.d/

# ADD ./backup.sql /docker-entrypoint-initdb.d

CMD ["postgres"]
