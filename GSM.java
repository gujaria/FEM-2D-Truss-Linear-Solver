import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

public class GSM {

	public double[][] KG;

	public ArrayList<Integer> disp_index;
	
	public ArrayList<Integer> force_index;
	
	public double[] force_vector;
	
	public double[] disp_vector;
	
	
	public void set_size(LINK ele)
	{
		
	KG=new double[2*ele.nodes.size()][2*ele.nodes.size()];
	disp_index=new ArrayList<Integer>();
	force_index=new ArrayList<Integer>();
	force_vector=new double[2*ele.nodes.size()];
	disp_vector=new double[2*ele.nodes.size()];
	
	}
	
	public void set_size(TRI ele)
	{
		
	KG=new double[2*ele.nodes.size()][2*ele.nodes.size()];
	disp_index=new ArrayList<Integer>();
	force_index=new ArrayList<Integer>();
	force_vector=new double[2*ele.nodes.size()];
	disp_vector=new double[2*ele.nodes.size()];
	
	}
	
	public void add_element(LINK ele)
	{
		for(int i=2*(ele.NODE1-1);i<=2*(ele.NODE1-1)+1;i++) //K11
			for(int j=2*(ele.NODE1-1);j<=2*(ele.NODE1-1)+1;j++)
			{
				KG[i][j]=ele.K[i-2*(ele.NODE1-1)][j-2*(ele.NODE1-1)]+KG[i][j];
			}
		
		for(int i=2*(ele.NODE2-1);i<=2*(ele.NODE2-1)+1;i++) //K22
			for(int j=2*(ele.NODE2-1);j<=2*(ele.NODE2-1)+1;j++)
			{
				int a=i-2*(ele.NODE2-1)+2;
				int b=j-2*(ele.NODE2-1)+2;
				KG[i][j]=ele.K[i-2*(ele.NODE2-1)+2][j-2*(ele.NODE2-1)+2]+KG[i][j];
			}
		
		for(int i=2*(ele.NODE1-1);i<=2*(ele.NODE1-1)+1;i++) //K12
			for(int j=2*(ele.NODE2-1);j<=2*(ele.NODE2-1)+1;j++)
			{
				KG[i][j]=ele.K[i-2*(ele.NODE1-1)][j-2*(ele.NODE2-1)+2]+KG[i][j];
			}
		
		for(int i=2*(ele.NODE2-1);i<=2*(ele.NODE2-1)+1;i++) //K21
			for(int j=2*(ele.NODE1-1);j<=2*(ele.NODE1-1)+1;j++)
			{
				KG[i][j]=ele.K[i-2*(ele.NODE2-1)+2][j-2*(ele.NODE1-1)]+KG[i][j];
			}
		
	}
	
