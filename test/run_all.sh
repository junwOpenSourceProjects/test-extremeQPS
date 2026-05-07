#!/bin/bash
echo "Running level 1..."
wrk -t8 -c500 -d180s http://127.0.0.1:8080/api/level1 > level1.txt
echo "Running level 2..."
wrk -t8 -c500 -d180s http://127.0.0.1:8080/api/level2 > level2.txt
echo "Running level 3..."
wrk -t8 -c500 -d180s http://127.0.0.1:8080/api/level3 > level3.txt
echo "All done."
