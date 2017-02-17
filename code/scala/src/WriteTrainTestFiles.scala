package main.scala

import java.nio.file.{ Files, Paths }
import scala.collection.mutable.ListBuffer
import java.io.FileWriter
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.functions._

object WriteTrainTestFiles {
	def main(args: Array[String]) {
		val conf = new SparkConf().setAppName("Simple Application")
				val sc = new SparkContext(conf)
				val sqlContext = new SQLContext(sc)
				import sqlContext._
				import sqlContext.implicits._
				//"at","be","br","ch","cz","de","dk","es",
				val countryCodes = Array("fi","ie","nb","nl","no","pl","pt","ru","se","uk","it","fr")


				val fw = new FileWriter("/data/sidana/recsysBaselines/bug_december/inputfile/inputfile.txt", true)
				for (code <- countryCodes){
					val t1 = System.currentTimeMillis
							val offers = sqlContext.read
							.format("com.databricks.spark.csv")
							.option("header", "true") // Use first line of all files as header
							.option("inferSchema", "true") // Automatically infer data types
							.option("delimiter", ",")
							.load("/data/sidana/recsysBaselines/bug_december/rawfiles/rawFile_"+code+".csv").withColumn("utcdate",unix_timestamp($"utcdate","yyyy-MM-dd HH:mm:ss").cast("timestamp")).withColumnRenamed("cast(unixtimestamp(utcdate,yyyy-MM-dd HH:mm:ss) as timestamp)","utcdate")


							val csvOuterJoin = offers.orderBy("utcdate")
							val totalItems = csvOuterJoin.count
							val cnt = totalItems * 0.7

							val trainDF = sqlContext.createDataFrame(csvOuterJoin.rdd.zipWithIndex.filter {
							case (_, i) => i <= cnt 
							}.map(_._1), csvOuterJoin.schema)

							val testDF = sqlContext.createDataFrame(csvOuterJoin.rdd.zipWithIndex.filter {
							case (_, i) => i > cnt 
							}.map(_._1), csvOuterJoin.schema)

							//form distinct values
							val inputTrain = trainDF.select("useridclicks","offeridclicks","useridoffers","offeridoffers","countrycode","category","merchant","utcdate").distinct
							val inputTest = testDF.select("useridclicks","offeridclicks","useridoffers","offeridoffers","countrycode","category","merchant","utcdate").distinct


							inputTrain.rdd.coalesce(1,false).saveAsTextFile("/data/sidana/recsysBaselines/bug_december/inputfile/train_"+code+".csv")
							inputTest.rdd.coalesce(1,false).saveAsTextFile("/data/sidana/recsysBaselines/bug_december/inputfile/test_"+code+".csv")
							val t2 = System.currentTimeMillis
							println((t2 - t1) + " msecs")
							fw.write("code,"+(t2 - t1) + " msecs")
				}
		fw.close()
	}
}

