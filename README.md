# oye-rickshaw
## Assumptions
1. I have added the max limit of vehicles that can be returned via the API as 500.

## Approach
The project consists of two APIs:

1. Location Tracking API: The PUT API takes in a JSON value and indexes the updated location of the vehicle in elasticsearch. It also indexes fields like vehicle number, vehicle type and owner name for further use.

Sample Request JSON:

{
  "location": {
    "lat": 28.566394,
    "lon": 77.204838
  },
  "ownerName": "Abhishek",
  "vehicleNo": "DL9CD5019",
  "vehicleType": "CAR"
}

2. Get Nearby Vehicles API: The GET API takes lat and lon parameters(user's current location) and returns a list of vehicles within 200 kms. This is done through elasticsearchs geo distance query. Vehicle number, owner name and vehicle type is also returned along with the last updated location of the vehicle.

Sample Request:
{
  "location": {
    "lat": 28.564321,
    "lon": 77.205249
  }
}

Sample Response:
{
  "message": "Found 2 vehicles within 200 KMS.",
  "status": true,
  "code": 200,
  "vehicles": [
    {
      "vehicleNo": "DL9CD5019",
      "vehicleType": "CAR",
      "ownerName": "Abhishek",
      "location": {
        "lat": 28.566394,
        "lon": 77.204838
      }
    },
    {
      "vehicleNo": "DL3CCK3179",
      "vehicleType": "CAR",
      "ownerName": "vaibhav",
      "location": {
        "lat": 28.575998,
        "lon": 77.219382
      }
    }
  ]
}

## Prerequisites and setup
1. This application needs elasticsearch to be installed on local system.

For mac os 10 and later

you can download elasticsearch using homebrew using the following command:

1. brew tap elastic/tap

2. brew install elastic/tap/elasticsearch-full

After Elasticsearch has been installed it can be started using the command "elasticsearch" from mac os terminal. you can verify if elasticsearch has started by opening http://localhost:9200 on the browser.

For installing elasticsearch on ubuntu, run the following commands:

1. wget -qO - https://artifacts.elastic.co/GPG-KEY-elasticsearch | sudo apt-key add -
2. sudo apt-get install apt-transport-https
3. echo "deb https://artifacts.elastic.co/packages/7.x/apt stable main" | sudo tee /etc/apt/sources.list.d/elastic-7.x.list
4. sudo apt-get update && sudo apt-get install elasticsearch

After installing elasticsearch can be started or stopped by using the command:
sudo -i service elasticsearch start
sudo -i service elasticsearch stop

## Creating Index mapping 

1. Once elasticsearch has started, issue the following command to create the index and appropriate mapping:

curl -X PUT "localhost:9200/vehicle_location_tracking?pretty" -H 'Content-Type: application/json' -d'
{
  "settings": {
    "number_of_shards": 10,
    "number_of_replicas": 1
  },
  "mappings": {
    "properties": {
      "vehicleNo":{
        "type": "keyword"
      },
      "vehicleType":{
        "type": "keyword"
      },
      "ownerName":{
        "type": "text"
      },
      "location": {
        "type": "geo_point"
      }
    }
  }
}
'

## Starting the application

1. Clone this repository and use the cd command to go inside the "oye-rickshaw-assignment" folder.
2. Build the project using the command "mvn clean install". 
3. The API can now be deployed through the command "java -jar target/oye-rickshaw-backend-1.0.jar".
4. You can check the API documentation and test the API by going to the URL: http://localhost:8093/swagger-ui.html#/
