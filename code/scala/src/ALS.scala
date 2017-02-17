package main.scala

import java.nio.file.{ Files, Paths }
import scala.collection.mutable.ListBuffer
import java.io.FileWriter
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.functions._
import org.apache.spark.mllib.recommendation.ALS
import org.apache.spark.mllib.recommendation.MatrixFactorizationModel
import org.apache.spark.mllib.recommendation.Rating
import org.apache.spark.ml.feature.StringIndexer
import org.apache.spark.sql.functions.{lit, to_date}
import org.apache.spark.sql.types._
import org.apache.spark.sql._

object ALS {
	def main(args: Array[String]) {
		val conf = new SparkConf().setAppName("Simple Application")
				val sc = new SparkContext(conf)
				val sqlContext = new SQLContext(sc)
				import sqlContext._
				import sqlContext.implicits._
				val countryCodes = Array("at","be","br","ch","cz","de","dk","es","fi","ie","nb","nl","no","pl","pt","ru","se","uk","it","fr")
				val fwTrain = new FileWriter("/data/sidana/recsysBaselines/experiments/mfcountryfiles/train.txt", true)
				val fwTest = new FileWriter("/data/sidana/recsysBaselines/experiments/mfcountryfiles/test.txt", true)

				for (code <- countryCodes){
					val time1 = java.lang.System.currentTimeMillis()
							val inputFile = sqlContext.read
							.format("com.databricks.spark.csv")
							.option("header", "true") // Use first line of all files as header
							.option("inferSchema", "true") // Automatically infer data types
							.option("delimiter", "\t")
							.load("/data/sidana/recsysBaselines/experiments/mfcountryfiles/input/mfinput_"+code) 
							val indexerUserId = new StringIndexer().setInputCol("userid").setOutputCol("user")
							val indexerOfferId = new StringIndexer().setInputCol("offerid").setOutputCol("offer") 
							val clickedOffersIndexedUsers = indexerUserId.fit(inputFile).transform(inputFile)
							val clickedOffersIndexedOffers = indexerOfferId.fit(clickedOffersIndexedUsers).transform(clickedOffersIndexedUsers)
							val validusersoffers = clickedOffersIndexedOffers.select("userid","user","offerid","offer")


							val csvFileClickTrain = sqlContext.read
							.format("com.databricks.spark.csv")
							.option("header", "true") // Use first line of all files as header
							.option("inferSchema", "true") // Automatically infer data types
							.option("delimiter", "\t")
							.load("/data/sidana/recsysBaselines/experiments/mfcountryfiles/input/train_"+code+".csv") 

							val trainDF = csvFileClickTrain.filter(csvFileClickTrain("rating") ==="1")

							val validTrainUsersOffers = trainDF.join(validusersoffers,trainDF("userid")<=>validusersoffers("userid") && trainDF("offerid")<=>validusersoffers("offerid") ).drop(validusersoffers("userid")).drop(validusersoffers("offerid"))

							val groupedOffers = validTrainUsersOffers.select("user","offer","rating")
							val ratings = groupedOffers.map{
							case Row(user, offer, rating) => Rating(user.asInstanceOf[Double].intValue, offer.asInstanceOf[Double].intValue, rating.asInstanceOf[Int].doubleValue)
					}

					val rank = 10
							val numIterations = 10
							val model = ALS.train(ratings, rank, numIterations, 0.01)
							val time2 = java.lang.System.currentTimeMillis()
							val time = time2 - time1
							fwTrain.write(code+" "+time)

							val t1 = System.currentTimeMillis
							val testDF = sqlContext.read
							.format("com.databricks.spark.csv")
							.option("header", "true") // Use first line of all files as header
							.option("inferSchema", "true") // Automatically infer data types
							.option("delimiter", "\t")
							.load("/data/sidana/recsysBaselines/experiments/mfcountryfiles/input/test_"+code+".csv")

							val validTestUsersOffers = testDF.join(validusersoffers,testDF("userid")<=>validusersoffers("userid")&&testDF("offerid")<=>validusersoffers("offerid")).drop(validusersoffers("userid")).drop(validusersoffers("offerid"))

							val groupedTestUsersOffers = validTestUsersOffers.select("user","offer","rating")
							val ratingsTest = groupedTestUsersOffers.map{
							case Row(user, offer, rating) => Rating(user.asInstanceOf[Double].intValue, offer.asInstanceOf[Double].intValue, rating.asInstanceOf[Int].doubleValue)
					}

					ratingsTest.coalesce(1,false).saveAsTextFile("/data/sidana/recsysBaselines/experiments/mfcountryfiles/output/ratingsTest_"+code+".csv")

					val usersProducts = ratingsTest.map { case Rating(user, product, rate) =>
					(user, product)
					}

					val predictions = model.predict(usersProducts).map { case Rating(user, product, rate) => 
					((user, product), rate)}
					predictions.coalesce(1,false).saveAsTextFile("/data/sidana/recsysBaselines/experiments/mfcountryfiles/output/predictions_"+code+".csv")
					val t2 = System.currentTimeMillis
					fwTest.write(code+" "+(t2 - t1))
				}
		fwTrain.close()
		fwTest.close()

	}
}


