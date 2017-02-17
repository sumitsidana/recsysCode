package main.scala

import java.nio.file.{ Files, Paths }
import scala.collection.mutable.ListBuffer
import java.io.FileWriter
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.functions._

object Popularity {
	def main(args: Array[String]) {
		val conf = new SparkConf().setAppName("Simple Application")
				val sc = new SparkContext(conf)
				val sqlContext = new SQLContext(sc)
				import sqlContext._
				import sqlContext.implicits._
				//"at",
				val countryCodes = Array("at","be","br","ch","cz","de","dk","es","fi","ie","nb","nl","no","pl","pt","ru","se","uk","it","fr")
				val fwTime = new FileWriter("/data/sidana/recsysBaselines/experiments/popcountryfiles/input/computepopularitems.txt", true)
				for (code <- countryCodes){
					val t1 = System.currentTimeMillis
							val csvFileClick = sqlContext.read
							.format("com.databricks.spark.csv")
							.option("header", "true") // Use first line of all files as header
							.option("inferSchema", "true") // Automatically infer data types
							.option("delimiter", "\t")
							.load("/data/sidana/recsysBaselines/experiments/tabseparatedinput/default/train_"+code+".csv") 

							val clickTemp = csvFileClick.filter(csvFileClick("rating") ==="1")

							val groupedByUsersandOffers = clickTemp.select(clickTemp("userid"),clickTemp("offerid")).distinct
							val groupedByOffers = groupedByUsersandOffers.groupBy("offerid").count.sort(desc("count"))
							val topItems = groupedByOffers.select("offerid").rdd.map(r => r(0)).take(30)
							//groupedByOffers.rdd.coalesce(1, false).saveAsTextFile("/tmp/sidana/popularOfferCountsTrain.csv")
							import java.nio.file.{ Files, Paths }
					import scala.collection.mutable.ListBuffer
					import java.io.FileWriter
					var items = new ListBuffer[String]()
					val fw = new FileWriter("/data/sidana/recsysBaselines/experiments/popcountryfiles/input/mostpopularitems_"+code+".txt", true)
					for( a <- 1 to 30){
						val firstval = topItems(a-1)
								items += firstval.toString.toString
								fw.write(firstval.toString.toString+" ")
								//items += ","
					}
					val t2 = System.currentTimeMillis
							println((t2 - t1) + " msecs")
							fwTime.write(code+","+(t2 - t1) + " msecs")
							fw.close()
				}
		fwTime.close()
	}
}

