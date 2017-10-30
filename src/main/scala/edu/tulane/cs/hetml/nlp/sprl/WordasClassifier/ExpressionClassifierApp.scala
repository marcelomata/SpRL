package edu.tulane.cs.hetml.nlp.sprl.WordasClassifier

import edu.stanford.nlp.tagger.maxent.PairsHolder
import edu.tulane.cs.hetml.nlp.BaseTypes._
import edu.tulane.cs.hetml.nlp.sprl.Eval.SpRLEvaluation
import edu.tulane.cs.hetml.nlp.sprl.WordasClassifier.WordExpressionSegmentConstraintClassifiers.ExpressionasClassiferConstraintClassifier
import edu.tulane.cs.hetml.nlp.sprl.WordasClassifier.WordasClassifierClassifiers._
import edu.tulane.cs.hetml.nlp.sprl.WordasClassifier.WordasClassifierDataModel._
import edu.tulane.cs.hetml.nlp.sprl.mSpRLConfigurator._
import edu.tulane.cs.hetml.vision._
import me.tongfei.progressbar.ProgressBar

import scala.collection.JavaConversions._
import scala.collection.mutable.ListBuffer

/** Created by Umar on 2017-10-20.
  */

object ExpressionClassifierApp extends App {

  // Preprocess RefExp
  val stopWords = Array("the", "an", "a")
  var combinedResults = Seq[SpRLEvaluation]()

  val relWords = Array("below", "above", "between", "not", "behind", "under", "underneath", "front of", "right of",
    "left of", "ontop of", "next to", "middle of")

  val CLEFGoogleNETReaderHelper = new CLEFGoogleNETReader(imageDataPath)
  val classifierDirectory = s"models/mSpRL/ExpressionClassiferScoreOnly/"
  println("Start Reading Data from Files...")
  val allImages =
    if(isTrain)
      CLEFGoogleNETReaderHelper.trainImages.toList
    else
      CLEFGoogleNETReaderHelper.testImages.take(200).toList

  val allsegments =
    if(!useAnntotatedClef) {
        CLEFGoogleNETReaderHelper.allSegments.filter(s => {allImages.exists(i=> i.getId==s.getAssociatedImageID)})
    } else {
      CLEFGoogleNETReaderHelper.allSegments.toList
    }

  val allDocuments = CLEFGoogleNETReaderHelper.allDocuments.filter(s => {
    val imgSegId = s.getId.split("_")
    allImages.exists(i=> i.getId==imgSegId(0))
  })

  val allSentence = CLEFGoogleNETReaderHelper.allSentences.filter(d => {
    val senID = d.getId.split("_")
    allImages.exists(i=> i.getId==senID(0))
  })

  loadWordClassifiers()

  images.populate(allImages, isTrain)
  segments.populate(allsegments, isTrain)
  documents.populate(allDocuments, isTrain)
  sentences.populate(allSentence, isTrain)

  if(isTrain) {
    println("Training...")
    ExpressionasClassifer.modelDir = classifierDirectory
    ExpressionasClassifer.learn(iterations)
    ExpressionasClassifer.save()
  }

  if(!isTrain) {
    println("Testing...")
    //ExpressionasClassifer.modelDir = classifierDirectory
    var count = 0;
    ExpressionasClassifer.load()
    //ExpressionasClassifer.test()
//    val totalGroups = expressionSegmentPairs().groupBy(_.getArgumentId(0))
//    totalGroups.foreach(e => {
//      val PredictedSegId = getMaxScoreSeg(e._1, e._2.toList)
//      val ActualSegId = Integer.parseInt(e._1.split("_")(1))
//      if(PredictedSegId==ActualSegId) {
//        count +=1
//      }
//    })
//    val total = totalGroups.size
//    println(s"Total: ${total} Correct: ${count} Percentage: ${count*1.0/total}")

    ExpressionasClassifer.test(expressionSegmentPairs(), expressionPredictedRelation, expressionActualRelation)
//    ExpressionasClassiferConstraintClassifier.test()

    //ExpressionasClassiferConstraintClassifier.test(expressionSegmentPairs().filter(es => es.getArgumentId(2)=="isRel"))
    //ExpressionasClassifer.test(expressionSegmentPairs().filter(es => es.getArgumentId(2)=="isRel"))
  }

  def getMaxScoreSeg(exp: String,relations: List[Relation]): Int = {
    var Id = -1
    relations.foreach(r=> {
      if(ExpressionasClassiferConstraintClassifier(r)=="true") {
        Id = Integer.parseInt(r.getArgumentId(1).split("_")(1))
      }
    })
    Id
  }
}