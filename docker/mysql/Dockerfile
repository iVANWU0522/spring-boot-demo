FROM mysql:8

# Set environment variables for MySQL
ENV MYSQL_DATABASE=spring-boot-demo \
    MYSQL_USER=admin \
    MYSQL_PASSWORD=admin \
    MYSQL_ROOT_PASSWORD=root

# Copy custom MySQL configuration file to container
COPY my.cnf /etc/mysql/conf.d/

# Copy custom initialization script to container
COPY init.sql /docker-entrypoint-initdb.d/

# Expose MySQL port
EXPOSE 3306
