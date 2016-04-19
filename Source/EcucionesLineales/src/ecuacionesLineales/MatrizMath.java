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
	
	public void productoDeUnaFila(float[][] matriz, int fila, float factor){
		try {
			if (fila < 0 || fila >= this.dimensionFil)
				throw new ArrayIndexOutOfBoundsException(" Error Valor Indice de Matriz: "+fila);
			for(int i=0;i<this.dimensionCol;i++){
				matriz[fila][i] *= (factor);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				}
		}
	public void intercambiarFilas(float[][] matriz, int filaOrigen, int filaDestino){
		try {
			if (filaOrigen < 0 || filaOrigen >= this.dimensionFil)
				throw new ArrayIndexOutOfBoundsException(" Error Valor Indice de Matriz: "+filaOrigen);
			if (filaDestino < 0 || filaDestino >= this.dimensionFil)
				throw new ArrayIndexOutOfBoundsException(" Error Valor Indice de Matriz: "+filaDestino);
			
			float auxiliar;
			
			for(int i=0;i<this.dimensionCol;i++){
				auxiliar = matriz[filaOrigen][i];
				matriz[filaOrigen][i]=matriz[filaDestino][i];
				matriz[filaDestino][i]=auxiliar;
				
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				}
		}

	public void sumarFilas(float[][] matriz,int filaOrigen,int filaDestino){
		try {
			if (filaOrigen < 0 || filaOrigen >= this.dimensionFil)
				throw new ArrayIndexOutOfBoundsException(" Error Valor Indice de Matriz: "+filaOrigen);
			if (filaDestino < 0 || filaDestino >= this.dimensionFil)
				throw new ArrayIndexOutOfBoundsException(" Error Valor Indice de Matriz: "+filaDestino);
			
			for(int i=0;i<this.dimensionCol;i++){
				
				matriz[filaDestino][i]+=matriz[filaOrigen][i];
								
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				}
		}
	
	
	public MatrizMath Producto(float nro){
		
		float[][] componentes = new float[this.getDimensionFil()][this.getDimensionCol()];
		MatrizMath matriz = new MatrizMath(this.getDimensionFil(),this.getDimensionCol());
		for (int i = 0; i < this.getDimensionFil(); i++) {
			for (int j = 0; j < this.getDimensionCol(); j++) {
				componentes[i][j] = this.getComponentes()[i][j]*nro;
				
			}
		
		}
		matriz.setComponentes(componentes);
		return matriz;
		
	}
	
	public MatrizMath Producto(MatrizMath mat){
		MatrizMath matriz = null;
		
		try{
			if(this.getDimensionCol()!=mat.getDimensionFil())
				throw new DistDimException(" Distinta Dimension ");
			matriz = new MatrizMath(this.getDimensionFil(), mat.getDimensionCol());
			float[][] componentes = new float[this.getDimensionFil()][mat.getDimensionCol()];
			
			for (int i = 0; i < this.getDimensionFil(); i++) {
				for (int j = 0; j < mat.getDimensionCol(); j++) {
					for (int k = 0; k < this.getDimensionCol(); k++) {
						componentes[i][j]+=this.getComponentes()[i][k]+mat.getComponentes()[k][j];
					}
					
				}
				
			}
			matriz.setComponentes(componentes);
			return matriz;
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return matriz;
				
	}
	
	
	public void intercambiarConRenglonNoNuloPorDebajo(float matriz[][], int filaColumna){
		try {
			if (filaColumna < 0 || filaColumna >= this.dimensionFil)
				throw new ArrayIndexOutOfBoundsException(" Error Valor Indice de Matriz: "+filaColumna);
			if (filaColumna < 0 || filaColumna >= this.dimensionCol)
				throw new ArrayIndexOutOfBoundsException(" Error Valor Indice de Matriz: "+filaColumna);
			
			for(int i=filaColumna+1;i<this.dimensionFil;i++){
				
				if (matriz[i][filaColumna]!=0) {
					this.intercambiarFilas(matriz, filaColumna, i);
					i=this.dimensionFil;
				} 
								
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				}
		}
	
	public void intercambiarConRenglonNoNuloPorArriba(float matriz[][], int filaColumna){
		try {
			if (filaColumna < 0 || filaColumna >= this.dimensionFil)
				throw new ArrayIndexOutOfBoundsException(" Error Valor Indice de Matriz: "+filaColumna);
			if (filaColumna < 0 || filaColumna >= this.dimensionCol)
				throw new ArrayIndexOutOfBoundsException(" Error Valor Indice de Matriz: "+filaColumna);
			
			for(int i=filaColumna-1;i >=0;i--){
				
				if (matriz[i][filaColumna]!=0) {
					this.intercambiarFilas(matriz, filaColumna, i);
					i= -1 ;
				} 
								
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				}
		}
	public void llevarACeroPosicionesPorDebajo(float[][] matriz ,int filaColumna){
		try {
			if (filaColumna < 0 || filaColumna >= this.dimensionFil)
				throw new ArrayIndexOutOfBoundsException(" Error Valor Indice de Matriz: "+filaColumna);
			if (filaColumna < 0 || filaColumna >= this.dimensionCol)
				throw new ArrayIndexOutOfBoundsException(" Error Valor Indice de Matriz: "+filaColumna);
			
			for(int i=filaColumna+1;i < this.dimensionFil ;i++){
				
				if (matriz[i][filaColumna]!=0) {
					this.productoDeUnaFila(matriz, i, 1/(matriz[i][filaColumna]));
					this.productoDeUnaFila(matriz, i, (-1)*(matriz[filaColumna][filaColumna]));
					this.sumarFilas(matriz, filaColumna, i);
				} 
								
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				}
	}
	
	
}
	