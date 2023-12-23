from pyspark import SparkContext


def main():
    sc = SparkContext(appName='SparkWordCount')

    # 读取input文件夹下的所有txt文件，并将内容转换为小写
    files_rdd = sc.textFile("../wordCount/input/*.txt").flatMap(lambda x: x.lower().split())

    # 关键词列表
    keywords = ["american", "chinese", "girl", "women", "adventure", "human", "novel", "societal", "tale"]

    # 过滤出包含关键词的单词
    filtered_words = files_rdd.filter(lambda word: word in keywords)

    # 将关键词作为键，出现次数为值，进行计数
    counts = filtered_words.map(lambda word: (word, 1)).reduceByKey(lambda a, b: a + b)

    # 将所有统计结果合并在一起
    combined_counts = counts.reduceByKey(lambda a, b: a + b)

    # 按照词频数量排序
    sorted_counts = combined_counts.sortBy(lambda x: x[1], ascending=False)

    # 保存结果为单个输出文件
    sorted_counts.coalesce(1).saveAsTextFile('./output')  # 将结果合并成一个文件

    # 停止SparkContext
    sc.stop()


if __name__ == '__main__':
    main()
