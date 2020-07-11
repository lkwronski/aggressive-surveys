# aggressive-surveys

## How to run postgres
```
sudo docker-compose up -d postgres
```

## How to build ui image and run
```
sudo docker build -t ionic-preview -f dev-script/DockerfileUi .
docker run -p 8100:8100 -it ionic-preview
```

How to build project
```
sudo docker-compose up -d
mvn package docker:build
sudo docker-compose down
docker build -f  dev-script/DockerfileUi -t ui:v6 .
cd dev-script && sudo docker-compose up -d
```