package ecuacionesLineales;

public class VectorMath {
	
	private int dimension;
	private float[] componentes;
	
	public VectorMath(){
		
	}
	
	public VectorMath(int dimension){
		this.dimension = dimension;
		this.componentes = new float[dimension];
	}
	
	public int getDimension() {
		return dimension;
	}

	public void setDimension(int dimension) {
		this.dimension = dimension;
	}

	public float[] getComponentes() {
		return componentes;
	}

	public void setComponentes(float[] componentes) {
		this.componentes = componentes;
	}
	
	public VectorMath sumar(VectorMath vector){
		
		VectorMath resultado = new VectorMath(this.dimension);
		try {
			
			if (this.dimension != vector.dimension)
				throw new DistDimException(" Distinta Dimension ");
			
			for(int i=0; i<dimension; i++)
				resultado.componentes[i] = this.componentes[i]+vector.componentes[i];

		    } catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return resultado;
	}
	
	public VectorMath restar(VectorMath vector){
		
		VectorMath resultado = new VectorMath(this.dimension);
		try {
			
			if (this.dimension != vector.dimension)
				throw new DistDimException(" Distinta Dimension ");
			
			for(int i=0; i<dimension; i++)
				resultado.componentes[i] = this.componentes[i]-vector.componentes[i];

		    } catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return resultado;
	}
	
	public float producto(VectorMath vector){
		
		float resultado=0;
		try {
			
			if (this.dimension != vector.dimension)
				throw new DistDimException(" Distinta Dimension ");
			
			for(int i=0; i<dimension; i++)
				resultado += this.componentes[i]*vector.componentes[i];

		    } catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return resultado;
	}
	
	public VectorMath producto(MatrizMath matriz){
		
		VectorMath resultado=new VectorMath(this.dimension);
		try {
			
			if (this.dimension != matriz.getDimensionFil())
				throw new DistDimException(" Distinta Dimension ");
			
			for(int i=0; i<dimension; i++)
				for (int j = 0; j < matriz.getDimensionCol(); j++) {
					resultado.componentes[i] += this.componentes[j]*matriz.getComponentes()[j][i];
				}
			
		    } catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return resultado;
	}
	
	public VectorMath producto (float numero){
		float[] componentes  = new float[this.getDimension()];
		VectorMath vector = new VectorMath(this.getDimension());
		for (int i=0;i<this.getDimension();i++) {
			componentes[i]=this.getComponentes()[i]*numero;
		}
		vector.setComponentes(componentes);
		return vector;
	}
	
	public String ToString(){
		String resultado="";
		
		resultado+="Dimension:"+this.dimension+"\n";
		resultado+="( ";
		for (int i = 0; i < componentes.length; i++) {
			resultado += componentes[i]+" ";
			
		}
		resultado+=")";
		return resultado;
	}
	
	public boolean equals(Object vector){
		if (vector==null){
			return false;
		}
		if(!(vector instanceof VectorMath)){
			return false;
		}
		VectorMath vectorMath = (VectorMath) vector;
		if(vectorMath.getDimension()!=this.getDimension())
		{
			return false;
		}
		for (int i = 0; i < this.getDimension(); i++) {
			if(this.getComponentes()[i]!=vectorMath.getComponentes()[i]){
				return false;
			}
		}
		return true;
	}
	
	public VectorMath clone(){
		VectorMath vector = new VectorMath(this.getDimension());
		vector.setComponentes(this.getComponentes());
		return vector;
	}
	
	public double normaUno(){
		double resultado=0;
		for (float f : this.getComponentes()) {
			resultado+=Math.abs(f);
		}
		return resultado;
	}	
	
	public double normaDos(){
		double resultado=0;
		for (float f : this.getComponentes()) {
			resultado+=Math.pow(f, 2);
		}
		return Math.sqrt(resultado);
	}
	
	public double normaInfinito(){
		double maximo=Math.abs(this.getComponentes()[0]);
		for (float f : this.getComponentes()) {
			if(maximo<Math.abs(f))
				maximo=Math.abs(f);
		}
		return maximo;
	}
	
}