	public void add_element(TRI ele)
	{
		for(int i=2*(ele.NODE1-1);i<=2*(ele.NODE1-1)+1;i++) //K11
			for(int j=2*(ele.NODE1-1);j<=2*(ele.NODE1-1)+1;j++)
			{
				KG[i][j]=ele.K[i-2*(ele.NODE1-1)][j-2*(ele.NODE1-1)]+KG[i][j];
			}
		
		
		for(int i=2*(ele.NODE2-1);i<=2*(ele.NODE2-1)+1;i++) //K22
			for(int j=2*(ele.NODE2-1);j<=2*(ele.NODE2-1)+1;j++)
			{
				int a=i-2*(ele.NODE2-1)+2;
				int b=j-2*(ele.NODE2-1)+2;
				KG[i][j]=ele.K[i-2*(ele.NODE2-1)+2][j-2*(ele.NODE2-1)+2]+KG[i][j];
			}
		
		
		for(int i=2*(ele.NODE3-1);i<=2*(ele.NODE3-1)+1;i++) //K33
			for(int j=2*(ele.NODE3-1);j<=2*(ele.NODE3-1)+1;j++)
			{
				int a=i-2*(ele.NODE3-1)+2;
				int b=j-2*(ele.NODE3-1)+2;
				KG[i][j]=ele.K[i-2*(ele.NODE3-1)+4][j-2*(ele.NODE3-1)+4]+KG[i][j];
			}
		
		
		for(int i=2*(ele.NODE1-1);i<=2*(ele.NODE1-1)+1;i++) //K12
			for(int j=2*(ele.NODE2-1);j<=2*(ele.NODE2-1)+1;j++)
			{
				KG[i][j]=ele.K[i-2*(ele.NODE1-1)][j-2*(ele.NODE2-1)+2]+KG[i][j];
			}
		
		for(int i=2*(ele.NODE1-1);i<=2*(ele.NODE1-1)+1;i++) //K13
			for(int j=2*(ele.NODE3-1);j<=2*(ele.NODE3-1)+1;j++)
			{
				KG[i][j]=ele.K[i-2*(ele.NODE1-1)][j-2*(ele.NODE3-1)+4]+KG[i][j];
			}
		
		for(int i=2*(ele.NODE3-1);i<=2*(ele.NODE3-1)+1;i++) //K32
			for(int j=2*(ele.NODE2-1);j<=2*(ele.NODE2-1)+1;j++)
			{
				KG[i][j]=ele.K[i-2*(ele.NODE3-1)+4][j-2*(ele.NODE2-1)+2]+KG[i][j];
			}
		
		for(int i=2*(ele.NODE2-1);i<=2*(ele.NODE2-1)+1;i++) //K21
			for(int j=2*(ele.NODE1-1);j<=2*(ele.NODE1-1)+1;j++)
			{
				KG[i][j]=ele.K[i-2*(ele.NODE2-1)+2][j-2*(ele.NODE1-1)]+KG[i][j];
			}
		
		for(int i=2*(ele.NODE2-1);i<=2*(ele.NODE2-1)+1;i++) //K23
			for(int j=2*(ele.NODE3-1);j<=2*(ele.NODE3-1)+1;j++)
			{
				KG[i][j]=ele.K[i-2*(ele.NODE2-1)+2][j-2*(ele.NODE3-1)+4]+KG[i][j];
			}
		
		for(int i=2*(ele.NODE3-1);i<=2*(ele.NODE3-1)+1;i++) //K31
			for(int j=2*(ele.NODE1-1);j<=2*(ele.NODE1-1)+1;j++)
			{
				KG[i][j]=ele.K[i-2*(ele.NODE3-1)+4][j-2*(ele.NODE1-1)]+KG[i][j];
			}
	}
	
	public double[][] reduction_index_2D(int[] rows)
	{
		double [][] res=new double[KG[0].length-rows.length][KG[0].length-rows.length];
		Arrays.sort(rows);
		List<Integer> list = Arrays.stream(rows).boxed().collect(Collectors.toList());
		int col_count=0,row_count=0;
		
		for(int i=0;i<KG[0].length;i++)
		{
			if(list.indexOf(i)!=-1)
			{
				row_count=row_count+1;
				continue;
			}
			col_count=0;
			
			for(int j=0;j<KG[0].length;j++)
			{
				if( list.indexOf(j)!=-1 )
				{
					col_count=col_count+1;
				}
				else
				{
					res[i-row_count][j-col_count]=KG[i][j];
				}
			}
		}
		return res;
	}
	
	public double[] reduction_index_1D(int[] rows,double[][] arr)
	{
		double [] res=new double[arr.length-rows.length];
		Arrays.sort(rows);
		List<Integer> list = Arrays.stream(rows).boxed().collect(Collectors.toList());
		int row_count=0;
		
		for(int i=0;i<arr.length;i++)
		{
			if(list.indexOf(i)!=-1)
			{
				row_count=row_count+1;
				continue;
			}
			else
			{
				res[i-row_count]=arr[i][0];
			}
		}
		return res;
	}
	
