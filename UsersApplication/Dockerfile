FROM postgres:alpine3.18
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=postgres
ENV POSTGRES_DB=users
COPY init.sql /docker-entrypoint-initdb.d/
LABEL maintainer="v.fits"
VOLUME /tmp
EXPOSE 5432