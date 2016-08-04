# MetaProteomeAnalyzer (MPA) #

  * [Download](#Download.md)
  * [Introduction](#Introduction.md)
  * [Features](#Features)

---

## MetaProteomeAnalyzer Publication:
  * [Muth at al: J Proteome Res. 2015 Mar 6;14(3):1557-65.](http://www.ncbi.nlm.nih.gov/pubmed/25660940).
  * If you use MetaProteomeAnalyzer as part of a publication, please include this reference.

---

## Download ##

[[download]](http://svn.mpi-magdeburg.mpg.de/MetaProteomeAnalyzer/Download/mpa-portable-1.2.zip)  *MPA Portable version 1.2 - Windows 64-bit - released on July 21, 2016*

---

## Introduction ##

**MetaProteomeAnalyzer** (MPA) is a software pipeline to analyze and visualize metaproteomics (and also proteomics) data. It consists of a MS/MS data processing application in combination with an user-friendly interactive client. 
The metaproteomics data analysis software is developed in the Java programming language and uses a neo4j graph database backend for flexible and user-defined data querying of the results.

---

## MPA Portable ##

**MPA Portable** is a light-weight and stand-alone software for the identification of proteins and in-depth analysis of metaproteomics (and also proteomics) data. The MPA software uses X!Tandem and OMSSA as search engines and takes MGF spectrum files as input. The server application is included within the application and user-provided FASTA databases are formatted automatically.
*Please note:* MPA Portable can be run directly on your desktop PC or laptop and no separate installation is needed: just double-click the provided JAR file or use one of the provided batch files.

---

## Features ##

The MPA metaproteomics software comes with the following key features:
  * Intuitive graphical user interface
  * Project management
  * Top-down metaproteomics data analysis
  * In-depth analysis of taxonomies, ontologies, pathways and enzymes
  * Grouping of redundant protein hits to "meta-proteins" (protein groups)
  * Label-free quantification methods
  * Graph database-driven
  * Interactive overview 
  * Portable application 

---

[Go to top of page](#General_Information.md)

---

## System Requirements ##
  * **Operating system**: (Tested on Windows XP, Windows Vista, Windows 7)
  * **Memory**: The bigger the better (preferably 2 GB at least)

Please download the latest version of `mpa-portable-X.Y.Z.zip` (where X, Y and Z represent the current version of the software).

Before starting the MPA Portable version, please make sure that you have Java 1.8 installed. To check the currently installed java version, open a console/bash window and type:
```
java –version
```

If you haven't installed Java 1.8, go directly to
[Java.com](http://www.java.com/download/) to download this Java version.

[Go to top of page](#README.md)

---

## Portable Startup ##
After downloading the zip file, simply unzip the file and use the provided script, i.e. mpa-portable.bat for Windows OS.

You can also double-click on the JAR file, however this will give you no options to change the memory settings (see below).

Another possiblity is to use the commandline directly:
```
java -jar mpa-portable-X.Y.Z.jar -XmxXXXXm 
```
**Note:** -Xmx\*XXXX\*m stands for maximum assigned memory (in megabytes of RAM)

