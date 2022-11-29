# shared-prices-tracker

POC for tracking share prices for a given companies from 3-d party API.

## Table of Contents

* [General Info](#general-information)
* [Technologies Used](#technologies-used)
* [Features](#features)
* [Usage](#usage)
* [Project Status](#project-status)
* [Room for Improvement](#room-for-improvement)

## General Information

THe POC application written on kotlin, hexagonal architecture(ports and adapters), DDD , CQS, MongoDb. The purpose of
the project is to build a system that interact with a 3-d parti API in order to get the latest share prices for knowing
companies. Company share price retrieves every 20 sec from external API and saves in the DB.



## Technologies Used

- Kotlin
- Spring Boot 
- MongoDB
- maven
- MockK
- kotest

## Features

- The user can retrieve the list of all companies
- The user is able to choose between hourly, daily or weekly time series of the company share price

## Usage

* There are two use cases:

1) Get general information of all companies in the system. Request -
   `   curl --location --request GET http://localhost:8080/v1/companies   `

2) Get share prices data by company ID and time window, allowed time window units - HOURS, DAYS, WEEKS.

Request -
`
curl --location --request GET http://localhost:8080/v1/companies/dbd0059f-9a0e-34fd-ba31-1944148fe638 \
--header 'Content-Type: application/json' \
--data-raw '{
"duration": 1,
"dateUnit": "DAYS"
}'      `

## Project Status

Project is: _in progress_

## Room for Improvement

Room for improvement:

- in order to have all historical share price data the document versioning pattern 
can be implemented in order to keep track of changes to document - share-prices

TODO:
- allow adding companies to the system
- implement http client for 3-d party API
- improve error handling
- more testing
- dockerized application
- make companies retrieval pageable
- implement bach calls to the external API to get share prices for multiple companies


