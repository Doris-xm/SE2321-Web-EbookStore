from flask import Flask, jsonify
import os
import subprocess
from flask_cors import CORS
app = Flask(__name__)
CORS(app, supports_credentials=True)
@app.route('/hot_word', methods=['GET'])
def process_request():
    # 读取 Spark 生成的输出文件内容
    output_file_path = './output/part-00000'
    if not os.path.exists(output_file_path):
        return jsonify({'error': 'Output file not found'})

    with open(output_file_path, 'r') as file:
        lines = file.readlines()

    # 整理文件内容为 JSON 数组格式
    result = []
    for line in lines:
        parts = line.strip().replace('(', '').replace(')', '').split(',')
        word = parts[0].strip().replace("'", "")
        count = int(parts[1].strip())
        result.append({'word': word, 'count': count})

    # 返回整理后的 JSON 数组
    return jsonify(result)

@app.route('/update_hot_word', methods=['GET'])
def update_hot_word():
    try:
        # 删除 output 文件夹
        subprocess.run(['rm', '-rf', 'output'])

        # 执行 Spark 应用程序
        spark_submit_cmd = '/Users/dxm/IntellijProjects/spark-3.5.0-bin-hadoop3/bin/spark-submit --class "WordCount_Spark" --master local main.py'
        subprocess.run(spark_submit_cmd, shell=True)
        return jsonify({'status': 'Script executed successfully'})
    except subprocess.CalledProcessError as e:
        return jsonify({'error': f'Script execution failed: {e}'})


if __name__ == '__main__':
    app.run(host='127.0.0.1', port=8010)  # 在 8010 端口启动 Flask 应用
