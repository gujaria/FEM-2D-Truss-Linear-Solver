import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class LINK {

	public static ArrayList<double[]> nodes=new ArrayList<double[]>();
	public double[][] K=new double[4][4];
	
	public double x1,y1,x2,y2;
	public int NODE1,NODE2;
	public double length,angle,E,A; //angle will be in radians...
	public double FORCE1,FORCE2,DISP1,DISP2;
	
	LINK(double x1,double y1,double x2,double y2,double E,double A)
	{
		this.x1=x1;
		this.x2=x2;
		this.y1=y1;
		this.y2=y2;
		set_node();
		this.E=E;
		this.A=A;
		length_calc();
		angle_calc();
		K_calculation();
	}
	
	public void set_node() // Assigns node value to the given co-ordinates..
	{
//		if(check_node(x1,y1)==-1)
//		{
//			nodes.add(new double[] {x1,y1});
//			NODE1=check_node(x1,y1)+1;
//		}else
//		{
//			NODE1=check_node(x1,y1)+1;
//		}
//		
//		if(check_node(x2,y2)==-1)
//		{
//			nodes.add(new double[] {x2,y2});
//			NODE2=check_node(x2,y2)+1;
//		}else
//		{
//			NODE2=check_node(x2,y2)+1;
//		}
		
		if(check_node(x1,y1)==-1 && check_node(x2,y2)==-1 )
		{
			nodes.add(new double[] {x1,y1});
			NODE1=check_node(x1,y1)+1;
			nodes.add(new double[] {x2,y2});
			NODE2=check_node(x2,y2)+1;
		}else
		{
			if(check_node(x1,y1)==-1 && check_node(x2,y2)!=-1)
			{
				nodes.add(new double[] {x1,y1});
				NODE2=check_node(x1,y1)+1;
				NODE1=check_node(x2,y2)+1;
				
			}else if(check_node(x1,y1)!=-1 && check_node(x2,y2)==-1)
			{
				nodes.add(new double[] {x2,y2});
				NODE2=check_node(x2,y2)+1;
				NODE1=check_node(x1,y1)+1;
				
			}else if(check_node(x1,y1)!=-1 && check_node(x2,y2)!=-1 && check_node(x1,y1)>check_node(x2,y2) )
			{
				NODE2=check_node(x1,y1)+1;
				NODE1=check_node(x2,y2)+1;
				
			}else if(check_node(x1,y1)!=-1 && check_node(x2,y2)!=-1 && check_node(x1,y1)<check_node(x2,y2))
			{
				NODE1=check_node(x1,y1)+1;
				NODE2=check_node(x2,y2)+1;
			}
			
		}
		
		
	}
	
	public int check_node(double x1,double y1) //checks the existence of a particular co-ordinate
	{										   // in the array. If it does, exist in the array 
		for(int i=0;i<nodes.size();i++)		   // it returns that index or returns -1;
		{
			if(nodes.get(i)[0]==x1 && nodes.get(i)[1]==y1 )
				return i;
		}
		 return -1;
	}
	
	public void length_calc()
	{
		length=Math.sqrt(Math.pow((x2-x1),2) + Math.pow((y2-y1),2)) ; 
	}
	
	public void angle_calc()
	{
		if(x1==x2)
		{
			angle=Math.PI/2;
			return;
		}
		if(y1==y2)
		{
			angle=0;
			return;
		}
		
		angle=Math.atan( (y2-y1)/(x2-x1) );
	}
	
	public void K_calculation()
	{
		double mul=A*E/length;
		Double C=Math.cos(angle);
		Double S=Math.sin(angle);
		
		
		K[0][0]=C*C*mul;
		K[0][1]=C*S*mul;
		K[0][2]=-C*C*mul;
		K[0][3]=-C*S*mul;
		
		K[1][0]=C*S*mul;
		K[1][1]=S*S*mul;
		K[1][2]=-C*S*mul;
		K[1][3]=-S*S*mul;
		
		K[2][0]=-C*C*mul;
		K[2][1]=-C*S*mul;
		K[2][2]=C*C*mul;
		K[2][3]=C*S*mul;
		
		K[3][0]=-C*S*mul;
		K[3][1]=-S*S*mul;
		K[3][2]=C*S*mul;
		K[3][3]=S*S*mul;	
				
	}
	
	public void K_show()
	{
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(3);
		for (int i = 0; i < K.length; i++) {
		    for (int j = 0; j < K[i].length; j++) {
		        System.out.print(nf.format(K[i][j])+"    ");
		    }
		    System.out.println();
	}
	}
	
	
	public void set_disp(double disp, int node)
	{
		if(node==1)
			DISP1=disp;
		else if(node==2)
			DISP2=disp;
		else
			System.out.print("Wrong node");
		
	}
	
	public void set_force(double force, int node)
	{
		if(node==1)
			FORCE1=force;
		else if(node==2)
			FORCE2=force;
		else
			System.out.print("Wrong node");
		
	}
	
	
	
	
	
}
