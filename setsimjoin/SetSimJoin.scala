package comp9313.ass4
import org.apache.spark.SparkContext
import java.io.FileWriter
import org.apache.spark.SparkContext._
import org.apache.spark._
import scala.collection.mutable.HashMap
import org.apache.spark.rdd.RDD
import org.apache.spark.SparkConf
import collection.mutable.ArrayBuffer
import scala.io.Source


object SetSimJoin {

  def main(args: Array[String]){
     val inputFile = args(0)
     val outputFolder= args(1)
     val conf = new SparkConf().setAppName("setsimjoin").setMaster("local")
     val sc = new SparkContext(conf)
     val input =  sc.textFile(inputFile).map(_.split(" ")).cache()
     var threshold = args(2).toDouble
     //******************sorting part *********
     //use hashmap to calculate the frequency
     val map2 = new HashMap[Int, Int]();
     val x4=input.flatMap(x=>x.drop(1).map((_,1)))
     .reduceByKey(_+_)
     .map(x=>(x._2,x._1)).sortByKey(true)
     .map(x=>(x._2,x._1))
     .map(x=>x._1).collect()

     for (x<- (0 until x4.length)){

       map2.put(x4(x).toInt, x)
     }
     var map3 =sc.broadcast(map2)
    //****************************************
     val pairs= input.map(x=>{
       
       var rid = x(0).toInt
       var pp = x.drop(1)
    //sort the line as the frequency
       
        pp.map(y=>{
         map3.value(y.toInt)
         
       }).sorted.take( (pp.size  -  ((pp.size)*threshold.toDouble).ceil + 1).toInt)
           .map(x => ( x.toInt,(rid.toInt,pp) ))
     }).flatMap(x=>x)    
     .groupByKey().filter(x=>x._2.toList.length>1).flatMap(x=>{
       var p = x._2.toList
       // double for loop to combinations
       var sim = for (
       y1<-(0 until p.length);
       y2<-(y1+1 until p.length)
       
      /* if(
           p(y1)._2.intersect(p(y2)._2).length.toDouble<
           math.ceil(threshold/(1+threshold))*
           (p(y1)._2.toList.length + p(y2)._2.toList.length));
           
        if p(y1)._2.intersect(p(y2)._2).length.toDouble>= 
            (p(y1)._2.toList.length+p(y2)._2.toList.length - p(y1)._2.intersect(p(y2)._2).length.toDouble).toDouble*threshold
            */)
         yield(
             // to get the similarity
            (p(y1)._1.toInt,p(y2)._1.toInt),
            p(y1)._2.intersect(p(y2)._2).length.toDouble/
            (p(y1)._2.toList.length+p(y2)._2.toList.length - p(y1)._2.intersect(p(y2)._2).length.toDouble).toDouble)

        sim.filter(x=>x._2>=threshold)
// to filter the duplicate one
     }).map(x => if (x._1._1 > x._1._2) ((x._1._2,x._1._1),x._2) else ((x._1._1,x._1._2),x._2)).distinct() // fliter the answer
     .sortByKey()
     .map(x=> s"${x._1}\t${x._2}")
     //.foreach(println)
    
     .saveAsTextFile(outputFolder)

   

  }


}
