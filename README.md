# vehicle-inventory

# GET request:
# url:http://localhost:8080/api/vehicle/all

# POST request:
# url: http://localhost:8080/api/vehicle/
# Request:
{
  "modelId": "HondaCityBlack",
  "year": 2018,
  "make": "Honda",
  "model": "Honda City",
  "color": "Black",
  "mileage": 21,
  "vehicleType": "CAR"
}

# Response:

{
"id": 1,
"modelId": "HondaCityBlack",
"year": 2018,
"make": "Honda",
"model": "Honda City",
"color": "Black",
"mileage": 21,
"vehicleType": "CAR"
}

# PUT request:
# url:http://localhost:8080/api/vehicle/{modelId}
# Request:
{
"id": 2,
"modelId": "HondaCityBlue",
"year": 2018,
"make": "Honda",
"model": "Honda City",
"color": "Blue",
"mileage": 19,
"vehicleType": "CAR"
}

# DELETE request:
# url: http://localhost:8080/api/vehicle/{id}

Response: 204 No Content
