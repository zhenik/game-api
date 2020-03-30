#!/usr/bin/env bash
for collection in $(ls ./seed | cut -d'.' -f1); do mongoimport -h mongo -d app --authenticationDatabase admin --type=json --jsonArray -u root -p example --collection ${collection} --file ${collection}.json; done
