# FEM Solver for 2D Truss Elements

FEM implementation in Java 
•	Implementation of linear FEM with 2D Truss elements and Triangle plane strain elements in Java.
•	User is asked for input co-ordinates of elements as well as the boundary conditions for load and displacement and the code outputs the calculated displacements.
•	The code calculates the Global Stiffness Matrix and then does the reduction for the same using the local node numbers of the individual elements and boundary conditions.

In the below example you can see the structured meshes.
Simple Block Mesh
![Alt Text](https://github.com/gujaria/FDM-Meshing/blob/main/Block.png)






Mesh formed using Parametric Equations
![Alt Text](https://github.com/gujaria/FDM-Meshing/blob/main/circle.png)

Heat Diffusion equation solved on the block mesh(Explicit Central Difference)
![Alt Text](https://github.com/gujaria/FDM-Meshing/blob/main/2dheat1.gif)
