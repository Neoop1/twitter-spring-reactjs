
#!/bin/bash

set -e
set -u

# POSTGRES_MULTIPLE_DATABASES=tweet-test,chat-test,lists-test,notification-test,tag-test,topic-test


echo "Creating database 'tweet-test'"
psql -v ON_ERROR_STOP=1 --username postgres -c 'CREATE DATABASE "tweet-test"'
psql -v ON_ERROR_STOP=1 --username postgres -c 'GRANT ALL PRIVILEGES ON DATABASE "tweet-test" TO postgres;'


echo "Creating database 'chat-test'"
psql -v ON_ERROR_STOP=1 --username postgres -c 'CREATE DATABASE "chat-test"'
psql -v ON_ERROR_STOP=1 --username postgres -c 'GRANT ALL PRIVILEGES ON DATABASE "chat-test" TO postgres;'

echo "Creating database 'lists-test'"
psql -v ON_ERROR_STOP=1 --username postgres -c 'CREATE DATABASE "lists-test"'
psql -v ON_ERROR_STOP=1 --username postgres -c 'GRANT ALL PRIVILEGES ON DATABASE "lists-test" TO postgres;'

echo "Creating database 'notification-test'"
psql -v ON_ERROR_STOP=1 --username postgres -c 'CREATE DATABASE "notification-test"'
psql -v ON_ERROR_STOP=1 --username postgres -c 'GRANT ALL PRIVILEGES ON DATABASE "notification-test" TO postgres;';

echo "Creating database 'tag-test'"
psql -v ON_ERROR_STOP=1 --username postgres -c 'CREATE DATABASE "tag-test"'
psql -v ON_ERROR_STOP=1 --username postgres -c 'GRANT ALL PRIVILEGES ON DATABASE "tag-test" TO postgres;'

echo "Creating database 'topic-test'"
psql -v ON_ERROR_STOP=1 --username postgres -c 'CREATE DATABASE "topic-test"'
psql -v ON_ERROR_STOP=1 --username postgres -c 'GRANT ALL PRIVILEGES ON DATABASE "topic-test" TO postgres;'

echo "Creating database 'user-test'"
psql -v ON_ERROR_STOP=1 --username postgres -c 'CREATE DATABASE "user-test"'
psql -v ON_ERROR_STOP=1 --username postgres -c 'GRANT ALL PRIVILEGES ON DATABASE "user-test" TO postgres;'



###### Test

echo "Creating database 'tweet'"
psql -v ON_ERROR_STOP=1 --username postgres -c 'CREATE DATABASE "tweet"'
psql -v ON_ERROR_STOP=1 --username postgres -c 'GRANT ALL PRIVILEGES ON DATABASE "tweet" TO postgres;'


echo "Creating database 'chat'"
psql -v ON_ERROR_STOP=1 --username postgres -c 'CREATE DATABASE "chat"'
psql -v ON_ERROR_STOP=1 --username postgres -c 'GRANT ALL PRIVILEGES ON DATABASE "chat" TO postgres;'

echo "Creating database 'lists'"
psql -v ON_ERROR_STOP=1 --username postgres -c 'CREATE DATABASE "lists"'
psql -v ON_ERROR_STOP=1 --username postgres -c 'GRANT ALL PRIVILEGES ON DATABASE "lists" TO postgres;'

echo "Creating database 'notification'"
psql -v ON_ERROR_STOP=1 --username postgres -c 'CREATE DATABASE "notification"'
psql -v ON_ERROR_STOP=1 --username postgres -c 'GRANT ALL PRIVILEGES ON DATABASE "notification" TO postgres;';

echo "Creating database 'tag'"
psql -v ON_ERROR_STOP=1 --username postgres -c 'CREATE DATABASE "tag"'
psql -v ON_ERROR_STOP=1 --username postgres -c 'GRANT ALL PRIVILEGES ON DATABASE "tag" TO postgres;'

echo "Creating database 'topic'"
psql -v ON_ERROR_STOP=1 --username postgres -c 'CREATE DATABASE "topic"'
psql -v ON_ERROR_STOP=1 --username postgres -c 'GRANT ALL PRIVILEGES ON DATABASE "topic" TO postgres;'

echo "Multiple databases created"

