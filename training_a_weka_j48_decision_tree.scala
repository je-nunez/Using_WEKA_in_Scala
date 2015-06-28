#!/usr/bin/env scala

// You need to have your CLASSPATH variable including the
//      weka.jar
// file. Eg.
//      export CLASSPATH="$CLASSPATH:/path/to/weka.jar"


// import the WEKA J48 decision tree into Scala

import _root_.weka.classifiers.trees.J48
import _root_.weka.core.converters.ArffLoader
import _root_.weka.core.Instances
import _root_.weka.gui.arffviewer.ArffViewer
import _root_.weka.gui.treevisualizer.TreeVisualizer
import _root_.java.io.{BufferedOutputStream, PrintStream, File, FileOutputStream}

// create a WEKA J48 decision tree in Scala
// http://weka.sourceforge.net/doc.dev/weka/classifiers/trees/J48.html

var j48_decision_tree: J48 = new J48();

// load the training data for the J48 (C4.5) decision tree

var weka_arff_file : ArffLoader = new ArffLoader();
val current_dir = System.getProperty("user.dir");

// Show the ArffViewer with this file
// ArffViewer.main(Array("contact-lenses.arff"));

println("Reading training set...");
weka_arff_file.setURL("file:///" + current_dir + "/contact-lenses.arff");
println("Reading done.");

// set as the objective variable of the decision tree the last variable

var training_data : Instances = weka_arff_file.getDataSet();
training_data.setClassIndex(training_data.numAttributes() - 1);

// Build the decision tree based on the training instances from ARFF file

j48_decision_tree.setOptions(Array("-U"));   // "-U": an unpruned decision tree
j48_decision_tree.buildClassifier( training_data );

val tree_as_java_cl = j48_decision_tree.toSource("MyJavaClassName_to_Decide");
println("\n\nThe WEKA J48 decision tree output as a Java class:\n\n" +
        tree_as_java_cl);

// Display graphically our J48 decision tree (needs "dotty" string first)
// ( See also https://weka.wikispaces.com/Visualizing+a+Tree )

val dotty_representation_of_tree = j48_decision_tree.graph();
println(dotty_representation_of_tree);
val temp_file = File.createTempFile("J48_decision_tree", ".dotty");
temp_file.deleteOnExit();
val buff_writer = new PrintStream(new FileOutputStream(temp_file));
buff_writer.println(dotty_representation_of_tree);
buff_writer.flush();
buff_writer.close();

TreeVisualizer.main(Array(temp_file.getAbsolutePath));
temp_file.delete();

