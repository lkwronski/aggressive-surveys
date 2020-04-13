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
