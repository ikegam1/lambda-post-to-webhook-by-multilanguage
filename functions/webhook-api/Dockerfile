FROM golang:1.18.5

WORKDIR /var/task
COPY . /var/task
RUN mkdir /var/task/cache

ENV CGO_ENABLED=0 \
  GOOS=linux \
  GOARCH=amd64 \
  GOCACHE=/var/task/cache

RUN go mod init main \
  && go mod tidy \
  && go build

CMD ["go", "run", "/var/task/main.go"]
