import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

class App {

	public static void main(String[] args) {
//	Addition of elements to the system		
//
//		LINK ONE=new LINK(0,0,500,0,210000,78.5);
//		
//		LINK TWO=new LINK(0,0,250,433.012,210000,78.5);
//		
//		LINK THR=new LINK(250,433.012,500,0,210000,78.5);
//		
//		LINK FOU=new LINK(500,0,750,433.012,210000,78.5);
//		
//		LINK FIV=new LINK(250,433.012,750,433.012,210000,78.5);
//		
//		GSM KG=new GSM();
//
//	Addition of elements to the global stiffness matrix		
//		
//		KG.set_size(ONE);
//		
//		KG.add_element(ONE);
//		
//		KG.add_element(TWO);
//		
//		KG.add_element(THR);
//		
//		KG.add_element(FOU);
//		
//		KG.add_element(FIV);
		
		//System.out.println("KG is :");
		//KG.K_show();	
//
//	Setting the values of constraints and given displacement		
//		
//		KG.set_disp(1, "x",0);
//		KG.set_disp(1, "y",0);
//		KG.set_disp(2, "y",0);
//		KG.set_disp(4, "y",-4); //for second case comment this and uncomment the force
//
//	Setting the values of forces 
//		
//		
		//KG.set_force(4,"y", -100);
		
//
//	Solving the problem
//		
		
		
//		KG.solve();
//		
//		
//		
//		
		
		TRI ONE=new TRI(0,0,3,1,2,2,1,0.3,1);
		ONE.K_show();
		GSM KG=new GSM();
		KG.set_size(ONE);
		KG.add_element(ONE);
		KG.K_show();
		

        
	}

}
