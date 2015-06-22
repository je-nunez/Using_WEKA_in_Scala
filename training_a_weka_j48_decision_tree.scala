#!/usr/bin/env scala

// You need to have your CLASSPATH variable including the
//      weka.jar 
// file. Eg.
//      export CLASSPATH="$CLASSPATH:/path/to/weka.jar"


// import the WEKA J48 decision tree into Scala

import _root_.weka.classifiers.trees.J48
import _root_.weka.core.converters.ArffLoader
import _root_.weka.core.Instances

// create a WEKA J48 decision tree in Scala
// http://weka.sourceforge.net/doc.dev/weka/classifiers/trees/J48.html

var j48_decision_tree: J48 = new J48();

// load the training data for the J48 (C4.5) decision tree

var weka_arff_file : ArffLoader = new ArffLoader();
val current_dir = System.getProperty("user.dir");

println("Reading training set...");
weka_arff_file.setURL("file:///" + current_dir + "/contact-lenses.arff");
println("Reading done.");

// set as the objective variable of the decision tree the last variable

var training_data : Instances = weka_arff_file.getDataSet();
training_data.setClassIndex(training_data.numAttributes() - 1); 

// Build the decision tree based on the training instances from ARFF file

j48_decision_tree.setOptions(Array("U"));   // "U": an unpruned decision tree
j48_decision_tree.buildClassifier( training_data );

// val tree = j48_decision_tree.graph();

val tree = j48_decision_tree.toSource("MyJavaClassName_to_Decide");
println("\n\nThe WEKA J48 decision tree output as a Java class:\n\n" + tree);

