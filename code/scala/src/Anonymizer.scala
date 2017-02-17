import java.io.{BufferedWriter, FileOutputStream, OutputStreamWriter}

import scala.io.Source
import scala.util.Random

//==> Click <==
//ClickId,UserId,OfferId,OfferViewId,CountryCode,Category,Source,UtcDate,Keywords,OfferTitle

//==> Offers <==
//OfferId,OfferViewId,UserId,OfferRank,Merchant,Price,UtcDate,CountryCode

//==> Page_View <==
//UserId,CountryCode,UtcDate,URL

//  //==> Search <==
//SearchId,UserId,CountryCode,IsPrompt,UtcDate,QueryString

object ShaEncoder {
  private val salt:String=Random.alphanumeric.take(10).mkString
  private val digester=java.security.MessageDigest.getInstance("SHA-256")

  def encode(s: String): String = {
    val m = digester.digest((salt+s).getBytes("UTF-8"))
    m.map("%02x".format(_)).mkString
  }
}

object Anonymizer extends App {
  private val separator =","
  private val fieldsNameToAnonimize=List("userId","Source","Merchant").map(_.toLowerCase())

  //all files must be anonimized at once to have the same salt
  val filesToAnonymize=args.toList

  filesToAnonymize.foreach { fileName =>
    println(s"## Anonymizing file $fileName")
    val anonymizedFileWriter=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName+".anon"), "UTF-8"))

    val lines=Source.fromFile(fileName,"UTF-8").getLines()
    //handle header
    val _1stLine=lines.next()
    val indexesOfFieldsToAnonymize :Seq[Int]= _1stLine
      .split(separator)
        .map(_.toLowerCase())
      .zipWithIndex
      .filter { case (fieldName, index) => fieldsNameToAnonimize.contains(fieldName) }
      .map { case (fieldName, index) => index }

    println(s"Anonymizing fields indexes ${indexesOfFieldsToAnonymize.mkString(",")}")
    anonymizedFileWriter.write(_1stLine)
    anonymizedFileWriter.newLine()

    //other lines
    lines.foreach{ line =>
      val anonymizedLine = line.split(separator)
        .zipWithIndex
        .map{
        case (field, index) if indexesOfFieldsToAnonymize.contains(index) => ShaEncoder.encode(field)
        case (field, index) => field
      }.mkString(separator)
      anonymizedFileWriter.write(anonymizedLine)
      anonymizedFileWriter.newLine()
    }
    anonymizedFileWriter.close()

    println(s"## Finished anonymisation of file $fileName")
  }

}
