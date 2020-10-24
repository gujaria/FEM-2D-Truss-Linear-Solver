import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class TRI {

	public static ArrayList<double[]> nodes=new ArrayList<double[]>();
	public double[][] K=new double[6][6];
	public double[] c=new double[3];
	public double[] d=new double[3];
	public double x1,y1,x2,y2,x3,y3;
	public int NODE1,NODE2,NODE3;
	public double E,nu,h; //angle will be in radians...
	public double FORCE1,FORCE2,DISP1,DISP2;

	TRI(double x1,double y1,double x2,double y2,double x3,double y3,double E,double nu,double h)
	{
		this.x1=x1;
		this.x2=x2;
		this.x3=x3;
		this.y1=y1;
		this.y2=y2;
		this.y3=y3;
		
		set_node();
		this.E=E;
		this.nu=nu;
		this.h=h;
		K_calculation_strain();
	}
	
	
	public void set_node() // Assigns node value to the given co-ordinates..
	{
		if(check_node(x1,y1)==-1 && check_node(x2,y2)==-1 && check_node(x3,y3)==-1 ) // all three absent
		{
			nodes.add(new double[] {x1,y1});
			NODE1=check_node(x1,y1)+1;
			nodes.add(new double[] {x2,y2});
			NODE2=check_node(x2,y2)+1;
			nodes.add(new double[] {x3,y3});
			NODE3=check_node(x3,y3)+1;
		}
		else if(check_node(x1,y1)!=-1 || check_node(x2,y2)!=-1 || check_node(x3,y3)!=-1 )//atleast one present
		{
			if(check_node(x1,y1)!=-1 && check_node(x2,y2)!=-1 && check_node(x3,y3)==-1 )//1 and 2 present
			{
				nodes.add(new double[] {x3,y3});
				NODE3=check_node(x3,y3)+1;
				
				if(check_node(x1,y1)-check_node(x2,y2)>0)
				{
					NODE2=check_node(x1,y1)+1;
					NODE1=check_node(x2,y2)+1;
				}else
				{
					NODE1=check_node(x1,y1)+1;
					NODE2=check_node(x2,y2)+1;
				}
			}else if(check_node(x1,y1)!=-1 && check_node(x2,y2)==-1 && check_node(x3,y3)!=-1 )//1 and 3 present
			{
				nodes.add(new double[] {x2,y2});	
				NODE3=check_node(x2,y2)+1;
				
				if(check_node(x1,y1)-check_node(x3,y3)>0)
				{
					NODE2=check_node(x1,y1)+1;
					NODE1=check_node(x3,y3)+1;
				}else
				{
					NODE1=check_node(x1,y1)+1;
					NODE2=check_node(x3,y3)+1;
				}
			}else if(check_node(x1,y1)==-1 && check_node(x2,y2)!=-1 && check_node(x3,y3)!=-1 )//2 and 3 present
			{
				nodes.add(new double[] {x1,y1});
				NODE3=check_node(x1,y1)+1;
				
				if(check_node(x2,y2)-check_node(x3,y3)>0)
				{
					NODE2=check_node(x2,y2)+1;
					NODE1=check_node(x3,y3)+1;
				}else
				{
					NODE1=check_node(x2,y2)+1;
					NODE2=check_node(x3,y3)+1;
				}
			}
			
			else if(check_node(x1,y1)!=-1 && check_node(x2,y2)==-1 && check_node(x3,y3)==-1 ) //1 present
			{
				NODE1=check_node(x1,y1)+1;
				nodes.add(new double[] {x2,y2});
				NODE2=check_node(x2,y2)+1;
				nodes.add(new double[] {x3,y3});
				NODE3=check_node(x3,y3)+1;
			}
			else if(check_node(x1,y1)==-1 && check_node(x2,y2)!=-1 && check_node(x3,y3)==-1 ) //2 present
			{
				NODE1=check_node(x2,y2)+1;
				nodes.add(new double[] {x1,y1});
				NODE2=check_node(x1,y1)+1;
				nodes.add(new double[] {x3,y3});
				NODE3=check_node(x3,y3)+1;
			}
			else if(check_node(x1,y1)==-1 && check_node(x2,y2)==-1 && check_node(x3,y3)!=-1 ) //3 present
			{
				NODE1=check_node(x3,y3)+1;
				nodes.add(new double[] {x1,y1});
				NODE2=check_node(x1,y1)+1;
				nodes.add(new double[] {x2,y2});
				NODE3=check_node(x2,y2)+1;
			}
			else if(check_node(x1,y1)!=-1 && check_node(x2,y2)!=-1 && check_node(x3,y3)!=-1 ) //All present
			{
				int[] temp= new int[3];
				temp[0]=check_node(x1,y1);
				temp[1]=check_node(x2,y2);
				temp[2]=check_node(x3,y3);
				Arrays.sort(temp);
				NODE1=temp[0]+1;
				NODE2=temp[0]+2;
				NODE3=temp[0]+3;
				
			}
			
		}
		
	}
	
	public int checkpos(int a , int b , int c) //useless for now
	{
		if(a>=0)
			return 0;
		if(b>=0)
			return 1;
		if(c>=0)
			return 2;
		
		return -1;
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
	
	public void K_calculation_strain()
	{
		double area=2d; //need to put the correct formula here..possibly heron's formula
		double constant=(E*h)/((4*area)*(1+nu)*(1-2*nu)); //only for plane strain

//		c1=nodes.get(NODE2)[1]-nodes.get(NODE3)[1];
//		c2=nodes.get(NODE3)[1]-nodes.get(NODE1)[1];
//		c3=nodes.get(NODE1)[1]-nodes.get(NODE2)[1];
//		
		c[0]=nodes.get(NODE2-1)[1]-nodes.get(NODE3-1)[1];
		c[1]=nodes.get(NODE3-1)[1]-nodes.get(NODE1-1)[1];
		c[2]=nodes.get(NODE1-1)[1]-nodes.get(NODE2-1)[1];
				
//		d1=nodes.get(NODE3)[0]-nodes.get(NODE2)[0];
//		d2=nodes.get(NODE1)[0]-nodes.get(NODE3)[0];
//		d3=nodes.get(NODE2)[0]-nodes.get(NODE1)[0];
				
		d[0]=nodes.get(NODE3-1)[0]-nodes.get(NODE2-1)[0];
		d[1]=nodes.get(NODE1-1)[0]-nodes.get(NODE3-1)[0];
		d[2]=nodes.get(NODE2-1)[0]-nodes.get(NODE1-1)[0];
		
//		
//		K00
//		
		K[0][0]=term11_strain(0,0);
		K[0][1]=term12_strain(0,0);
		K[1][0]=term21_strain(0,0);
		K[1][1]=term22_strain(0,0);
		
//		
//		K10
//		
		K[2][0]=term11_strain(1,0);
		K[2][1]=term12_strain(1,0);
		K[3][0]=term21_strain(1,0);
		K[3][1]=term22_strain(1,0);
//		
//		K20
//		
		K[4][0]=term11_strain(2,0);
		K[4][1]=term12_strain(2,0);
		K[5][0]=term21_strain(2,0);
		K[5][1]=term22_strain(2,0);
//		
//		K01
//		
		K[0][2]=term11_strain(0,1);
		K[0][3]=term12_strain(0,1);
		K[1][2]=term21_strain(0,1);
		K[1][3]=term22_strain(0,1);
//		
//		K11
//		
		K[2][2]=term11_strain(1,1);
		K[2][3]=term12_strain(1,1);
		K[3][2]=term21_strain(1,1);
		K[3][3]=term22_strain(1,1);
//		
//		K21
//		
		K[4][2]=term11_strain(2,1);
		K[4][3]=term12_strain(2,1);
		K[5][2]=term21_strain(2,1);
		K[5][3]=term22_strain(2,1);
		
//		
//		K02
//		
		K[0][4]=term11_strain(0,2);
		K[0][5]=term12_strain(0,2);
		K[1][4]=term21_strain(0,2);
		K[1][5]=term22_strain(0,2);
//		
//		K12
//		
		K[2][4]=term11_strain(1,2);
		K[2][5]=term12_strain(1,2);
		K[3][4]=term21_strain(1,2);
		K[3][5]=term22_strain(1,2);
//		
//		K22
//		
		K[4][4]=term11_strain(2,2);
		K[4][5]=term12_strain(2,2);
		K[5][4]=term21_strain(2,2);
		K[5][5]=term22_strain(2,2);
																		
	}
	
	public double term11_strain(int i, int j)
	{
		return c[i]*c[j]*(1-nu) + d[i]*d[j]*(0.5-nu); 
	}
	
	public double term12_strain(int i, int j)
	{
		return c[i]*d[j]*(nu) + c[j]*d[i]*(0.5-nu); 
	}
	
	public double term21_strain(int i, int j)
	{
		return c[j]*d[i]*(nu) + c[i]*d[j]*(0.5-nu); 
	}
	
	public double term22_strain(int i, int j)
	{
		return d[j]*d[i]*(1-nu) + c[i]*c[j]*(0.5-nu); 
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
	
}
