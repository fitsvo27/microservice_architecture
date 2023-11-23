Команда для сборки образа из докер-файла:

docker buildx build -f Dockerfile -t pstgrs-name:pstgrs-tag .

Команда для запуска образа:
docker run -d --restart=always -p 5432:5432 pstgrs-name:pstgrs-tag