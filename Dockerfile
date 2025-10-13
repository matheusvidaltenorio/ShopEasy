FROM postgres:16-alpine

EXPOSE 5432

ENV POSTGRES_DB=shopeasy \
    POSTGRES_USER=postgres \
    POSTGRES_PASSWORD=root
