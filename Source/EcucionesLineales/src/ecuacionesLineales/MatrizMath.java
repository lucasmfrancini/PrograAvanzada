package ecuacionesLineales;

import java.io.*;

public class MatrizMath {
	
	private int dimensionFil;
	private int dimensionCol;
	private float[][] componentes;
	
	public MatrizMath(){
	}
	
	public MatrizMath(int dimensionFil,int dimensionCol){
		this.dimensionFil = dimensionFil;
		this.dimensionCol = dimensionCol;
		this.componentes = new float[dimensionFil][dimensionCol];
	}
	
	public MatrizMath(String path){
		File archivo=null;
		BufferedReader br= null;
		FileReader fr= null;
		String linea;
		String[] arraySplit;
		float[][] matriz = null;
		try{
			archivo = new File(path);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			if(null != (linea = br.readLine())){
				arraySplit= linea.split(" ");
				matriz = new float[Integer.parseInt(arraySplit[0])][Integer.parseInt(arraySplit[1])];
				this.dimensionFil=Integer.parseInt(arraySplit[0]);
				this.dimensionCol=Integer.parseInt(arraySplit[1]);
			}
			while(null!= (linea= br.readLine())){
				arraySplit= linea.split(" ");
				matriz[Integer.parseInt(arraySplit[0])][Integer.parseInt(arraySplit[1])]=Float.parseFloat(arraySplit[2]);
			}
			this.componentes=matriz;
		}
		catch(Exception e){
			System.out.println("Error al construir matriz");
		}
		finally{
			try{                    
	            if( null != fr ){   
	               fr.close();     
	            }                  
	         }catch (Exception e2){ 
	            e2.printStackTrace();
	         }
		}
	}
	
	public void setComponentes(float[][] componentes){
		this.componentes=componentes;
	}
	public int getDimensionFil() {
		return dimensionFil;
	}

	public int getDimensionCol() {
		return dimensionCol;
	}

	public float[][] getComponentes() {
		return componentes;
	}
	
	public String  toString(){
		String resultado="";
		for(int i=0;i<this.getDimensionFil();i++){
			for(int j=0;j<this.getDimensionCol();j++){
				resultado+=this.getComponentes()[i][j]+" ";
			}
			resultado += "\n";
		}
		return resultado;
	}
	
	public MatrizMath clone(){
		MatrizMath matriz = new MatrizMath(this.getDimensionFil(),this.getDimensionCol());
		matriz.setComponentes(this.componentes);
		return matriz;
	}
	
	public boolean equals(Object matriz){
		if(matriz == null)
			return false;
		if(!(matriz instanceof MatrizMath))
			return false;
		MatrizMath nuevaMatriz = (MatrizMath) matriz;
		if(nuevaMatriz.getDimensionCol()!=this.getDimensionCol()|| nuevaMatriz.getDimensionFil()!=this.getDimensionFil())
			return false;
		for(int i=0;i<this.getDimensionFil();i++){
			for(int j=0;j<this.getDimensionCol();j++){
				if(this.getComponentes()[i][j]!=nuevaMatriz.getComponentes()[i][j]){
					return false;
				}
			}
		}
		return true;
	}
	
	
	
}
