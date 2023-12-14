Команда для сборки образа из докер-файла:

docker buildx build -f Dockerfile -t pstgrs-name:pstgrs-tag .

Команда для запуска образа:
docker run -d --restart=always -p 5432:5432 pstgrs-name:pstgrs-tag

Команда для сборки образов из докер-компоус файла:

$ docker-compose build

Команда для запуска образов из докер-компоус:

$ docker-compose up

Из полезного:

Эта команда позволяет останавливать и удалять контейнеры и другие ресурсы, созданные командой docker-compose up:

$ docker-compose down

Эта команда выводит журналы сервисов:

$ docker-compose logs -f [service name]

Например, в нашем проекте её можно использовать в таком виде: $ docker-compose logs -f [service name].

С помощью такой команды можно вывести список контейнеров:

$ docker-compose ps

Данная команда позволяет выполнить команду в выполняющемся контейнере:

$ docker-compose exec [service name] [command]

Например, она может выглядеть так: docker-compose exec server ls.

Такая команда позволяет вывести список образов:

$ docker-compose images