	public double[] reduction_index_1D(int[] rows,double[] arr)
	{
		double [] res=new double[arr.length-rows.length];
		Arrays.sort(rows);
		List<Integer> list = Arrays.stream(rows).boxed().collect(Collectors.toList());
		int row_count=0;
		
		for(int i=0;i<arr.length;i++)
		{
			if(list.indexOf(i)!=-1)
			{
				row_count=row_count+1;
				continue;
			}
			else
			{
				res[i-row_count]=arr[i];
			}
		}
		return res;
	}
	
	public void K_show()
	{
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(3);
		for (int i = 0; i < KG.length; i++) {
		    for (int j = 0; j < KG[i].length; j++) {
		        System.out.print(nf.format(KG[i][j])+"    ");
		    }
		    System.out.println();
	}
	}
	
	public void print(double [][] arr)
	{
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(3);
		for (int i = 0; i < arr.length; i++) {
		    for (int j = 0; j <arr.length; j++) {
		        System.out.print(nf.format(arr[i][j])+"    ");
		    }
		    System.out.println();
	}
	}
	
	public void set_disp(int node,String dir,double value)
	{
		
		if(dir=="x")
		{
		disp_index.add(2*(node-1));	
		disp_vector[2*(node-1)]=value;
		}
		else if(dir=="y")
		{
		disp_index.add(2*(node-1)+1);
		disp_vector[2*(node-1)+1]=value;
		}
		
		
	}
	
	public void set_force(int node,String dir,double value)
	{
		if(dir=="x")
		{
		force_index.add(2*(node-1));	
		force_vector[2*(node-1)]=value;
		}
		else if(dir=="y")
		{
		force_index.add(2*(node-1)+1);
		force_vector[2*(node-1)+1]=value;
		}
	}
	
	public int[] get_disp_index()
	{
		return to_arr(disp_index);
	}
	
	public int[] get_force_index()
	{
		return to_arr(force_index);
	}
	
	public int[] to_arr(ArrayList<Integer> arr)
	{
		int[] res=new int[arr.size()];
		for(int i=0;i<res.length;i++)
		{
			res[i]=arr.get(i);
		}
		
		return res;
	}
	
	public void solve()
	{
		RealMatrix matrix_KG = new Array2DRowRealMatrix(KG);
		RealMatrix matrix_disp_vector=new Array2DRowRealMatrix(disp_vector);
		double [] temp_mul,temp_force;
		double[][] temp= matrix_KG.multiply(matrix_disp_vector).getData(); //Multiplication of global with disp_vec
		//System.out.println(Arrays.deepToString(temp));
		//System.out.println(temp.length);
		
		temp_mul=reduction_index_1D(get_disp_index(),temp);	//removal of given displacement values.
		temp_force=reduction_index_1D(get_disp_index(),force_vector); //removal of rows corresponding to above.
		
		RealMatrix matrix_temp_mul = new Array2DRowRealMatrix(temp_mul);
		RealMatrix matrix_temp_force = new Array2DRowRealMatrix(temp_force);
		RealMatrix matrix_K_reduced = new Array2DRowRealMatrix(reduction_index_2D(get_disp_index()));
		
		
		double[][] sol=(MatrixUtils.inverse(matrix_K_reduced).multiply(matrix_temp_force.subtract(matrix_temp_mul))).getData();
		
		int row_count=0;
		for(int i=0;i<disp_vector.length;i++)
		{
			if(disp_index.indexOf(i)!=-1)
			{
				row_count=row_count+1;
			}
			else
			{
				disp_vector[i]=sol[i-row_count][0];
			}
		}
		
		System.out.println(Arrays.toString(disp_vector));
		
		RealMatrix matrix_disp_vector_final=new Array2DRowRealMatrix(disp_vector);
		RealMatrix matrix_force_vector=matrix_KG.multiply(matrix_disp_vector_final);
		force_vector=matrix_force_vector.getColumn(0);
		System.out.println(Arrays.toString(force_vector));
	}
}
