package main.scala

import java.nio.file.{ Files, Paths }
import scala.collection.mutable.ListBuffer
import java.io.FileWriter
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.functions._

object Parquet2CSV {
	def main(args: Array[String]) {
		val conf = new SparkConf().setAppName("Simple Application")
				val sc = new SparkContext(conf)
				val sqlContext = new SQLContext(sc)
				import sqlContext._
				import sqlContext.implicits._
				//"at",
				val countryCodes = Array("at","be","br","ch","cz","de","dk","es","fi","ie","nb","nl","no","pl","pt","ru","se","uk","it","fr")
				val fw = new FileWriter("/home/ama/sidana/recsysBaselines/data/input/inputfile.txt", true)

				for (code <- countryCodes){

					val t1 = System.currentTimeMillis
							val clicksTemp = sqlContext.read.parquet("/home/ama/sidana/calypso_kk_june_data/click/"+code+"/2016/*/*/")
							val userClicks = clicksTemp.select(clicksTemp("userId"),substring_index(clicksTemp("offerViewId"),"-",1)).withColumnRenamed("substring_index(offerViewId,-,1)","offerIdClicks").withColumnRenamed("userId","userIdClicks")


							val parquetFileOffers = sqlContext.read.parquet("/home/ama/sidana/calypso_kk_june_data/offerView/"+code+"/2016/*/*/")
							val userOffers = parquetFileOffers.select(parquetFileOffers("userId"),substring_index(parquetFileOffers("offerViewId"),"-",1),$"siteDomain".getItem("countryCode"),parquetFileOffers("category")(0),parquetFileOffers("merchant"),parquetFileOffers("utcDate")).withColumnRenamed("substring_index(offerViewId,-,1)","offerIdOfferView").withColumnRenamed("userId","userIdOfferView")

							//inner join
							val userOfferCommon = userClicks.join(userOffers,userClicks("userIdClicks")<=>userOffers("userIdOfferView")).drop(userClicks("userIdClicks")).drop(userClicks("offerIdClicks"))


							//outerjoin
							val cOOuterJoin = userClicks.join(userOfferCommon,userClicks("userIdClicks")<=>userOfferCommon("userIdOfferView") && userClicks("offerIdClicks")<=>userOfferCommon("offerIdOfferView"),"right_outer")

							cOOuterJoin.rdd.coalesce(1,false).saveAsTextFile("/data/sidana/recsysBaselines/experiments/atleastone/totalData_"+code+".csv")
				}
		fw.close()
	}
}

