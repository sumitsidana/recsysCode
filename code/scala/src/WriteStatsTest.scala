package main.scala

import java.nio.file.{ Files, Paths }
import scala.collection.mutable.ListBuffer
import java.io.FileWriter
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.functions._

object WriteStatsTest {
	def main(args: Array[String]) {
		val conf = new SparkConf().setAppName("Simple Application")
				val sc = new SparkContext(conf)
				val sqlContext = new SQLContext(sc)
				import sqlContext._
				import sqlContext.implicits._
				//"at","be","br","ch","cz","de","dk","es",
				val countryCodes = Array("fi","ie","nb","nl","no","pl","pt","ru","se","uk","it","fr")

				for (code <- countryCodes){
							val inputfile = sqlContext.read
							.format("com.databricks.spark.csv") 
							.option("header", "true") // Use first line of all files as header
							.option("inferSchema", "true") // Automatically infer data types
							.option("delimiter", ",")
							.load("/data/sidana/recsysBaselines/bug_december/tsplitfiles/test_"+code+".csv")

							val interactions  =  inputfile.filter(!($"useridclicks" === "null"))
							val groupbyUser = interactions.groupBy("useridclicks").count.sort(desc("count"))
							val groupbyOffer = interactions.groupBy("offeridclicks").count.sort(desc("count"))

							groupbyUser.rdd.coalesce(1,false).saveAsTextFile("/data/sidana/recsysBaselines/bug_december/stats/temp/temp/userClicks_"+code+".test")
							groupbyOffer.rdd.coalesce(1,false).saveAsTextFile("/data/sidana/recsysBaselines/bug_december/stats/temp/temp/offerClicks_"+code+".test")

				}
	}
}

