#!/bin/bash
COUNTER=0
while [  $COUNTER -lt 200 ]; do
	amnt=$RANDOM
   curl -X POST --header 'Content-Type: application/json' http://127.0.0.1:8080/transactions -d "{\"amount\": $amnt, \"timestamp\": $(date +%s000)}"
   let COUNTER=COUNTER+1
done
