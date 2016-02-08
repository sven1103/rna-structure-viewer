# rna-structure-viewer RVee

_First, contributions go to Prof. Dr. Daniel Huson and PhD student Ania Gòrska, who provided the lecture 'Advanced Java for Bioinformatics' at University of Tübingen in 2015/16. Special thanks to Prof. Huson for establishing this awesome lecture which I regard as mandatory for a bioinformatician IMHO. Special thanks for Ania for her patience during the problem sessions, always good advice and the time she invested in us, therefore lost time for her PhD project. Very much seen and appreciated!_

This is a RNA structure viewer, that enables tertiary, secondary and primary structure visualization. Input is a PDB structure that must contain hydrogens. From the structure information, a 3D model is build and visualized. The information for the secondary structure is calculated from the tertiary structure information by computing hydrogen bonds distance and angle between the base residues. I also implemented a little algorithm in a branch and bound fashion, in order to automatically detect pseudo-knots in the structure and mark them as such in the primary structure view.

Have fun and enjoy the look and feel of JavaFX8 :)

### Control elements and shortcuts

**3D View**
* ```left-click + Drag```  Rotate via X-and Y-axis
* ```right-click + Drag``` Rotate via Z-axis
* ```Shift + left-click``` Zoom +/-
* ```Ctrl + left-click``` Move object

**All views**
* ```R```  Refresh/Recenter views
* ```D```  Deselect all
* ```Shift + hover``` (Multi-) Selection by hovering
* ```Ctrl + left-click``` (Multi-) Select nucleotide
* ```left-click``` Select single nucleotide
