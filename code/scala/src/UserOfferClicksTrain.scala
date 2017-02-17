package main.scala

import java.nio.file.{ Files, Paths }
import scala.collection.mutable.ListBuffer
import java.io.FileWriter
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.functions._

object UserOfferClicksTrain {
	def main(args: Array[String]) {
		val conf = new SparkConf().setAppName("Simple Application")
				val sc = new SparkContext(conf)
				val sqlContext = new SQLContext(sc)
				import sqlContext._
				import sqlContext.implicits._
				//"at","be","br","ch","cz","de","dk","es",
				import java.io.FileWriter
				val countryCodes = Array("fi","ie","nb","nl","no","pl","pt","ru","se","uk","it","fr")
				for (code <- countryCodes){
					val inputfile = sqlContext.read
							.format("com.databricks.spark.csv") 
							.option("header", "true") // Use first line of all files as header
							.option("inferSchema", "true") // Automatically infer data types
							.option("delimiter", ",")
							.load("/data/sidana/recsysBaselines/bug_december/inputffm/train/userclicks/ffminput_"+code+".csv").withColumn("utcdate",unix_timestamp($"utcdate","yyyy-MM-dd HH:mm:ss").cast("timestamp")).withColumnRenamed("cast(unixtimestamp(utcdate,yyyy-MM-dd HH:mm:ss) as timestamp)","utcdate")

							val groupbyOffer = sqlContext.read
							.format("com.databricks.spark.csv") 
							.option("header", "true") // Use first line of all files as header
							.option("inferSchema", "true") // Automatically infer data types
							.option("delimiter", ",")
							.load("/data/sidana/recsysBaselines/bug_december/stats/offerClicks_"+code+".train")

							val inputuseroffercount = inputfile.join(groupbyOffer,inputfile("offeridoffers")<=>groupbyOffer("offerid"),"left_outer")
							val ordered = inputuseroffercount.orderBy("utcdate")
							ordered.rdd.coalesce(1,false).saveAsTextFile("/data/sidana/recsysBaselines/bug_december/inputffm/train/userofferclicks/temp/temp/ffminput_"+code+".csv")
				}
	}
}

