# 纽约租房信息可视化系统

源数据是来自于美国爱彼迎（Airbnb）公司，有关于纽约市的租房数据的
汇总。该数据来自于爱彼迎公司对于其注册用户所上传的信息的收集与汇
总，该数据中为我们展示了美国纽约州纽约市的租房情况。

---

## 技术使用

**编程语言：** `Java+Scala+Sql`

**技术或工具：** `Spark+Flume+Hive+MySql+Kettle`

## 详细介绍

基于 SparkSession 服务以及 Spark Streaming 服务编写代码，并将编写完毕的
代码打包发送致虚拟机运行，通过使用从 Flume 中获取到的数据，以实现对原始
的数据实现数据的清洗。并借助于 spark.implicits 服务在代码当中编入 Hive 写入
代码以实现在 Hive 之中建立数仓的目的。之后使用 Kettle 工具连接好 Hive 与
MySql 从而实现将 Hive 的数据迁移到 MySql 数据。在最后我们设计好后端代码，
为前端使用 MySql 设计接口，并设定运行架构。最后在前端调用接口，通过 Ajax
请求获取后端数据实现可视化。

---
## 结果展示

![image](https://github.com/user-attachments/assets/d4822016-8e6d-4a4f-9ec7-eb6a5ab554b3)


