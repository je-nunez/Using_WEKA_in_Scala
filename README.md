# Using WEKA in Scala

Some scripts to use WEKA in Scala.

For a general description of WEKA, its use in Big Data (as in map-reduce jobs to Hadoop), etc, see the other repository:

     https://github.com/je-nunez/WEKA

WEKA ((c) The University of Waikato, Hamilton, New Zealand).

# WIP

This project is a *work in progress*. The implementation is *incomplete* and subject to change. The documentation can be inaccurate.

# Description:

Script `training_a_weka_j48_decision_tree.scala`:

     Loads a training set in ARFF format, constructs a WEKA J48 (Ross Quinlan's C4.5
     revision 8) decision tree in Scala from this training set, and visualizes the
     resulting decision tree and also prints it as a Java class.

     (The visualization of the tree requires a graphical window-manager, ie., in Unix
     the DISPLAY X Window variable, or in Windows access to the GUI.)

     The ARFF file "contact-lenses.arff" is the one that exists in the
     standard WEKA distribution (in its "./data/" directory)

