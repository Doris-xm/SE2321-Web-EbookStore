rm -rf output
/Users/dxm/IntellijProjects/spark-3.5.0-bin-hadoop3/bin/spark-submit \
  --class "WordCount_Spark" \
  --master local \
  main.py