git clone -b Dev https://github.com/Neoop1/twitter-spring-reactjs

# Stop all the containers

docker stop $(docker ps -a -q)

# Remove all the containers

docker rm $(docker ps -a -q)

## Build MVN without tests
mvn clean package -DskipTests

ssh -L 8080:192.168.7.105:8080 vmuser@192.168.7.105

ssh -t vmuser@192.168.7.87 "cd ./dev/twitter-spring-reactjs; exec \$SHELL -l"