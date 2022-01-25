$container_name = "pg-docker"
$postgres_username = "postgres"
$postgres_password = "postgres"
$postgres_db = "dev"

# Set path to PowerShell path temporarily
$scriptpath = $MyInvocation.MyCommand.Path
$dir = Split-Path $scriptpath
Push-Location $dir

docker run --rm --name $container_name -e POSTGRES_PASSWORD=$postgres_password -e POSTGRES_DB=$postgres_db -d -p 5432:5432 postgres
Start-Sleep -s 3
# Set Initialization data
docker cp .\schema.sql ${container_name}:/schema.sql
docker cp data.sql ${container_name}:/data.sql
docker exec $container_name psql -U $postgres_username -d $postgres_db -h localhost -f schema.sql
docker exec $container_name psql -U $postgres_username -d $postgres_db -h localhost -f data.sql

Pop-Location